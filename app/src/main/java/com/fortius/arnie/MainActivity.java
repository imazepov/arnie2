package com.fortius.arnie;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.fortius.arnie.data.Exercise;
import com.fortius.arnie.data.ExerciseSet;
import com.fortius.arnie.data.WorkoutSession;
import com.fortius.arnie.data.WorkoutSessionManager;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ActionBarActivity
    implements FbLoginFragment.OnFragmentInteractionListener {

    private CallbackManager callbackManager;
    private boolean isLoggedIn;
    private AccessToken fbAccessToken;
    private boolean splashScreen;
    private volatile WorkoutSession currentSession;
    private List<Long> sessionExerciseIds;

    private class FbLoginCallback implements FacebookCallback<LoginResult> {
        @Override
        public void onSuccess(LoginResult loginResult) {
            MainActivity.this.isLoggedIn = true;
            MainActivity.this.fbAccessToken = loginResult.getAccessToken();
            MainActivity.this.showStartButton();
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException e) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashScreen = true;

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FbLoginCallback());

        WorkoutSession previousSesison = null;
        if (isLoggedIn) {
            showStartButton();
            previousSesison = WorkoutSessionManager.fetchPreviousSession(this);
        }
        if (previousSesison != null && !previousSesison.getExerciseSets().isEmpty()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            WorkoutSession session = previousSesison;
            dialog.setMessage("Previous session exists. Continue?")
                    .setCancelable(false)
                    .setPositiveButton("Continue", (dlg, id) -> {
                        currentSession = session;
                        displayCurrentSession();
                    })
                    .setNegativeButton("Start over", (dlg, id) -> {
                        currentSession = WorkoutSessionManager.startNewSession(this);
                        displayCurrentSession();
                    });
            dialog.show();
        } else {
            setContentView(R.layout.activity_main);
            currentSession = WorkoutSessionManager.startNewSession(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!splashScreen) {
            displayCurrentSession();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WorkoutSessionManager.closeWorkoutSession(this, true);
    }

    private void displayCurrentSession() {
        splashScreen = false;
        setContentView(R.layout.current_session);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                groupExerciseSets(currentSession.getExerciseSets()));

        ListView lvSession = (ListView) findViewById(R.id.lvCurrentSession);
        lvSession.setAdapter(adapter);
        lvSession.setOnItemClickListener(new ExerciseListClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_past_sessions) {
            startActivity(new Intent(this, SessionCalendarActivity.class));
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String exerciseSetGroupToString(List<ExerciseSet> setGroup) {
        if (setGroup.isEmpty()) {
            return "<Empty group>";
        }

        Exercise exercise = setGroup.get(0).getExercise();
        double maxWeight = -1;
        int maxWeightReps = 0;
        for (ExerciseSet set : setGroup) {
            double weight = set.getWeight();
            if (weight > maxWeight) {
                maxWeight = weight;
                maxWeightReps = set.getReps();
            }
        }
        return String.format("%s, %d sets, max weight: %.1f for %d reps", exercise.getName(),
                setGroup.size(), maxWeight, maxWeightReps);
    }

    private List<String> groupExerciseSets(List<ExerciseSet> sets) {
        if (sets == null || sets.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Long, List<ExerciseSet>> groups = new HashMap<>();
        for(ExerciseSet set : sets) {
            long exerciseId = set.getExerciseId();
            List<ExerciseSet> group = groups.get(exerciseId);
            if (group == null) {
                group = new ArrayList<>();
                groups.put(exerciseId, group);
            }
            group.add(set);
        }

        sessionExerciseIds = Lists.newArrayList(groups.keySet());

        return Lists.newArrayList(Iterables.<List<ExerciseSet>, String>transform(groups.values(),
                l -> exerciseSetGroupToString(l)));
    }

    public void showStartButton() {
        Button startBtn = (Button)findViewById(R.id.btnStart);

        FragmentManager mgr = getFragmentManager();
        FbLoginFragment loginFrg = (FbLoginFragment) mgr.findFragmentById(R.id.frgLogin);

        FragmentTransaction tr = mgr.beginTransaction();
        tr.remove(loginFrg);
        tr.commit();

        startBtn.setVisibility(View.VISIBLE);
    }

    // Click handlers
    public void onStartClick(View view) {
        if (currentSession == null) {
            return;
        }
        splashScreen = false;
        displayCurrentSession();
    }

    public void onAddExClick(View view) {
        if (currentSession == null) {
            return;
        }
        startActivity(new Intent(MainActivity.this, SelectExerciseActivity.class));
    }

    public void onEndSessionClick(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to end session?")
                .setCancelable(false)
                .setPositiveButton("I'm done", (dlg, id) -> {
                    WorkoutSessionManager.closeWorkoutSession(this, false);
                    finish();
                })
                .setNegativeButton("Continue", (dlg, id) -> {
                    dlg.cancel();
                })
                .show();
    }

    private class ExerciseListClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Long exerciseId = sessionExerciseIds.get(position);
            if(exerciseId != null) {
                Intent intent = new Intent(MainActivity.this, ExerciseSetsActivity.class)
                        .putExtra("exerciseId", exerciseId);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}

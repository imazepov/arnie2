package com.fortius.arnie;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.google.common.collect.Lists;
import com.fortius.arnie.data.DataAccess;
import com.fortius.arnie.data.WorkoutSessionManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fortius.arnie.data.Exercise;
import com.fortius.arnie.data.ExerciseSet;
import com.fortius.arnie.data.WorkoutSession;
import com.fortius.arnie.data.WorkoutSessionDao;


public class PastSessionViewActivity extends ActionBarActivity {

    private WorkoutSessionDao wsDao;
    private WorkoutSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_session_view);

        Long sessionId = getIntent().getLongExtra("sessionId", -1);
        if (sessionId <= 0) {
            showInvalidSessionDialog();
            return;
        }

        wsDao = DataAccess.getSession(this).getWorkoutSessionDao();
        session = wsDao.load(sessionId);
        if (session == null) {
            showInvalidSessionDialog();
            return;
        }

        this.setTitle(
                String.format(
                        getString(R.string.title_activity_past_session_view),
                        DateFormat.getDateInstance(DateFormat.MEDIUM)
                                .format(session.getStartTime())
                )
        );

        List<ExerciseSet> sets = session.getExerciseSets();
        TreeMap<Exercise, List<ExerciseSet>> groupedSets = new TreeMap<>(
                (ex1, ex2) -> {
                    return ex1.getId().compareTo(ex2.getId());
                });

        for (ExerciseSet set : sets) {
            Exercise ex = set.getExercise();
            List<ExerciseSet> setsForEx = groupedSets.get(ex);
            if (setsForEx == null) {
                setsForEx = new ArrayList<>();
                groupedSets.put(ex, setsForEx);
            }
            setsForEx.add(set);
        }

        List<Map<String, String>> exerciseNames = Lists.<Exercise, Map<String,String>>transform(
                new ArrayList<>(groupedSets.keySet()), ex -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("Text", ex.getName());
                    return m;
                });
        List<List<Map<String, ?>>> listGroups = new ArrayList<>();
        for (List<ExerciseSet> setGroup : groupedSets.values()) {
            listGroups.add(Lists.transform(setGroup, set -> {
                Map<String, String> m = new HashMap<>();
                m.put("Text", String.format("%.1f lbs x %d reps", set.getWeight(), set.getReps()));
                return m;
            }));
        }

        SimpleExpandableListAdapter listAdapter = new SimpleExpandableListAdapter(this,
                exerciseNames, android.R.layout.simple_expandable_list_item_1, new String[] {"Text"},
                new int[] {android.R.id.text1}, listGroups,
                android.R.layout.simple_expandable_list_item_1, new String[] {"Text"},
                new int[] {android.R.id.text1});

        ExpandableListView elv = (ExpandableListView)findViewById(R.id.elvPastSession);
        elv.setAdapter(listAdapter);
    }

    private void showInvalidSessionDialog() {
        new AlertDialog.Builder(this)
                .setMessage("This session no longer exists.")
                .setCancelable(false)
                .setPositiveButton("OK", (dlg, which) -> {
                    finish();
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_past_session_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_remove_past_session && session != null) {
            new AlertDialog.Builder(this)
                    .setMessage("This session is about to be erased permanently. Are you sure you want to delete it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dlg, which) -> {
                        WorkoutSessionManager.removePastSession(this, session);
                        finish();
                    })
                    .setNegativeButton("No", (dlg, which) -> {
                        dlg.cancel();
                    })
                    .show();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

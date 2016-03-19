package com.fortius.arnie;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.common.collect.Lists;
import com.fortius.arnie.data.DataAccess;
import com.fortius.arnie.data.WorkoutSessionManager;

import java.util.Calendar;
import java.util.List;

import com.fortius.arnie.data.DaoSession;
import com.fortius.arnie.data.Exercise;
import com.fortius.arnie.data.ExerciseDao;
import com.fortius.arnie.data.ExerciseSet;
import com.fortius.arnie.data.ExerciseSetDao;
import com.fortius.arnie.data.WorkoutSession;


public class ExerciseSetsActivity extends ActionBarActivity {

    private DaoSession daoSession;
    private ExerciseSetDao setDao;
    private Exercise currentExercise;
    private WorkoutSession currentSession;

    private List<ExerciseSet> sets;

    private ListView lvSets;
    private EditText etReps;
    private EditText etWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_sets);
        daoSession = DataAccess.getSession(this);
        setDao = daoSession.getExerciseSetDao();

        ExerciseDao exerciseDao = daoSession.getExerciseDao();
        Long exerciseId = getIntent().getLongExtra("exerciseId", -1);
        if (exerciseId <= 0) {
            return;
        }

        currentExercise = exerciseDao.load(exerciseId);
        currentSession = WorkoutSessionManager.getCurrentWorkoutSession(this);

        setTitle(String.format(getResources().getString(R.string.title_activity_exercise_sets),
                               currentExercise.getName()));

        sets = setDao.queryBuilder().where(
                ExerciseSetDao.Properties.ExerciseId.eq(exerciseId),
                ExerciseSetDao.Properties.WorkoutSessionId.eq(currentSession.getId())
        ).list();

        lvSets = (ListView) findViewById(R.id.lvSets);
        etReps = (EditText) findViewById(R.id.etReps);
        etWeight = (EditText) findViewById(R.id.etWeight);

        bindSetList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_sets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bindSetList() {
        List<String> listItems = Lists.transform(sets,
                set -> String.format("%.1f lbs x %d reps", set.getWeight(), set.getReps()));
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                listItems);
        lvSets.setAdapter(adapter);
    }

    public void onAddSetClick(View view) {
        ExerciseSet set = new ExerciseSet();
        set.setExercise(currentExercise);
        set.setReps(Integer.valueOf(etReps.getText().toString()));
        set.setWeight(Double.valueOf(etWeight.getText().toString()));
        set.setTimestamp(Calendar.getInstance().getTime()); // now
        set.setWorkoutSession(currentSession);
        setDao.insert(set);

        currentSession.getExerciseSets().add(set);

        sets.add(set);

        bindSetList();
    }
}

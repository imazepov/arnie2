package com.ivan.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.google.common.collect.Lists;
import com.ivan.myapplication.data.DataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import arnie.data.DaoSession;
import arnie.data.Exercise;
import arnie.data.MuscleGroup;
import arnie.data.MuscleGroupDao;


public class SelectExerciseActivity extends ActionBarActivity {

    private DaoSession daoSession;
    private Exercise selectedExercise;

    private List<List<Exercise>> groupedExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);

        daoSession = DataAccess.getSession(this);
        MuscleGroupDao mgDao = daoSession.getMuscleGroupDao();
        List<MuscleGroup> muscleGroups = mgDao.loadAll();
        List<Map<String, String>> groupData = Lists.transform(muscleGroups, mg -> {
            Map<String, String> map = new HashMap<>();
            map.put("Name", mg.getName());
            return map;
        });

        groupedExercises = new ArrayList<>();

        List<List<Map<String, ?>>> childData = Lists.transform(muscleGroups, mg -> {
            List<Map<String, ?>> results = new ArrayList<>();
            List<Exercise> exercises = DataAccess.getExercisesForMuscleGroup(mg);
            groupedExercises.add(exercises);
            for(Exercise ex : exercises) {
                Map<String, String> map = new HashMap<>();
                map.put("Name", ex.getName());
                results.add(map);
            }
            return results;
        });

        SimpleExpandableListAdapter listAdapter = new SimpleExpandableListAdapter(this,
                groupData, android.R.layout.simple_expandable_list_item_1, new String[] {"Name"},
                new int[] {android.R.id.text1}, childData,
                android.R.layout.simple_expandable_list_item_1, new String[] {"Name"},
                new int[] {android.R.id.text1});

        ExpandableListView elvExercises = (ExpandableListView) findViewById(R.id.elvExercises);
        elvExercises.setAdapter(listAdapter);
        elvExercises.setOnChildClickListener(new ExerciseClickListener());
    }

    private class ExerciseClickListener implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                    int childPosition, long id) {
            selectedExercise = groupedExercises.get(groupPosition).get(childPosition);
            Intent intent = new Intent(SelectExerciseActivity.this, ExerciseSetsActivity.class)
                    .putExtra("exerciseId", selectedExercise.getId());
            startActivity(intent);
            finish();
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_exercise, menu);
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
}

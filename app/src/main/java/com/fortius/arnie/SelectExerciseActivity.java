package com.fortius.arnie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.fortius.arnie.data.DaoSession;
import com.fortius.arnie.data.DataAccess;
import com.fortius.arnie.data.Exercise;
import com.fortius.arnie.data.MuscleGroup;
import com.fortius.arnie.data.MuscleGroupDao;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SelectExerciseActivity extends BaseActivity {

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
                map.put("Name", ex.getName() + " - " + ex.getSlug());
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
    protected int getMenuResource() {
        return R.menu.menu_select_exercise;
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

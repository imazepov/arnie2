package com.ivan.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ivan.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arnie.data.DaoMaster;
import arnie.data.DaoSession;
import arnie.data.Exercise;
import arnie.data.ExerciseDao;
import arnie.data.ExerciseMuscleGroupRef;
import arnie.data.ExerciseMuscleGroupRefDao;
import arnie.data.MuscleGroup;
import arnie.data.MuscleGroupDao;
import de.greenrobot.dao.AbstractDao;

public class DataAccess {
    public static final String PREF_FIXTURES_INSERTED = "fixturesInserted";
    public static final String TAG = "DataAccess";
    private static boolean isInitialized;
    private static Object syncRoot = new Object();
    private static DaoMaster master;
    private static DaoSession session;

    public static DaoSession getSession(Context ctx) {
        synchronized (syncRoot) {
            if(!isInitialized) {
                initialize(ctx);
            }
        }

        return session;
    }

    public static List<MuscleGroup> getMuscleGroupsForExercise(Exercise ex) {
        List<MuscleGroup> results = new ArrayList<>();
        List<ExerciseMuscleGroupRef> refs = ex.getMuscleGroupRefs();
        for (ExerciseMuscleGroupRef ref : refs) {
            results.add(ref.getMuscleGroup());
        }
        return results;
    }

    public static List<Exercise> getExercisesForMuscleGroup(MuscleGroup mg) {
        List<Exercise> results = new ArrayList<>();
        List<ExerciseMuscleGroupRef> refs = mg.getExerciseRefs();
        for (ExerciseMuscleGroupRef ref : refs) {
            results.add(ref.getExercise());
        }
        return results;
    }

    private static void initialize(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, "arnie-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        boolean fixturesInserted = session.queryBuilder(Exercise.class).count() > 0;
        if (!fixturesInserted) {
            try {
                insertFixtures(ctx);
                Log.d(TAG, "Fixtures inserted");
            } finally {
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putBoolean(PREF_FIXTURES_INSERTED, true);
                prefsEditor.commit();
            }
        } else {
            Log.d(TAG, "Fixtures are already there");
        }

        isInitialized = true;
    }

    private interface FixtureHandler<T> {
        List<T> processFixture(JSONObject obj) throws JSONException;
    }

    private static void insertFixtures(Context ctx) {
        MuscleGroupDao mgDao = session.getMuscleGroupDao();
        ExerciseDao exDao = session.getExerciseDao();
        ExerciseMuscleGroupRefDao emgDao = session.getExerciseMuscleGroupRefDao();
        Resources resources = ctx.getResources();

        insertFixturesForType(resources.getStringArray(R.array.muscle_groups), mgDao, obj -> {
            long mgId = obj.getLong("id");
            String mgName = obj.getString("name");
            return Arrays.asList(new MuscleGroup(mgId, mgName));
        });

        boolean exercisesInserted = insertFixturesForType(
                resources.getStringArray(R.array.exercises), exDao, obj -> {
                    long exId = obj.getLong("id");
                    String exName = obj.getString("name");
                    return Arrays.asList(new Exercise(exId, exName));
                });

        if (exercisesInserted) {
            insertFixturesForType(resources.getStringArray(R.array.exercises), emgDao, obj -> {
                List<ExerciseMuscleGroupRef> results = new ArrayList<>();
                long exId = obj.getLong("id");
                JSONArray muscleGroupIds = obj.getJSONArray("muscleGroupIds");
                for(int i=0; i<muscleGroupIds.length(); i++) {
                    ExerciseMuscleGroupRef ref = new ExerciseMuscleGroupRef(exId,
                            muscleGroupIds.getLong(i));
                    results.add(ref);
                }
                return results;
            }, true);
        }
    }
    private static <T, IdType> boolean insertFixturesForType(String[] serializedItems,
                                                     AbstractDao<T, IdType> dao,
                                                     FixtureHandler<T> handler) {
        return insertFixturesForType(serializedItems, dao, handler, false);
    }

    private static <T, IdType> boolean insertFixturesForType(String[] serializedItems,
                                                     AbstractDao<T, IdType> dao,
                                                     FixtureHandler<T> handler,
                                                     boolean deleteIfNone) {
        List<T> items = new ArrayList<>();
        for (String mgJson : serializedItems) {
            try {
                JSONObject obj = new JSONObject(mgJson);
                List<T> newItems = handler.processFixture(obj);
                items.addAll(newItems);
            } catch (JSONException ex) {
                // Log
            }
        }
        if(!items.isEmpty() || deleteIfNone) {
            dao.deleteAll();
            for(T item : items) {
                dao.insert(item);
            }
            return true;
        }
        return false;
    }
}

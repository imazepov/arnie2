package com.fortius.arnie.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

public class WorkoutSessionManager {
    private static WorkoutSession currentSession;
    private static WorkoutSession previousSession;

    public static WorkoutSession fetchPreviousSession(Context ctx) {
        if (previousSession != null) {
            return previousSession;
        }

        WorkoutSessionDao wsDao = DataAccess.getSession(ctx).getWorkoutSessionDao();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        long currentSessionId = prefs.getLong("sessionId", -1);
        if (currentSessionId > 0) {
            previousSession = wsDao.load(currentSessionId);
            return previousSession;
        }

        return null;
    }

    public static WorkoutSession getCurrentWorkoutSession(Context ctx) {
        if (currentSession == null) {
            currentSession = fetchPreviousSession(ctx);
            if (currentSession == null) {
                startNewSession(ctx);
            }
        }
        return currentSession;
    }

    public static WorkoutSession startNewSession(Context ctx) {
        currentSession = new WorkoutSession();
        WorkoutSessionDao wsDao = DataAccess.getSession(ctx).getWorkoutSessionDao();
        long currentSessionId = wsDao.insert(currentSession);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putLong("sessionId", currentSessionId);
        ed.commit();

        return currentSession;
    }

    public static void closeWorkoutSession(Context ctx, boolean saveForLater) {
        if (currentSession == null) {
            // The session either has been closed or hasn't been created yet
            return;
        }

        Date minSetTime = new Date(Long.MAX_VALUE);
        Date maxSetTime = new Date(Long.MIN_VALUE);
        for (ExerciseSet set : currentSession.getExerciseSets()) {
            Date timestamp = set.getTimestamp();
            if (timestamp.compareTo(minSetTime) < 0) {
                minSetTime = timestamp;
            }
            if (timestamp.compareTo(maxSetTime) > 0) {
                maxSetTime = timestamp;
            }
        }

        // Update timestamps - session starts with the first set and ends with the last one
        currentSession.setStartTime(minSetTime);
        currentSession.setEndTime(maxSetTime);
        currentSession.update();

        if (!saveForLater) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            SharedPreferences.Editor ed = prefs.edit();
            ed.remove("sessionId");
            ed.commit();

            currentSession = null;
            previousSession = null;
        }
    }

    public static void removePastSession(Context ctx, WorkoutSession session) {
        WorkoutSessionDao wsDao = DataAccess.getSession(ctx).getWorkoutSessionDao();
        wsDao.delete(session);
    }
}

namespace java com.fortius.arnie.data.interop
namespace csharp Arnie.Data.Interop

// Data objects

struct Exercise {
    1: string id;
    2: string displayName;
    3: string muscleGroup;
}

struct ExerciseSet {
    1: string exerciseId;
    2: i64 timestamp;
    3: i32 reps;
    4: double weight;
}

struct WorkoutSession {
    1: i64 startTimestamp;
    2: i64 endTimestamp;
    3: list<ExerciseSet> sets;
}


// Client-server synchronization objects

enum SyncRequestType {
	SYNC_TIME_RANGES = 0,
	SYNC_SESSIONS = 1,
	SYNC_EXERCISES = 2
}

enum SyncResponseType {
	ALL_SYNC = 0,
	MISSING_SESSIONS = 1,
	EXERCISES_SYNC = 2,
	MISSING_EXERCISES = 3
}

struct TimeRange {
	1: i64 startTimestamp;
	2: i64 endTimestamp;
}

struct SyncRequest {
	1: i64 userId;
	2: SyncRequestType requestType;
	// For requestType = SYNC_TIME_INTERVALS
	3: optional list<TimeRange> existingSessionRanges;
	// For requestType = SYNC_SESSIONS
	4: optional list<WorkoutSession> newSessions;
}

struct SyncResponse {
	2: optional list<TimeRange> missingSessionRanges;
	3: optional list<WorkoutSession> missingSessions;
	4: optional list<Exercise> missingExercises;
}

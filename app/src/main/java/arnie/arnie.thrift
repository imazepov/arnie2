namespace java arnie.data.interop

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

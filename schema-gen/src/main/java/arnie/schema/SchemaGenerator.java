package arnie.schema;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class SchemaGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(6, "com.fortius.arnie.data");

        Entity muscleGroup = schema.addEntity("MuscleGroup");
        muscleGroup.addIdProperty();
        muscleGroup.addStringProperty("name");

        Entity exercise = schema.addEntity("Exercise");
        exercise.addIdProperty();
        exercise.addStringProperty("slug");
        exercise.addStringProperty("name");

        Entity exerciseMuscleGroupRef = schema.addEntity("ExerciseMuscleGroupRef");
        Property joinExerciseIdProperty = exerciseMuscleGroupRef.addLongProperty("exerciseId")
                .notNull().getProperty();
        Property joinMuscleGroupIdProperty = exerciseMuscleGroupRef.addLongProperty("muscleGroupId")
                .notNull().getProperty();

        exerciseMuscleGroupRef.addToOne(exercise, joinExerciseIdProperty);
        exerciseMuscleGroupRef.addToOne(muscleGroup, joinMuscleGroupIdProperty);

        exercise.addToMany(exerciseMuscleGroupRef, joinExerciseIdProperty, "muscleGroupRefs");
        muscleGroup.addToMany(exerciseMuscleGroupRef, joinMuscleGroupIdProperty, "exerciseRefs");

        Entity workoutSession = schema.addEntity("WorkoutSession");
        workoutSession.addIdProperty();
        workoutSession.addDateProperty("startTime");
        workoutSession.addDateProperty("endTime");

        Entity exerciseSet = schema.addEntity("ExerciseSet");
        exerciseSet.addIdProperty();
        exerciseSet.addIntProperty("reps");
        exerciseSet.addDoubleProperty("weight");
        exerciseSet.addDateProperty("timestamp");
        Property exerciseIdProperty = exerciseSet.addLongProperty("exerciseId").notNull()
                .getProperty();
        exerciseSet.addToOne(exercise, exerciseIdProperty);
        Property workoutIdProperty = exerciseSet.addLongProperty("workoutSessionId").notNull()
                .getProperty();
        exerciseSet.addToOne(workoutSession, workoutIdProperty);

        exercise.addToMany(exerciseSet, exerciseIdProperty, "exercises");
        workoutSession.addToMany(exerciseSet, workoutIdProperty, "exerciseSets");

        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }
}

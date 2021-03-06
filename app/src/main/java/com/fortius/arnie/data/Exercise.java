package com.fortius.arnie.data;

import java.util.List;
import com.fortius.arnie.data.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EXERCISE.
 */
public class Exercise {

    private Long id;
    private String slug;
    private String name;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ExerciseDao myDao;

    private List<ExerciseMuscleGroupRef> muscleGroupRefs;
    private List<ExerciseSet> exercises;

    public Exercise() {
    }

    public Exercise(Long id) {
        this.id = id;
    }

    public Exercise(Long id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getExerciseDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ExerciseMuscleGroupRef> getMuscleGroupRefs() {
        if (muscleGroupRefs == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ExerciseMuscleGroupRefDao targetDao = daoSession.getExerciseMuscleGroupRefDao();
            List<ExerciseMuscleGroupRef> muscleGroupRefsNew = targetDao._queryExercise_MuscleGroupRefs(id);
            synchronized (this) {
                if(muscleGroupRefs == null) {
                    muscleGroupRefs = muscleGroupRefsNew;
                }
            }
        }
        return muscleGroupRefs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetMuscleGroupRefs() {
        muscleGroupRefs = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ExerciseSet> getExercises() {
        if (exercises == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ExerciseSetDao targetDao = daoSession.getExerciseSetDao();
            List<ExerciseSet> exercisesNew = targetDao._queryExercise_Exercises(id);
            synchronized (this) {
                if(exercises == null) {
                    exercises = exercisesNew;
                }
            }
        }
        return exercises;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetExercises() {
        exercises = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}

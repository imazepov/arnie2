package com.fortius.arnie.data;

import java.util.List;
import com.fortius.arnie.data.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table WORKOUT_SESSION.
 */
public class WorkoutSession {

    private Long id;
    private java.util.Date startTime;
    private java.util.Date endTime;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient WorkoutSessionDao myDao;

    private List<ExerciseSet> exerciseSets;

    public WorkoutSession() {
    }

    public WorkoutSession(Long id) {
        this.id = id;
    }

    public WorkoutSession(Long id, java.util.Date startTime, java.util.Date endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWorkoutSessionDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ExerciseSet> getExerciseSets() {
        if (exerciseSets == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ExerciseSetDao targetDao = daoSession.getExerciseSetDao();
            List<ExerciseSet> exerciseSetsNew = targetDao._queryWorkoutSession_ExerciseSets(id);
            synchronized (this) {
                if(exerciseSets == null) {
                    exerciseSets = exerciseSetsNew;
                }
            }
        }
        return exerciseSets;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetExerciseSets() {
        exerciseSets = null;
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

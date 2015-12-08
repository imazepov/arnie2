package com.ivan.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.common.collect.Lists;
import com.ivan.myapplication.data.DataAccess;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import arnie.data.Exercise;
import arnie.data.ExerciseSet;
import arnie.data.WorkoutSession;
import arnie.data.WorkoutSessionDao;


public class SessionCalendarActivity extends ActionBarActivity {

    private WorkoutSessionDao wsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_calendar);

        wsDao = DataAccess.getSession(this).getWorkoutSessionDao();
        List<WorkoutSession> sessions = wsDao.queryBuilder()
                .where(WorkoutSessionDao.Properties.StartTime.isNotNull(),
                        WorkoutSessionDao.Properties.StartTime.lt(new Date()))
                .orderAsc(WorkoutSessionDao.Properties.StartTime).list();
        Date start = !sessions.isEmpty()
                ? sessions.get(0).getStartTime()
                : Calendar.getInstance().getTime();
        Calendar end = Calendar.getInstance();
        if (!sessions.isEmpty()) {
            end.setTime(sessions.get(sessions.size() - 1).getStartTime());
        }

        Date selectedTime = end.getTime();

        end.add(Calendar.DAY_OF_MONTH, 1);

        CalendarPickerView cv = (CalendarPickerView) findViewById(R.id.calendarView);
        cv.setOnDateSelectedListener(new OnCalendarDateChanged());
        cv.setOnInvalidDateSelectedListener(this::invalidDateSelected);
        cv.init(start, end.getTime())
                .withHighlightedDates(Lists.transform(sessions, WorkoutSession::getStartTime))
                .withSelectedDate(selectedTime);
    }

    private class OnCalendarDateChanged implements CalendarPickerView.OnDateSelectedListener {
        @Override
        public void onDateSelected(Date startDate) {
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(startDate);
            endDate.add(Calendar.DAY_OF_MONTH, 1);

            List<WorkoutSession> sessions = wsDao.queryBuilder().where(
                    WorkoutSessionDao.Properties.StartTime.lt(endDate.getTime()),
                    WorkoutSessionDao.Properties.EndTime.gt(startDate.getTime())
            ).list();

            if (sessions.isEmpty()) {
                invalidDateSelected(startDate);
            } else if (sessions.size() == 1) {
                selectSession(sessions.get(0));
            } else {
                // Select session
                List<String> sessionStrs = Lists.transform(sessions, ws -> {
                    String startTimeStr = new SimpleDateFormat("h:mm a").format(ws.getStartTime());
                    List<ExerciseSet> sets = ws.getExerciseSets();
                    String exerciseStr = "<none>";
                    if (!sets.isEmpty()) {
                        Exercise ex = sets.get(0).getExercise();
                        if (ex != null) {
                            exerciseStr = ex.getName();
                        }
                    }
                    return String.format("%s at %s", startTimeStr, exerciseStr);
                });
                AlertDialog.Builder dialog = new AlertDialog.Builder(SessionCalendarActivity.this)
                        .setTitle("Select session beginning with...")
                        .setItems(sessionStrs.toArray(new String[sessionStrs.size()]), (dlg, which) -> {
                            selectSession(sessions.get(which));
                        });
                dialog.show();
            }
        }

        @Override
        public void onDateUnselected(Date date) {
        }
    }

    private void invalidDateSelected(Date date) {
        Toast toast = Toast.makeText(
                SessionCalendarActivity.this,
                String.format("No workout sessions for %s",
                    new SimpleDateFormat("MMM, d, yyyy").format(date)),
                Toast.LENGTH_SHORT
        );
        toast.show();
    }

    private void selectSession(WorkoutSession session) {
        Intent intent = new Intent(this, PastSessionViewActivity.class);
        intent.putExtra("sessionId", session.getId());
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_session_calendar, menu);
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

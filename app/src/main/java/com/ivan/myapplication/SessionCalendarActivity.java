package com.ivan.myapplication;

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
        cv.init(start, end.getTime())
                .withHighlightedDates(Lists.transform(sessions, ws -> ws.getStartTime()))
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

            Intent intent;
            if (sessions.isEmpty()) {
                Toast toast = Toast.makeText(
                        SessionCalendarActivity.this,
                        String.format("No workout sessions for %s",
                            new SimpleDateFormat("MMM, d, yyyy").format(startDate)),
                        Toast.LENGTH_SHORT
                );
                toast.show();
            } else if (sessions.size() == 1) {
                intent = new Intent(SessionCalendarActivity.this, PastSessionViewActivity.class);
                intent.putExtra("sessionId", sessions.get(0).getId());
                SessionCalendarActivity.this.startActivity(intent);
            } else {
                // Select session
            }
        }

        @Override
        public void onDateUnselected(Date date) {

        }
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

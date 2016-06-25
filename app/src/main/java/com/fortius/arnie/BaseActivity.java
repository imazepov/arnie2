package com.fortius.arnie;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public abstract class BaseActivity extends ActionBarActivity {

    protected Menu optionsMenu;

    protected abstract int getMenuResource();

    protected void customizeMenu(Menu menu) {
    }

    @Override
    protected void onResume() {
        if (!(this instanceof MainActivity)) {
            MainActivity.redirectToMainIfNotLoggedIn(this);
        }

        if (optionsMenu != null) {
            this.onPrepareOptionsMenu(optionsMenu);
        }

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        optionsMenu = menu;

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(getMenuResource(), menu);
        customizeMenu(menu);
        MainActivity.addFbLogoutButton(menu, this);
        return true;
    }
}

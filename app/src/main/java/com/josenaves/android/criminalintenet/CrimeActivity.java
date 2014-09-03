package com.josenaves.android.criminalintenet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class CrimeActivity extends ActionBarActivity {

    private static final String LOG_TAG = CrimeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "CrimeActivity onCreate...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fm = getFragmentManager();

        // this is the activity's layout
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        // attention: even if the activity is destroyed, its fragments aren't, so we don't want
        // to RECREATE fragments at will...
        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}

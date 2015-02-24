package com.josenaves.android.criminalintenet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String LOG_TAG = CrimeActivity.class.getSimpleName();

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}

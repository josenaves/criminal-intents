package com.josenaves.android.criminalintenet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private static final String TAG = "CrimeListFragment";

    private static final int REQUEST_CRIME = 1;

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Get the crime from adapter
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, "Clicked on item " + c.getTitle());

        // Start CrimeActivity
        Intent i = new Intent(getActivity(), CrimeActivity.class);
        startActivityForResult(i, REQUEST_CRIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CRIME) {
            // handle the result
        }
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }

            // configure view for this Crime
            Crime c = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_title);
            titleTextView.setText(c.getTitle());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_date);
            dateTextView.setText(c.getDate().toString());

            CheckBox solved = (CheckBox) convertView.findViewById(R.id.crime_list_item_solved_checkbox);
            solved.setChecked(c.isSolved());

            return convertView;
        }
    }
}

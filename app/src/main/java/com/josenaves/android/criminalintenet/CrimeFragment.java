package com.josenaves.android.criminalintenet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.UUID;


public class CrimeFragment extends Fragment {

    public static final String EXTRA_CRIME_ID = "com.josenaves.android.criminalintent.crime_id";

    private static final String LOG_TAG = CrimeFragment.class.getSimpleName();

    private static final DateFormat DF = new SimpleDateFormat("E, MMM d, y");

    private Crime mCrime;

    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "CrimeFragment onCreate...");
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        Log.d(LOG_TAG, "CrimeFragment onCreateView...");

        View view = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitleField = (EditText) view.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }
        });

        mDateButton = (Button) view.findViewById(R.id.crime_date);
        mDateButton.setText(DF.format(mCrime.getDate()));
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });

        return view;
    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

}

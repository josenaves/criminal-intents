package com.josenaves.android.criminalintenet;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.text.DateFormat;


public class CrimeFragment extends Fragment {

    private static final String LOG_TAG = CrimeFragment.class.getSimpleName();

    private static final DateFormat DF = new SimpleDateFormat("E, MMM d, y");

    private Crime crime;
    private EditText titleField;
    private Button dateButton;
    private CheckBox solvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "CrimeFragment onCreate...");

        super.onCreate(savedInstanceState);
        crime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        Log.d(LOG_TAG, "CrimeFragment onCreateView...");

        View view = inflater.inflate(R.layout.fragment_crime, parent, false);

        titleField = (EditText) view.findViewById(R.id.crime_title);
        titleField.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                crime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }
        });

        dateButton = (Button) view.findViewById(R.id.crime_date);
        dateButton.setText(DF.format(crime.getDate()));
        dateButton.setEnabled(false);

        solvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // set the crime's solved property
                crime.setSolved(isChecked);
            }
        });

        return view;
    }

}

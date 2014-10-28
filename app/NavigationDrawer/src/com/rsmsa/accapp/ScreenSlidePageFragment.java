package com.rsmsa.accapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 *  Created by isaiah on 10/17/2014.
 */
public class ScreenSlidePageFragment extends Fragment {

    EditText tab_one;

    Button pickDate;

    private Calendar cal;

    private int day;

    private int month;

    private int year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.vehicle_one, container, false);

        tab_one = (EditText) rootView.findViewById(R.id.dob_one);


        cal = Calendar.getInstance();

        day = cal.get(Calendar.DAY_OF_MONTH);

        month = cal.get(Calendar.MONTH);

        year = cal.get(Calendar.YEAR);

        pickDate = (Button) rootView.findViewById(R.id.date_picker);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity() , datePickerListener, year, month, day);
                datePickerDialog.show();
            }
        });

        return rootView;
    }

    /**
     * Date change listender
     */
    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            tab_one.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
}
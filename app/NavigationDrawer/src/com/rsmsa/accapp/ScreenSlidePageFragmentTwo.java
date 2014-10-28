package com.rsmsa.accapp;

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

/**
 *  Created by isaiah on 10/20/2014.
 */
public class ScreenSlidePageFragmentTwo extends Fragment {


    EditText tab_one;

    Button pickDate;

    Spinner vehicle_category;

    Button vehicle_type;

    private int selectedSpinner;

    private Calendar cal;

    private int day;

    private int month;

    private int year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.vehicle_two, container, false);

        tab_one = (EditText) rootView.findViewById(R.id.dob_one);

        vehicle_category = (Spinner)rootView.findViewById(R.id.vtype_spinner);

        vehicle_type = (Button)rootView.findViewById(R.id.vehicle_type_select_button);

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

        List<String> vehicle_category_list = new ArrayList<String>();
        vehicle_category_list.add("Private");
        vehicle_category_list.add("Commercial");
        vehicle_category_list.add("Government");
        vehicle_category_list.add("Emergency");
        vehicle_category_list.add("Passenger Service Vehicles");

        vehicle_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSpinner = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        vehicle_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VehicleType.class);
                intent.putExtra("category", selectedSpinner+"");
                startActivity(intent);
            }
        });

        ArrayAdapter<String> atc_adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,vehicle_category_list);
        atc_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        vehicle_category.setAdapter(atc_adapter);

        return rootView;
    }

    /**
     * Date change listender
     */
    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            tab_one.setText(selectedDay + " / " + (selectedMonth + 1) + " / "  + selectedYear);
        }
    };

}
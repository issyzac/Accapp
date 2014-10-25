package Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rsmsa.accapp.MainActivity;
import com.rsmsa.accapp.Passenger;
import com.rsmsa.accapp.R;

import java.util.Calendar;

/**
 *  Created by isaiah on 10/21/2014.
 */

public class VehicleOnePassengerOneScreenSlide extends Fragment {

    ViewGroup rootView;
    EditText tab_one;

    /**
     * defining the instances of our view elements
     */

    public Button okay_button;
    public EditText ViewName;
    public EditText ViewDateOfBirth;
    public EditText ViewAddress;
    public EditText ViewPhysicalAddress;
    public EditText ViewNationalId;
    public EditText ViewPhoneNo;
    public EditText ViewDrugsAlcohol;
    public CheckBox ViewSeatbeltHelmet;

    Button pickDate;

    private Calendar cal;

    private int day;

    private int month;

    private int year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.one_pass_one, container, false);
        tab_one = (EditText) rootView.findViewById(R.id.dob_one);

        init();


        cal = Calendar.getInstance();

        day = cal.get(Calendar.DAY_OF_MONTH);

        month = cal.get(Calendar.MONTH);

        year = cal.get(Calendar.YEAR);

        pickDate = (Button) rootView.findViewById(R.id.date_picker);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), datePickerListener, year, month, day);
                datePickerDialog.show();
            }
        });

        return rootView;
    }

    public void init() {

        /**
         * getting instances of our view elements
         */
        okay_button = (Button) rootView.findViewById(R.id.pass_next_btn);

        ViewName = (EditText) rootView.findViewById(R.id.name_edit);
        ViewDateOfBirth = (EditText) rootView.findViewById(R.id.dob_one);
        ViewAddress = (EditText) rootView.findViewById(R.id.address_box_one);
        ViewPhysicalAddress = (EditText) rootView.findViewById(R.id.physical_address_one);
        ViewNationalId = (EditText) rootView.findViewById(R.id.national_id_one);
        ViewPhoneNo = (EditText) rootView.findViewById(R.id.phone_no_one);
        ViewDrugsAlcohol = (EditText) rootView.findViewById(R.id.nationality_one);
        ViewSeatbeltHelmet = (CheckBox) rootView.findViewById(R.id.seat_belt_check);

        final RadioButton Male = (RadioButton) rootView.findViewById(R.id.male);
        final RadioButton Female = (RadioButton) rootView.findViewById(R.id.female);
        final RadioButton Fatal = (RadioButton) rootView.findViewById(R.id.fatal);
        final RadioButton severe = (RadioButton) rootView.findViewById(R.id.severe);
        final RadioButton Light = (RadioButton) rootView.findViewById(R.id.light);

        /**
         * getting values of our view elements
         */


        Male.setChecked(true);
        Fatal.setChecked(true);

        Male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Female.setChecked(false);
                    Passenger.Gender11 = "male";
                }
            }
        });

        Female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Male.setChecked(false);
                    Passenger.Gender11 = "female";
                }
            }
        });
        Fatal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    severe.setChecked(false);
                    Light.setChecked(false);
                    Passenger.casuality11 = "fatal";
                }
            }
        });
        severe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Fatal.setChecked(false);
                    Light.setChecked(false);
                    Passenger.casuality11 = "severe";
                }
            }
        });
        Light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Fatal.setChecked(false);
                    severe.setChecked(false);
                    Passenger.casuality11 = "light";
                }
            }
        });


        ViewName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.Name11=ViewName.getText().toString();
                Log.d("isaya", Passenger.Name11+"");
                //Passenger.logging();
            }
        });
        Passenger.DateOfBirth11=ViewDateOfBirth.getText().toString();
        Passenger.Address11=ViewAddress.getText().toString();
        Passenger.PhysicalAddress11=ViewPhysicalAddress.getText().toString();
        Passenger.NationalId11=ViewNationalId.getText().toString();
        Passenger.PhoneNo11=ViewPhoneNo.getText().toString();
        Passenger.drugs11=ViewDrugsAlcohol.getText().toString();
        Passenger.SeatbeltHelmet11=ViewSeatbeltHelmet.getText().toString();

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
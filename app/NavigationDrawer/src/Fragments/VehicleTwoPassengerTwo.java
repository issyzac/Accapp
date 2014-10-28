package Fragments;

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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rsmsa.accapp.MainActivity;
import com.rsmsa.accapp.Passenger;
import com.rsmsa.accapp.R;

/**
 *  Created by isaiah on 10/21/2014.
 */

public class VehicleTwoPassengerTwo extends Fragment {

    ViewGroup rootView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.two_pass_three, container, false);

        init();

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
                    Passenger.Gender22 = "male";
                }
            }
        });

        Female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Male.setChecked(false);
                    Passenger.Gender22 = "female";
                }
            }
        });
        Fatal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    severe.setChecked(false);
                    Light.setChecked(false);
                    Passenger.casuality22 = "fatal";
                }
            }
        });
        severe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Fatal.setChecked(false);
                    Light.setChecked(false);
                    Passenger.casuality22 = "severe";
                }
            }
        });
        Light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Fatal.setChecked(false);
                    severe.setChecked(false);
                    Passenger.casuality22 = "light";

                }
            }
        });
        Log.d("isaya", Passenger.casuality22+"");

        ViewName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.Name22=ViewName.getText().toString();
                //  Log.d("isaya", Passenger.Name11+"");
                //Passenger.logging();
            }
        });
        ViewDateOfBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.DateOfBirth22=ViewDateOfBirth.getText().toString();

            }
        });
        ViewAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.Address22=ViewAddress.getText().toString();

            }
        });
        ViewPhysicalAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.PhysicalAddress22=ViewPhysicalAddress.getText().toString();

            }
        });
        ViewNationalId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.NationalId22=ViewNationalId.getText().toString();

            }
        });
        ViewPhoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.PhoneNo22=ViewPhoneNo.getText().toString();

            }
        });
        ViewDrugsAlcohol.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.drugs22=ViewDrugsAlcohol.getText().toString();

            }
        });
        ViewSeatbeltHelmet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Passenger.SeatbeltHelmet22=ViewSeatbeltHelmet.getText().toString();

            }
        });







    }
}

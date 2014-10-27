package com.rsmsa.accapp;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 *  Created by isaiah on 10/20/2014.
 */
public class ScreenSlidePageFragmentTwo extends Fragment {

    EditText tab_one;
    /**
     * Defining layout items.
     */
    EditText inputFatal;
    EditText inputInjuries;
    EditText inputSimple;
    EditText inputNotInjured;

    EditText surname_one;
    EditText othernames_one;
    EditText physical_address_one;
    EditText address_box_one;
    EditText national_id_one;
    EditText phone_no_one;
    RadioGroup gender;
    // As class variables - define your buttons
    EditText nationality_one;
    EditText license_one;
    EditText occupation_one;
    CheckBox drug_edit;
    EditText alcohol_edit;
    CheckBox phone_edit;
    CheckBox seat_belt_edit;
    EditText type_one;
    EditText registration_number_one;
    EditText company_one;
    EditText insurance_type_one;
    EditText insurance_phone;
    EditText policy_period_one;
    EditText policy_number_one;
    EditText repair_amount_one;
    EditText vehicle;
    EditText vehicle_total;
    EditText infrastructure;
    EditText cost;

    private Calendar cal;

    private int day;

    private int month;

    private int year;

    Button pickDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.vehicle_two, container, false);

        tab_one = (EditText) rootView.findViewById(R.id.dob_one);

        /**
         * Defining all layout items
         **/

        inputFatal = (EditText) rootView.findViewById(R.id.fatal_edit);
        inputInjuries = (EditText) rootView.findViewById(R.id.injury_edit);
        inputSimple = (EditText) rootView.findViewById(R.id.simple_edit);
        inputNotInjured = (EditText) rootView.findViewById(R.id.not_injured_edit);

        //driver 0ne details
        surname_one = (EditText) rootView.findViewById(R.id.surname_one);
        othernames_one = (EditText) rootView.findViewById(R.id.othernames_one);
        physical_address_one = (EditText) rootView.findViewById(R.id.physical_address_one);
        address_box_one = (EditText) rootView.findViewById(R.id.address_box_one);
        national_id_one = (EditText) rootView.findViewById(R.id.national_id_one);
        phone_no_one = (EditText) rootView.findViewById(R.id.phone_no_one);
        gender = (RadioGroup) rootView.findViewById(R.id.gender);
        RadioButton male = (RadioButton) rootView.findViewById(R.id.male);
        RadioButton female = (RadioButton) rootView.findViewById(R.id.female);
        nationality_one = (EditText) rootView.findViewById(R.id.nationality_one);
        license_one = (EditText) rootView.findViewById(R.id.license_one);
        occupation_one = (EditText) rootView.findViewById(R.id.occupation_one);


        alcohol_edit = (EditText) rootView.findViewById(R.id.alcohol_edit);
        drug_edit = (CheckBox) rootView.findViewById(R.id.drug_edit);
        phone_edit = (CheckBox) rootView.findViewById(R.id.phone_edit);
        seat_belt_edit = (CheckBox) rootView.findViewById(R.id.seat_belt_edit);

        //Vehicle one details
        type_one = (EditText) rootView.findViewById(R.id.type_one);
        registration_number_one = (EditText) rootView.findViewById(R.id.registration_number_one);

        //Vehicle one  Insurance details
        company_one = (EditText) rootView.findViewById(R.id.company_one);
        insurance_type_one = (EditText) rootView.findViewById(R.id.insurance_type_one);
        insurance_phone = (EditText) rootView.findViewById(R.id.insurance_phone);
        policy_period_one = (EditText) rootView.findViewById(R.id.policy_period_one);
        policy_number_one = (EditText)rootView.findViewById(R.id.policy_number_one);
        repair_amount_one = (EditText) rootView.findViewById(R.id.repair_amount_one);

        //Vehicle one  damage details
        vehicle = (EditText) rootView.findViewById(R.id.vehicle_title_edit);
        vehicle_total = (EditText) rootView.findViewById(R.id.vehicle_total_edit);
        infrastructure = (EditText) rootView.findViewById(R.id.infrastructure_edit);
        cost = (EditText) rootView.findViewById(R.id.rescue_cost_edit);


//Textchange
        inputFatal.addTextChangedListener(new EditTextWatcher(inputFatal));
        inputInjuries.addTextChangedListener(new EditTextWatcher(inputInjuries));
        inputSimple.addTextChangedListener(new EditTextWatcher(inputSimple));
        inputNotInjured.addTextChangedListener(new EditTextWatcher(inputNotInjured));

        surname_one.addTextChangedListener(new EditTextWatcher(surname_one));
        othernames_one.addTextChangedListener(new EditTextWatcher(othernames_one));
        physical_address_one.addTextChangedListener(new EditTextWatcher(physical_address_one));
        address_box_one.addTextChangedListener(new EditTextWatcher(address_box_one));
        national_id_one.addTextChangedListener(new EditTextWatcher(national_id_one));
        phone_no_one.addTextChangedListener(new EditTextWatcher(phone_no_one));
        inputFatal.addTextChangedListener(new EditTextWatcher(inputFatal));
        inputInjuries.addTextChangedListener(new EditTextWatcher(inputInjuries));
        inputSimple.addTextChangedListener(new EditTextWatcher(inputSimple));
        inputNotInjured.addTextChangedListener(new EditTextWatcher(inputNotInjured));
        // gender.addTextChangedListener(new EditTextWatcher(gender));
        nationality_one.addTextChangedListener(new EditTextWatcher(nationality_one));
        license_one.addTextChangedListener(new EditTextWatcher(license_one));
        occupation_one.addTextChangedListener(new EditTextWatcher(occupation_one));

        alcohol_edit.addTextChangedListener(new EditTextWatcher(alcohol_edit));
        //drug_edit.addTextChangedListener(new EditTextWatcher(drug_edit));
        //phone_edit.addTextChangedListener(new EditTextWatcher(phone_edit));
        //seat_belt_edit.addTextChangedListener(new EditTextWatcher(seat_belt_edit));

        type_one.addTextChangedListener(new EditTextWatcher(type_one));
        registration_number_one.addTextChangedListener(new EditTextWatcher(registration_number_one));

        company_one.addTextChangedListener(new EditTextWatcher(company_one));
        insurance_type_one.addTextChangedListener(new EditTextWatcher(insurance_type_one));
        insurance_phone.addTextChangedListener(new EditTextWatcher(insurance_phone));
        policy_period_one.addTextChangedListener(new EditTextWatcher(policy_period_one));
        policy_number_one.addTextChangedListener(new EditTextWatcher(policy_number_one));
        repair_amount_one.addTextChangedListener(new EditTextWatcher(repair_amount_one));

        vehicle.addTextChangedListener(new EditTextWatcher(vehicle));
        vehicle_total.addTextChangedListener(new EditTextWatcher(vehicle_total));
        infrastructure.addTextChangedListener(new EditTextWatcher(infrastructure));
        cost.addTextChangedListener(new EditTextWatcher(cost));


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

    private class EditTextWatcher implements TextWatcher {

        EditText v;

        public EditTextWatcher(EditText view) {
            this.v = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        public void afterTextChanged(Editable s) {

            // Only if the currently edited text field contains something
            if (v.getText().toString().length() > 0) {
                switch (v.getId()) {
                    case R.id.fatal_edit:
                        MainActivity.V2_fatal = inputFatal.getText().toString();
                        break;
                    case R.id.injury_edit:
                        MainActivity.V2_injury = inputInjuries.getText().toString();
                        break;
                    case R.id.simple_edit:
                        MainActivity.V2_simple = inputSimple.getText().toString();
                        break;
                    case R.id.not_injured_edit:
                        MainActivity.V2_not_injured = inputNotInjured.getText().toString();
                        break;
                    case R.id.surname_one:
                        MainActivity.V2_surname = surname_one.getText().toString();
                        break;
                    case R.id.othernames_one:
                        MainActivity.V2_othernames = othernames_one.getText().toString();
                        break;
                    case R.id.physical_address_one:
                        MainActivity.V2_physical_address_one = physical_address_one.getText().toString();
                        break;
                    case R.id.address_box_one:
                        MainActivity.V2_address_box_one = address_box_one.getText().toString();

                    case R.id.national_id_one:
                        MainActivity.V2_national_id_one = national_id_one.getText().toString();
                        break;
                    case R.id.phone_no_one:
                        MainActivity.V2_phone_no_one = phone_no_one.getText().toString();
                        break;
                    case R.id.nationality_one:
                        MainActivity.V2_nationality_one = nationality_one.getText().toString();
                        break;
                    case R.id.license_one:
                        MainActivity.V2_license_one = license_one.getText().toString();
                        break;
                    case R.id.occupation_one:
                        MainActivity.V2_occupation = occupation_one.getText().toString();
                        break;
                    case R.id.alcohol_edit:
                        MainActivity.V2_drug_edit = drug_edit.getText().toString();
                        break;

                    case R.id.type_one:
                        MainActivity.V2_type_one = type_one.getText().toString();
                        break;
                    case R.id.registration_number_one:
                        MainActivity.V2_registration_number_one  = registration_number_one.getText().toString();

                    case R.id.company_one:
                        MainActivity.V2_company_one = company_one.getText().toString();
                        break;
                    case R.id.insurance_type_one:
                        MainActivity.V2_insurance_type_one = insurance_type_one.getText().toString();
                        break;
                    case R.id.insurance_phone:
                        MainActivity.V2_insurance_phone = insurance_phone.getText().toString();
                        break;
                    case R.id.policy_period_one:
                        MainActivity.V2_policy_period_one = policy_period_one.getText().toString();
                        break;
                    case R.id.policy_number_one:
                        MainActivity.V2_policy_number_one = policy_period_one.getText().toString();
                        break;
                    case R.id.repair_amount_one:
                        MainActivity.V2_repair_amount_one = repair_amount_one.getText().toString();
                    case R.id.vehicle_title_edit:
                        MainActivity.V2_vehicle = vehicle.getText().toString();
                        break;
                    case R.id.vehicle_total_edit:
                        MainActivity.V2_vehicle_total = vehicle_total.getText().toString();
                        break;
                    case R.id.infrastructure_edit:
                        MainActivity.V2_infrastructure = infrastructure.getText().toString();

                    case R.id.rescue_cost_edit:
                        MainActivity.V2_cost = cost.getText().toString();
                        break;
                    default:
                }
            }


        }
    }

}
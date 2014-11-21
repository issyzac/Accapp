package com.rsmsa.accapp;

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.Spinner;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

/**
 *  Created by isaiah on 10/20/2014.
 */
public class ScreenSlidePageFragmentTwo extends Fragment {

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
    EditText nationality_one;
    EditText license_one;
    EditText occupation_one;
    CheckBox drug;
    EditText alcohol_edit;
    CheckBox phone_use;
    CheckBox seat_belt;
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
        final RadioButton male = (RadioButton) rootView.findViewById(R.id.male);
        final RadioButton female = (RadioButton) rootView.findViewById(R.id.female);
        nationality_one = (EditText) rootView.findViewById(R.id.nationality_one);
        license_one = (EditText) rootView.findViewById(R.id.license_one);
        occupation_one = (EditText) rootView.findViewById(R.id.occupation_one);


        alcohol_edit = (EditText) rootView.findViewById(R.id.alcohol_edit);
        drug = (CheckBox) rootView.findViewById(R.id.drug_edit);
        phone_use = (CheckBox) rootView.findViewById(R.id.phone_edit);
        seat_belt = (CheckBox) rootView.findViewById(R.id.seat_belt_edit);

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


        /**
         * getting values of our view elements
         */
        drug.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    MainActivity.V2_drug_edit= "Drugs Use";
                }
                else{ MainActivity.V2_drug_edit= " No Drug use";}
            }
        });

        phone_use.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    MainActivity.V2_phone_edit= "Was using Phone";
                }else{ MainActivity.V2_phone_edit= " No phone use";}
            }
        });

        seat_belt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (((CheckBox) v).isChecked()) {

                    MainActivity.V2_seat_belt_edit= "Seat belt not fastened";
                }else{ MainActivity.V2_seat_belt_edit= "Seat belt fastened";}
            }
        });

        male.setChecked(true);
        //   Fatal.setChecked(true);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    female.setChecked(false);
                    MainActivity.V2_gender = "male";
                }
            }
        });


        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    male.setChecked(false);
                    MainActivity.V2_gender = "female";
                }
            }
        });


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

    //TextWatcher
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
                        MainActivity.V2_alcohol_edit = alcohol_edit.getText().toString();
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
package com.rsmsa.accapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by PETER on 10/21/2014.
 */
public class Vehicle1 extends Activity {

    /**
     * JSON Response node names.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_FATAL = "fatal";
    private static String KEY_INJURY = "injury";
    private static String KEY_SIMPLE = "simple";
    private static String KEY_CREATED_AT = "created_at";

    //DRIVER ONE KEYS
    private static String KEY_SURNAME = "surname";
    private static String KEY_OTHER_NAMES= "other_names";
    private static String KEY_PHYSICAL_ADDRESS = "physical_address";
    private static String KEY_BOX_ADDRESS = "box_address";
    private static String KEY_NATIONAL_ID = "national_id";
    private static String KEY_PHONE_NO = "phone_no";
    private static String KEY_GENDER = "gender";
    private static String KEY_DOB = "dob";
    private static String KEY_NATIONALITY = "nationality";
    private static String KEY_LICENSE= "license";
    private static String KEY_OCCUPATION = "occupation";
    private static String KEY_DRUG = "drug";
    private static String KEY_ALCOHOL= "alcohol";
    private static String KEY_PHONE_USE = "phone_use";
    private static String KEY_SEAT_BELT = "seat_belt";

  //VEHICLE ONE KEYS
    private static String KEY_VEHICLE_TYPE= "vehicle_type";
    private static String KEY_REG_NUMBER = "reg_number";
    private static String KEY_OWNER_NAME = "owner_full_name";
    private static String KEY_OWNER_NATIONALITY = "owner_nationality";
    private static String KEY_OWNER_PHYSICAL_ADDRESS = "owner_physical_address";
    private static String KEY_OWNER_BOX_ADDRESS = "owner_box_address";
    private static String KEY_VEHICLE_MODEL= "vehicle_model";
    private static String KEY_YEAR_OF_MANU = "manufacture_year";
    private static String KEY_CHASIS_NO = "chasis_no";


    //VEHICLE ONE INSURANCE_KEYS
    private static String KEY_INSURANCE_COMPANY= "insurance_company";
    private static String KEY_INSURANCE_TYPE = "insurance_type";
    private static String KEY_INSURANCE_PHONE_NO = "insurance_phone_no";
    private static String KEY_POLICY_NUMBER= "policy_no";
    private static String KEY_POLICY_PERIOD = "policy_period";
    private static String KEY_ESTIMATED_REPAIR = "estimated_repair";


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
    RadioButton male;
    RadioButton female;
    EditText dob_one;
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
    EditText repair_amount_one;
    EditText vehicle;
    EditText vehicle_total;
    EditText infrastructure;
    EditText cost;



    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_one);
        /**
         * Defining all layout items
         **/

        inputFatal = (EditText) findViewById(R.id.fatal_edit);
        inputInjuries = (EditText) findViewById(R.id.injury_edit);
        inputSimple = (EditText) findViewById(R.id.simple_edit);
        inputNotInjured = (EditText) findViewById(R.id.not_injured_edit);

        //driver 0ne details
        surname_one = (EditText) findViewById(R.id.surname_one);
        othernames_one = (EditText) findViewById(R.id.othernames_one);
        physical_address_one = (EditText) findViewById(R.id.physical_address_one);
        address_box_one = (EditText) findViewById(R.id.address_box_one);
        national_id_one = (EditText) findViewById(R.id.national_id_one);
        phone_no_one = (EditText) findViewById(R.id.phone_no_one);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        dob_one = (EditText) findViewById(R.id.dob_one);
        nationality_one = (EditText) findViewById(R.id.nationality_one);
        license_one = (EditText) findViewById(R.id.license_one);
        occupation_one = (EditText) findViewById(R.id.occupation_one);


        alcohol_edit = (EditText) findViewById(R.id.alcohol_edit);
        drug_edit = (CheckBox) findViewById(R.id.drug_edit);
        phone_edit = (CheckBox) findViewById(R.id.phone_edit);
        seat_belt_edit = (CheckBox) findViewById(R.id.seat_belt_edit);

        //Vehicle one details
        type_one = (EditText) findViewById(R.id.type_one);
        registration_number_one = (EditText) findViewById(R.id.registration_number_one);

        //Vehicle one  Insurance details
        company_one = (EditText) findViewById(R.id.company_one);
        insurance_type_one = (EditText) findViewById(R.id.insurance_type_one);
        insurance_phone = (EditText) findViewById(R.id.insurance_phone);
        policy_period_one = (EditText) findViewById(R.id.policy_period_one);
        repair_amount_one = (EditText) findViewById(R.id.repair_amount_one);

        //Vehicle one  damage details
        vehicle = (EditText) findViewById(R.id.vehicle_title_edit);
        vehicle_total = (EditText) findViewById(R.id.vehicle_total_edit);
        infrastructure = (EditText) findViewById(R.id.infrastructure_edit);
        cost = (EditText) findViewById(R.id.rescue_cost_edit);

    }


}
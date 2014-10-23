package com.rsmsa.accapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.rsmsa.accapp.library.DatabaseHandler;
import com.rsmsa.accapp.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by PETER on 10/21/2014.
 */
public class Vehicle2 extends Activity {

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
    private static String KEY_OTHER_NAMES = "other_names";
    private static String KEY_PHYSICAL_ADDRESS = "physical_address";
    private static String KEY_BOX_ADDRESS = "box_address";
    private static String KEY_NATIONAL_ID = "national_id";
    private static String KEY_PHONE_NO = "phone_no";
    private static String KEY_GENDER = "gender";
    private static String KEY_DOB = "dob";
    private static String KEY_NATIONALITY = "nationality";
    private static String KEY_LICENSE = "license";
    private static String KEY_OCCUPATION = "occupation";
    private static String KEY_DRUG = "drug";
    private static String KEY_ALCOHOL = "alcohol";
    private static String KEY_PHONE_USE = "phone_use";
    private static String KEY_SEAT_BELT = "seat_belt";

    //VEHICLE ONE KEYS
    private static String KEY_VEHICLE_TYPE = "vehicle_type";
    private static String KEY_REG_NUMBER = "reg_number";

    //VEHICLE ONE INSURANCE_KEYS
    private static String KEY_INSURANCE_COMPANY = "insurance_company";
    private static String KEY_INSURANCE_TYPE = "insurance_type";
    private static String KEY_INSURANCE_PHONE_NO = "insurance_phone_no";
    private static String KEY_POLICY_NUMBER = "policy_no";
    private static String KEY_POLICY_PERIOD = "policy_period";
    private static String KEY_ESTIMATED_REPAIR = "estimated_repair";

    //Vehicle one damage
    private static String KEY_DAMAGE_VEHICLE = "vehicle";
    private static String KEY_VEHICLE_TOTAL = "vehicle_total";
    private static String KEY_INFRASTRUCTURE = "infrastructure";
    private static String KEY_DAMAGE_COST = "cost";
    private static String KEY_PATH = "path";
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
    RadioButton male = null;
    RadioButton female = null;
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
    EditText policy_number_one;
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
        setContentView(R.layout.vehicle_two);
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
        gender = (RadioGroup) findViewById(R.id.gender);
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
        policy_number_one = (EditText) findViewById(R.id.policy_number_one);
        repair_amount_one = (EditText) findViewById(R.id.repair_amount_one);

        //Vehicle one  damage details
        vehicle = (EditText) findViewById(R.id.vehicle_title_edit);
        vehicle_total = (EditText) findViewById(R.id.vehicle_total_edit);
        infrastructure = (EditText) findViewById(R.id.infrastructure_edit);
        cost = (EditText) findViewById(R.id.rescue_cost_edit);

        new NetCheck().execute();

    }

    /**
     * Async Task to check whether internet connection is working
     */
    private class NetCheck extends AsyncTask<String, String, Boolean> {
        private ProgressDialog nDialog;
        private ProcessDriverData asyncTask2;
        private ProcessInsuranceData asyncTask3;
        private ProcessDamageData asyncTask4;




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(Vehicle2.this);
            nDialog.setMessage("Loading..");
            nDialog.setTitle("Checking Network");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {
/**
 * Gets current device state and checks for working internet connection by trying Google.
 **/
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean th) {
            if (th == true) {
                nDialog.dismiss();
                new ProcessVehicleData().execute();
                asyncTask2 = new ProcessDriverData();
                asyncTask3 = new ProcessInsuranceData();
                asyncTask4 = new ProcessDamageData();
                startMyTask(asyncTask2);
                startMyTask(asyncTask3);
                startMyTask(asyncTask4);

            } else {
                nDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error in Network Connection.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class ProcessDriverData extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal, severe, simple, not_injured;
        //driver one details
        String surname, other_names, physical_address, po_box, national_id, phone_no, gender, dob, nationality, licence, occupation, drug, alcohol, phone_use, seatbelt_helmet;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            fatal = inputFatal.getText().toString();
            severe = inputInjuries.getText().toString();
            simple = inputSimple.getText().toString();
            not_injured = inputNotInjured.getText().toString();

            //driver details
            surname = surname_one.getText().toString();
            other_names = othernames_one.getText().toString();
            physical_address = physical_address_one.getText().toString();
            po_box = address_box_one.getText().toString();
            national_id = national_id_one.getText().toString();
            phone_no = phone_no_one.getText().toString();
            //getting the gender value
            if (male.isChecked()) {
                gender = "Male";
            } else if (female.isChecked()) {
                gender = "Female";
            }

            dob = dob_one.getText().toString();
            nationality = nationality_one.getText().toString();
            licence = license_one.getText().toString();
            occupation = occupation_one.getText().toString();
            drug = drug_edit.getText().toString();
            alcohol = alcohol_edit.getText().toString();
            phone_use = phone_edit.getText().toString();
            seatbelt_helmet = seat_belt_edit.getText().toString();

            //driver details
            surname = surname_one.getText().toString();
            other_names = othernames_one.getText().toString();
            physical_address = physical_address_one.getText().toString();
            po_box = address_box_one.getText().toString();
            national_id = national_id_one.getText().toString();

            //getting the gender value
            if (male.isChecked()) {
                gender = "Male";
            } else if (female.isChecked()) {
                gender = "Female";
            }

            dob = dob_one.getText().toString();
            nationality = nationality_one.getText().toString();
            licence = license_one.getText().toString();
            occupation = occupation_one.getText().toString();
            drug = drug_edit.getText().toString();
            alcohol = alcohol_edit.getText().toString();
            phone_use = phone_edit.getText().toString();
            seatbelt_helmet = seat_belt_edit.getText().toString();


            pDialog = new ProgressDialog(Vehicle2.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addDriver(surname, other_names, physical_address, po_box, national_id, phone_no, gender, dob, nationality, licence, occupation, drug, alcohol, phone_use, seatbelt_helmet);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            try {
                if (json != null && json.getString(KEY_SUCCESS) != null) {
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {
                        pDialog.setTitle("Getting Data");
                        pDialog.setMessage("Loading Info");
                        Toast.makeText(getApplicationContext(), "Driver One Details Stored.", Toast.LENGTH_SHORT).show();

                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_driver = json.getJSONObject("driver");
                        /**
                         * Removes all the previous data in the SQlite database
                         **/
                        UserFunctions logout = new UserFunctions();
                        logout.logoutUser(getApplicationContext());

                        /**
                         * Stores registered data in SQlite Database
                         **/
                        db.addDriver(json_driver.getString(KEY_SURNAME), json_driver.getString(KEY_OTHER_NAMES),
                                json_driver.getString(KEY_PHYSICAL_ADDRESS), json_driver.getString(KEY_BOX_ADDRESS),
                                json_driver.getString(KEY_NATIONAL_ID), json_driver.getString(KEY_PHONE_NO),
                                json_driver.getString(KEY_GENDER), json_driver.getString(KEY_DOB), json_driver.getString(KEY_NATIONALITY),
                                json_driver.getString(KEY_LICENSE), json_driver.getString(KEY_OCCUPATION), json_driver.getString(KEY_ALCOHOL),
                                json_driver.getString(KEY_DRUG), json_driver.getString(KEY_PHONE_USE), json_driver.getString(KEY_SEAT_BELT));


                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error Occurred.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProcessVehicleData extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal, severe, simple, not_injured;
        //driver one details
        String vehicle_type, registration_number;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //vehicle details
            vehicle_type = type_one.getText().toString();
            registration_number = othernames_one.getText().toString();

            pDialog = new ProgressDialog(Vehicle2.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addVehicle(vehicle_type, registration_number);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            try {
                if (json != null && json.getString(KEY_SUCCESS) != null) {
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {
                        pDialog.setTitle("Getting Data");
                        pDialog.setMessage("Loading Info");
                        Toast.makeText(getApplicationContext(), "Vehicle One Details Stored.", Toast.LENGTH_SHORT).show();

                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_vehicle = json.getJSONObject("vehicle");
                        /**
                         * Removes all the previous data in the SQlite database
                         **/
                        UserFunctions logout = new UserFunctions();
                        logout.logoutUser(getApplicationContext());

                        /**
                         * Stores registered data in SQlite Database
                         **/
                        db.addVehicle(json_vehicle.getString(KEY_VEHICLE_TYPE), json_vehicle.getString(KEY_REG_NUMBER));
                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error Occurred.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private class ProcessInsuranceData extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        //driver one details
        String insurance_company, type, phone_no, policy_no, policy_period, cost;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //vehicle details

            insurance_company = company_one.getText().toString();
            type = insurance_type_one.getText().toString();
            phone_no = insurance_phone.getText().toString();
            policy_no = policy_period_one.getText().toString();
            policy_period = policy_period_one.getText().toString();
            cost = repair_amount_one.getText().toString();

            pDialog = new ProgressDialog(Vehicle2.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addInsurance(insurance_company, type, phone_no, policy_no, policy_period, cost);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            try {
                if (json != null && json.getString(KEY_SUCCESS) != null) {
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {
                        pDialog.setTitle("Getting Data");
                        pDialog.setMessage("Loading Info");
                        Toast.makeText(getApplicationContext(), "Insurance Details Stored.", Toast.LENGTH_SHORT).show();

                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_insurance = json.getJSONObject("insurance");
                        /**
                         * Removes all the previous data in the SQlite database
                         **/
                        UserFunctions logout = new UserFunctions();
                        logout.logoutUser(getApplicationContext());

                        /**
                         * Stores registered data in SQlite Database
                         **/
                        db.addInsurance(json_insurance.getString(KEY_INSURANCE_COMPANY), json_insurance.getString(KEY_INSURANCE_TYPE),
                                json_insurance.getString(KEY_INSURANCE_PHONE_NO), json_insurance.getString(KEY_POLICY_NUMBER),
                                json_insurance.getString(KEY_POLICY_PERIOD), json_insurance.getString(KEY_ESTIMATED_REPAIR)
                        );
                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error Occurred.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProcessDamageData extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        //damage one details
        String v, total, inf, estimated_cost;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //vehicle details
            v = vehicle.getText().toString();
            total = vehicle_total.getText().toString();
            inf = infrastructure.getText().toString();
            estimated_cost = cost.getText().toString();

            pDialog = new ProgressDialog(Vehicle2.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addDamage(v, total, inf, estimated_cost);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            try {
                if (json != null && json.getString(KEY_SUCCESS) != null) {
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {
                        pDialog.setTitle("Getting Data");
                        pDialog.setMessage("Loading Info");
                        Toast.makeText(getApplicationContext(), "Damage Details Stored.", Toast.LENGTH_SHORT).show();

                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        JSONObject json_damage = json.getJSONObject("damage");
                        /**
                         * Removes all the previous data in the SQlite database
                         **/
                        UserFunctions logout = new UserFunctions();
                        logout.logoutUser(getApplicationContext());

                        /**
                         * Stores registered data in SQlite Database
                         **/
                        db.addDamage(json_damage.getString(KEY_DAMAGE_VEHICLE), json_damage.getString(KEY_VEHICLE_TOTAL),
                                json_damage.getString(KEY_INFRASTRUCTURE), json_damage.getString(KEY_DAMAGE_COST), json_damage.getString(KEY_PATH));
                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error Occurred.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) // API 11
    void startMyTask(AsyncTask asyncTask) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyncTask.execute();
    }
}

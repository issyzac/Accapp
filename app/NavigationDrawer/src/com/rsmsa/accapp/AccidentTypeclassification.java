package com.rsmsa.accapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsmsa.accapp.library.DatabaseHandler;
import com.rsmsa.accapp.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import adapters.SpinnerAdapter;


/**
 *  Created by isaiah on 10/22/2014.
 */
public class AccidentTypeclassification extends Activity {
    /**
     * JSON Response node names.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

    /**
     * Select buttons
     */
    Button accidentTypeSelectButton;

    Button finishButton;

    int selectedSpinner;

    /**
     *
     * Accident type classification spinner
     */
    public Spinner atcSpinner, junctionStructureSpinner, junctionControlSpinner, roadTypeSpinner, surfaceTypeSpinner, roadStructureSpinner,
            surfaceStatusSpinner, roadSurfaceSpinner, lightSpinner, whetherSpinner, controlSpinner, violationOneSpinner, violationTwoSpinner
            ,defectOneSpinner, defectTwoSpinner;


    /**
     * Spinner Adapter declaration
     */
    public SpinnerAdapter dataAdapter;

    /**
     * Spinner Adapter declaration
     */
    ImageView scroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atc);


        accidentTypeSelectButton = (Button)findViewById(R.id.accident_type_select_button);

        finishButton = (Button)findViewById(R.id.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetAsync(view);
            }
        });


        atcSpinner = (Spinner) findViewById(R.id.atc_spinner);
        junctionStructureSpinner = (Spinner) findViewById(R.id.junction_structure_spinner);
        junctionControlSpinner = (Spinner) findViewById(R.id.junction_control_spinner);
        roadTypeSpinner = (Spinner) findViewById(R.id.road_type_spinner);
        surfaceTypeSpinner = (Spinner) findViewById(R.id.surface_type_spinner);
        roadStructureSpinner = (Spinner) findViewById(R.id.road_structure_spinner);
        surfaceStatusSpinner = (Spinner) findViewById(R.id.road_status_spinner);
        roadSurfaceSpinner = (Spinner) findViewById(R.id.road_surface_spinner);
        lightSpinner = (Spinner) findViewById(R.id.light_spinner);
        whetherSpinner = (Spinner) findViewById(R.id.wheather_spinner);
        controlSpinner = (Spinner) findViewById(R.id.control_spinner);
        violationOneSpinner = (Spinner) findViewById(R.id.one_violations_spinner);
        violationTwoSpinner = (Spinner) findViewById(R.id.two_violations_spinner);
        defectOneSpinner = (Spinner) findViewById(R.id.one_defects_spinner);
        defectTwoSpinner = (Spinner) findViewById(R.id.two_defects_spinner);





        List<String> list = new ArrayList<String>();
        list.add("Single vehicle accident");
        list.add("Accidents between vehicles driving same travel direction (2 or more vehicles)");
        list.add("Accidents between vehicles driving opposite travel direction (2 or more vehicles)");
        list.add("Accidents at a junction turning in same or opposite direction (2 or more vehi.)");
        list.add("Collision at a junction between two or more participants");
        list.add("Accident w. parked vehicles");
        list.add("Pedestrian, animals and other accidents");

        List<String> junctionStructure = new ArrayList<String>();
        junctionStructure.add("Crossing Roads");
        junctionStructure.add("Round About");
        junctionStructure.add("T Junction");
        junctionStructure.add("Y Junction");
        junctionStructure.add("Staggered Junction");
        junctionStructure.add("Junc > 4 Arms");
        junctionStructure.add("Bridge/Fly over");
        junctionStructure.add("Rail Cross Manned");
        junctionStructure.add("Rail Cross No Sign");
        junctionStructure.add("Pedestrian Cross");
        junctionStructure.add("none");

        List<String> junctionControl = new ArrayList<String>();
        junctionControl.add("Uncontrolled");
        junctionControl.add("Police Officer");
        junctionControl.add("Traffic Signs");
        junctionControl.add("Traffic Light");
        junctionControl.add("Flashing signal");
        junctionControl.add("none");

        List<String> roadClass = new ArrayList<String>();
        roadClass.add("Trunk Roads");
        roadClass.add("Regional Roads");
        roadClass.add("District Roads");
        roadClass.add("City Roads");
        roadClass.add("Rural Roads");
        roadClass.add("Bridges");

        List<String> surfaceType = new ArrayList<String>();
        surfaceType.add("Paved");
        surfaceType.add("Unpaved");
        surfaceType.add("concrete");
        surfaceType.add("Metal Bridge");
        surfaceType.add("Gravel");
        surfaceType.add("Sandy");

        List<String> roadStructure = new ArrayList<String>();
        roadStructure.add("No Lanes");
        roadStructure.add("1, 2, 3 Lanes");
        roadStructure.add("Hard Shoulder");
        roadStructure.add("Straight");
        roadStructure.add("Slight Curve");
        roadStructure.add("Sharp Curve");

        List<String> roadStatus = new ArrayList<String>();
        roadStatus.add("Flat Road");
        roadStatus.add("Gentle Slope");
        roadStatus.add("Steep Slope");
        roadStatus.add("Hump/Bump");
        roadStatus.add("Dip (Hole/Drift)");
        roadStatus.add("Road Works");

        List<String> roadSurface = new ArrayList<String>();
        roadSurface.add("Dry");
        roadSurface.add("Wet");
        roadSurface.add("Rain");
        roadSurface.add("Water");
        roadSurface.add("Muddy");
        roadSurface.add("Debris");

        List<String> light = new ArrayList<String>();
        light.add("Day");
        light.add("Twilight");
        light.add("Night");
        light.add("Smoke");
        light.add("Street Light");

        List<String> weather = new ArrayList<String>();
        weather.add("Clear");
        weather.add("Cloudy");
        weather.add("Storm");
        weather.add("Fog");

        List<String> control = new ArrayList<String>();
        control.add("Trafic Signal");
        control.add("No Trafic Signal");
        control.add("Lane Marking");
        control.add("Speed Limit/Sign");


        List<String> violation = new ArrayList<String>();
        violation.add("Overspeed");
        violation.add("Overload");
        violation.add("Distance Keeping");
        violation.add("White Lane Cross");
        violation.add("Red Light");
        violation.add("Over Taking");
        violation.add("Wrong Direction");
        violation.add("Drink and Drive");
        violation.add("Careless Pedestrian");
        violation.add("Unsecure Load");
        violation.add("Zebra Crossing");
        violation.add("Others");

        List<String> defects = new ArrayList<String>();
        defects.add("Breaks");
        defects.add("Bad Light");
        defects.add("Bad Tyre");
        defects.add("Tyre Burst");
        defects.add("Others");

        adapterSetter(1, list);


        atcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSpinner = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        finishButton = (Button) findViewById(R.id.finish_button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetAsync(view);
            }
        });

        ArrayAdapter<String> atc_adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);
        atc_adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        atcSpinner.setAdapter(atc_adapter);


        ArrayAdapter<String> junction_structure = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,junctionStructure);
        junction_structure.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        junctionStructureSpinner.setAdapter(junction_structure);

        ArrayAdapter<String> junctionControlAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,junctionControl);
        junctionControlAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        junctionControlSpinner.setAdapter(junctionControlAdapter);

        ArrayAdapter<String> roadTypeAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,roadClass);
        roadTypeAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        roadTypeSpinner.setAdapter(roadTypeAdapter);

        ArrayAdapter<String> surfaceTypeAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,surfaceType);
        surfaceTypeAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        surfaceTypeSpinner.setAdapter(surfaceTypeAdapter);

        ArrayAdapter<String> roadStructureAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,roadStructure);
        roadStructureAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        roadStructureSpinner.setAdapter(roadStructureAdapter);

        ArrayAdapter<String> surfaceStatusAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,roadStatus);
        surfaceStatusAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        surfaceStatusSpinner.setAdapter(surfaceStatusAdapter);

        ArrayAdapter<String> roadSurfaceAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,roadSurface);
        roadSurfaceAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        roadSurfaceSpinner.setAdapter(roadSurfaceAdapter);

        ArrayAdapter<String> lightAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,light);
        lightAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        lightSpinner.setAdapter(lightAdapter);

        ArrayAdapter<String> weatherAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,weather);
        weatherAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        whetherSpinner.setAdapter(weatherAdapter);

        ArrayAdapter<String> controlAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,control);
        controlAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        controlSpinner.setAdapter(controlAdapter);

        ArrayAdapter<String> violationOneAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,violation);
        violationOneAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        violationOneSpinner.setAdapter(violationOneAdapter);

        ArrayAdapter<String> violationTwoAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,violation);
        violationTwoAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        violationTwoSpinner.setAdapter(violationTwoAdapter);

        ArrayAdapter<String> defectOneAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,defects);
        defectOneAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        defectOneSpinner.setAdapter(defectOneAdapter);

        ArrayAdapter<String> defectTwoAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,defects);
        defectTwoAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        defectTwoSpinner.setAdapter(defectTwoAdapter);



        accidentTypeSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atcselectintent = new Intent(AccidentTypeclassification.this, AtcSelect.class);
                atcselectintent.putExtra("classification", selectedSpinner+"");
                Log.d("selected", atcSpinner.getSelectedItem()+"");
                startActivity(atcselectintent);
            }
        });



    }


    /**
     * Async Task to check whether internet connection is working
     */
    private class NetCheck extends AsyncTask<String, String, Boolean> {
        private ProgressDialog nDialog;
        // private ProcessDriverData asyncTask2;
        // private ProcessInsuranceData asyncTask3;
        // private ProcessDamageData asyncTask4;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(AccidentTypeclassification.this);
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
                new ProcessVehicle1Data().execute();
                //new ProcessDriverData().execute();

            } else {
                nDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error in Network Connection.", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private class ProcessDriver1 extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal, severe, simple, not_injured;

        //driver one details
        String surname1 = MainActivity.V1_surname;
        String other_names1 = MainActivity.V1_othernames;
        String physical_address1 = MainActivity.V1_physical_address_one;
        String po_box1 = MainActivity.V1_address_box_one;
        String national_id1 = MainActivity.V1_national_id_one;
        String phone_no1= MainActivity.V1_phone_no_one;
        String gender1 = MainActivity.V1_gender;
        String dob1 = MainActivity.V1_dob_one;
        String nationality1 = MainActivity.V1_nationality_one;
        String licence1 = MainActivity.V1_license_one;
        String occupation1  = MainActivity.V1_occupation;
        String drug1 = MainActivity.V1_drug_edit;
        String alcohol1 = MainActivity.V1_alcohol_edit;
        String phone_use1  = MainActivity.V1_phone_edit;
        String seatbelt_helmet1 = MainActivity.V1_seat_belt_edit;



        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            //pDialog.setTitle("Contacting Servers");
            //pDialog.setMessage("Storing Data...");
            //pDialog.setIndeterminate(false);
            //pDialog.setCancelable(true);
            //pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addDriver(surname1, other_names1, physical_address1, po_box1, national_id1, phone_no1, gender1, dob1, nationality1, licence1, occupation1, drug1, alcohol1, phone_use1, seatbelt_helmet1);
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
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Drivers Details Stored.", Toast.LENGTH_SHORT).show();
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


    private class ProcessDriver2 extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal, severe, simple, not_injured;

        //driver two details
        String surname2 = MainActivity.V2_surname;
        String other_names2 = MainActivity.V2_othernames;
        String physical_address2 = MainActivity.V2_physical_address_one;
        String po_box2 = MainActivity.V2_address_box_one;
        String national_id2 = MainActivity.V2_national_id_one;
        String phone_no2= MainActivity.V2_phone_no_one;
        String gender2 = MainActivity.V2_gender;
        String dob2 = MainActivity.V2_dob_one;
        String nationality2 = MainActivity.V2_nationality_one;
        String licence2 = MainActivity.V2_license_one;
        String occupation2  = MainActivity.V2_occupation;
        String drug2 = MainActivity.V2_drug_edit;
        String alcohol2 = MainActivity.V2_alcohol_edit;
        String phone_use2  = MainActivity.V2_phone_edit;
        String seatbelt_helmet2 = MainActivity.V2_seat_belt_edit;


        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            //pDialog.setTitle("Contacting Servers");
            //pDialog.setMessage("Storing Data...");
            //pDialog.setIndeterminate(false);
            //pDialog.setCancelable(true);
            //pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addDriver(surname2, other_names2, physical_address2, po_box2, national_id2, phone_no2, gender2, dob2, nationality2, licence2, occupation2, drug2, alcohol2, phone_use2, seatbelt_helmet2);
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
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Drivers Two Details Stored.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error Occurred Occured Storing Driver two.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    private class ProcessVehicle1Data extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal1, severe1, simple1, not_injured1;

        //Vehicle one details
        String type1 = MainActivity.V1_type_one;
        String reg_no1 = MainActivity.V1_registration_number_one;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addVehicle(type1, reg_no1);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle One Details Stored.", Toast.LENGTH_SHORT).show();
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

    private class ProcessVehicle2Data extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal1, severe1, simple1, not_injured1;

        //Vehicle one details
        String type2 = MainActivity.V2_type_one; String reg_no2 = MainActivity.V2_registration_number_one;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addVehicle(type2, reg_no2);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle Two Details Stored.", Toast.LENGTH_SHORT).show();
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

    private class ProcessInsurance1Data extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;

        String company_name1 = MainActivity.V1_company_one;
        String company_type1 = MainActivity.V1_insurance_type_one;
        String phone_no1 = MainActivity.V1_phone_no_one;
        String policy_no1 = MainActivity.V1_policy_number_one;
        String expire_period = MainActivity.V1_policy_period_one;
        String repair_costs1 = MainActivity.V1_repair_amount_one;


        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addInsurance(company_name1, company_type1,phone_no1,policy_no1,expire_period,repair_costs1);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle Two Details Stored.", Toast.LENGTH_SHORT).show();
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

    private class ProcessInsurance2Data extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;

        String company_name = MainActivity.V2_company_one;
        String company_type = MainActivity.V2_insurance_type_one;
        String phone_no = MainActivity.V2_phone_no_one;
        String policy_no = MainActivity.V2_policy_number_one;
        String expire_period = MainActivity.V2_policy_period_one;
        String repair_costs = MainActivity.V2_repair_amount_one;


        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addInsurance(company_name, company_type,phone_no,policy_no,expire_period,repair_costs);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle Two Details Stored.", Toast.LENGTH_SHORT).show();
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



    private class Processdamage1Data extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;

        String vehicle = MainActivity.V1_vehicle;
        String total = MainActivity.V1_vehicle_total;
        String infrastructure = MainActivity.V1_infrastructure;
        String costs = MainActivity.V1_cost;


        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addDamage(vehicle,total,infrastructure,costs);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle Two Details Stored.", Toast.LENGTH_SHORT).show();
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


    private class Processdamage2Data extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;

        String vehicle = MainActivity.V2_vehicle;
        String total = MainActivity.V2_vehicle_total;
        String infrastructure = MainActivity.V2_infrastructure;
        String costs = MainActivity.V2_cost;


        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addDamage(vehicle,total,infrastructure,costs);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle Two Details Stored.", Toast.LENGTH_SHORT).show();
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

    private class ProcessLocationData extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;

        String area = MainActivity.acc_area;
        String district = MainActivity.acc_district;
        String region = MainActivity.acc_region;
        String roadName = MainActivity.roadName;
        String roadNo = MainActivity.roadNumber;
        String roadMark = MainActivity.roadMark;
        String IntersectionName = MainActivity.intersectionName;
        String IntersectionNo = MainActivity.intersectionNumber;
        String IntersectionMark = MainActivity.intersectionMark;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pDialog = new ProgressDialog(AccidentTypeclassification.this);
            // pDialog.setTitle("Contacting Servers");
            // pDialog.setMessage("Storing Data...");
            // pDialog.setIndeterminate(false);
            // pDialog.setCancelable(true);
            // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addAccidentLocation(area,roadName,roadNo,roadMark,IntersectionName,IntersectionNo,IntersectionMark);
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
                        // pDialog.setTitle("Getting Data");
                        // pDialog.setMessage("Loading Info");
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Vehicle Two Details Stored.", Toast.LENGTH_SHORT).show();
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


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        // API 11
    void startMyTask(AsyncTask asyncTask) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyncTask.execute();
    }


    public void adapterSetter(int position, List<String> mList) {
        if (position == 1) {
            dataAdapter = new SpinnerAdapter(AccidentTypeclassification.this, mList);
            atcSpinner.setAdapter(dataAdapter);
        }
        if (position == 2) {
            dataAdapter = new SpinnerAdapter(AccidentTypeclassification.this, mList);
            junctionStructureSpinner.setAdapter(dataAdapter);
        }

    }


    public void NetAsync(View view) {
        new NetCheck().execute();

    }
}

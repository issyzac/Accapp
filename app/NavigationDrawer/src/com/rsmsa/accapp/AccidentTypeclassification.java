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
import java.util.HashMap;
import java.util.List;


/**
 *  Created by isaiah on 10/22/2014.
 */
public class AccidentTypeclassification extends Activity {
    /**
     * JSON Response node names.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";

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


    ImageView scroller;
    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
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
        private ProcessDriverData asyncTask2;
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
                new ProcessVehicleData().execute();

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
            HashMap driver = new HashMap();
            driver = db.getDriver();

            surname = (String) driver.get("surname");
            other_names = (String) driver.get("names");
            physical_address = (String) driver.get("address");
            po_box = (String) driver.get("po_box");
            national_id = (String) driver.get("national_id");
            phone_no = (String) driver.get("phone");
            gender = (String) driver.get("gender");
            dob = (String) driver.get("dob");
            nationality = (String) driver.get("nationality");
            licence = (String) driver.get("license");
            occupation = (String) driver.get("occupation");
            alcohol = (String) driver.get("alcohol");
            drug = (String) driver.get("drugs");
            phone_use = (String) driver.get("phone_use");
            seatbelt_helmet = (String) driver.get("helmet");

            pDialog = new ProgressDialog(AccidentTypeclassification.this);
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
                        //Pass sqlite data
                        Toast.makeText(getApplicationContext(), "Driver One Details Stored.", Toast.LENGTH_SHORT).show();
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
        String type, reg_no;

        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            HashMap vehicle = new HashMap();
            vehicle = db.getVehicle();

            type = (String) vehicle.get("vehicle_type");
            reg_no = (String) vehicle.get("vehicle_reg_no");

            pDialog = new ProgressDialog(AccidentTypeclassification.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunctions userFunction = new UserFunctions();

            JSONObject json = userFunction.addVehicle(type, reg_no);
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



    @TargetApi(Build.VERSION_CODES.HONEYCOMB) // API 11
    void startMyTask(AsyncTask asyncTask) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyncTask.execute();
    }


    public void NetAsync(View view){
        new NetCheck().execute();

    }

}

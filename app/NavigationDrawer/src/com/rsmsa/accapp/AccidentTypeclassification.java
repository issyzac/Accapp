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
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.TextView;
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

    public final int REPORT_RESULT = 1;

    public String positionSelected = "-1";

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
  //  public SpinnerAdapter dataAdapter;

   // ImageView scroller;

    public TextView sthinSelected;


    /**
     * Spinner Adapter declaration
     */
    public SpinnerAdapter dataAdapter;

    ImageView scroller;
   // DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atc);

        sthinSelected = (TextView)findViewById(R.id.selected_atc);


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




        atcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSpinner = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

                new ProcessPassengerDB().execute();
                new ProcessVehicle1Data().execute();
                new ProcessDriver1().execute();
                new ProcessDriver2().execute();
                new ProcessVehicle1Data().execute();
                new ProcessVehicle2Data().execute();

            } else {
                nDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error in Network Connection Data will be processed later.", Toast.LENGTH_SHORT).show();
                 ProcessPassengerSQLLITE();
            }
        }
    }


    private class ProcessPassengerDB extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;

        //passenger one vehicle one details
        String name11=Passenger.Name11;
        String gender11=Passenger.Gender11;
        String dob11=Passenger.DateOfBirth11;
        String physical_address11=Passenger.PhysicalAddress11;
        String address_box11=Passenger.Address11;
        String nationality_id11=Passenger.NationalId11;
        String phone_no11=Passenger.PhoneNo11;
        String casuality11=Passenger.casuality11;
        String alcohol11=Passenger.drugs11;
        String seat_helmet11=Passenger.SeatbeltHelmet11;
        String vehicle_no11="1";
        String status11="passenger";

        //passenger two vehicle onedetails
        String name12=Passenger.Name12;
        String gender12=Passenger.Gender12;
        String dob12=Passenger.DateOfBirth12;
        String physical_address12=Passenger.PhysicalAddress12;
        String address_box12=Passenger.Address12;
        String nationality_id12=Passenger.NationalId12;
        String phone_no12=Passenger.PhoneNo12;
        String casuality12=Passenger.casuality12;
        String alcohol12=Passenger.drugs12;
        String seat_helmet12=Passenger.SeatbeltHelmet12;
        String vehicle_no12="1";
        String status12="passenger";



        //passenger three vehicle one details
        String name13=Passenger.Name13;
        String gender13=Passenger.Gender13;
        String dob13=Passenger.DateOfBirth13;
        String physical_address13=Passenger.PhysicalAddress13;
        String address_box13=Passenger.Address13;
        String nationality_id13=Passenger.NationalId13;
        String phone_no13=Passenger.PhoneNo13;
        String casuality13=Passenger.casuality13;
        String alcohol13=Passenger.drugs13;
        String seat_helmet13=Passenger.SeatbeltHelmet13;
        String vehicle_no13="1";
        String status13="passenger";


        //passenger one vehicle two details
        String name21=Passenger.Name21;
        String gender21=Passenger.Gender21;
        String dob21=Passenger.DateOfBirth21;
        String physical_address21=Passenger.PhysicalAddress21;
        String address_box21=Passenger.Address21;
        String nationality_id21=Passenger.NationalId21;
        String phone_no21=Passenger.PhoneNo21;
        String casuality21=Passenger.casuality21;
        String alcohol21=Passenger.drugs21;
        String seat_helmet21=Passenger.SeatbeltHelmet21;
        String vehicle_no21="2";
        String status21="passenger";

        //passenger two vehicle two details
        String name22=Passenger.Name22;
        String gender22=Passenger.Gender22;
        String dob22=Passenger.DateOfBirth22;
        String physical_address22=Passenger.PhysicalAddress22;
        String address_box22=Passenger.Address22;
        String nationality_id22=Passenger.NationalId22;
        String phone_no22=Passenger.PhoneNo22;
        String casuality22=Passenger.casuality22;
        String alcohol22=Passenger.drugs22;
        String seat_helmet22=Passenger.SeatbeltHelmet22;
        String vehicle_no22="2";
        String status22="passenger";

        //passenger three vehicle two details
        String name23=Passenger.Name23;
        String gender23=Passenger.Gender23;
        String dob23=Passenger.DateOfBirth23;
        String physical_address23=Passenger.PhysicalAddress23;
        String address_box23=Passenger.Address23;
        String nationality_id23=Passenger.NationalId23;
        String phone_no23=Passenger.PhoneNo23;
        String casuality23=Passenger.casuality23;
        String alcohol23=Passenger.drugs23;
        String seat_helmet23=Passenger.SeatbeltHelmet23;
        String vehicle_no23="2";
        String status23="passenger";

        //Pedestrian A
        String nameA=Passenger.NameA;
        String genderA=Passenger.GenderA;
        String dobA=Passenger.DateOfBirthA;
        String physical_addressA=Passenger.PhysicalAddressA;
        String address_boxA=Passenger.AddressA;
        String nationality_idA=Passenger.NationalIdA;
        String phone_noA=Passenger.PhoneNoA;
        String casualityA=Passenger.casualityA;
        String alcoholA=Passenger.drugsA;
        String seat_helmetA="";
        String vehicle_noA="ALL";
        String statusA="pedestrian";

        //passenger one vehicle one details
        String nameB=Passenger.NameB;
        String genderB=Passenger.GenderB;
        String dobB=Passenger.DateOfBirthB;
        String physical_addressB=Passenger.PhysicalAddressB;
        String address_boxB=Passenger.AddressB;
        String nationality_idB=Passenger.NationalIdB;
        String phone_noB=Passenger.PhoneNoB;
        String casualityB=Passenger.casualityB;
        String alcoholB=Passenger.drugsB;
        String seat_helmetB="";
        String vehicle_noB="ALL";
        String statusB="pedestrian";

        //passenger one vehicle one details
        String nameC=Passenger.NameC;
        String genderC=Passenger.GenderC;
        String dobC=Passenger.DateOfBirthC;
        String physical_addressC=Passenger.PhysicalAddressC;
        String address_boxC=Passenger.AddressC;
        String nationality_idC=Passenger.NationalIdC;
        String phone_noC=Passenger.PhoneNoC;
        String casualityC=Passenger.casualityC;
        String alcoholC=Passenger.drugsC;
        String seat_helmetC="";
        String vehicle_noC="ALL";
        String statusC="pedestrian";

        //passenger one vehicle one details
        String nameD=Passenger.NameD;
        String genderD=Passenger.GenderD;
        String dobD=Passenger.DateOfBirthD;
        String physical_addressD=Passenger.PhysicalAddressD;
        String address_boxD=Passenger.AddressD;
        String nationality_idD=Passenger.NationalIdD;
        String phone_noD=Passenger.PhoneNoD;
        String casualityD=Passenger.casualityD;
        String alcoholD=Passenger.drugsD;
        String seat_helmetD="";
        String vehicle_noD="ALL";
        String statusD="Others";

        //passenger one vehicle one details
        String nameE=Passenger.NameE;
        String genderE=Passenger.GenderE;
        String dobE=Passenger.DateOfBirthE;
        String physical_addressE=Passenger.PhysicalAddressE;
        String address_boxE=Passenger.AddressE;
        String nationality_idE=Passenger.NationalIdE;
        String phone_noE=Passenger.PhoneNoE;
        String casualityE=Passenger.casualityE;
        String alcoholE=Passenger.drugsE;
        String seat_helmetE="";
        String vehicle_noE="ALL";
        String statusE="Others";

        //passenger one vehicle one details
        String nameF=Passenger.NameF;
        String genderF=Passenger.GenderF;
        String dobF=Passenger.DateOfBirthF;
        String physical_addressF=Passenger.PhysicalAddressF;
        String address_boxF=Passenger.AddressF;
        String nationality_idF=Passenger.NationalIdF;
        String phone_noF=Passenger.PhoneNoF;
        String seat_helmetF="";
        String alcoholF="";
        String casualityF="";
        String vehicle_noF="ALL";
        String statusF="Witness";
        //
        @Override
        protected void onPreExecute() {
            super.onPreExecute();



            pDialog = new ProgressDialog(AccidentTypeclassification.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
           // pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONObject json=new JSONObject();
            UserFunctions userFunction = new UserFunctions();
            if (name11==""&&dob11==""&&physical_address11==""&&address_box11==""&&nationality_id11==""&&phone_no11==""&&casuality11==""&&alcohol11=="") {

            }else{
                json = userFunction.addPerson(name11, gender11, dob11, physical_address11, address_box11, nationality_id11, phone_no11, casuality11, alcohol11, seat_helmet11, vehicle_no11, status11);
            }

            if (name12==""&&dob12==""&&physical_address12==""&&address_box12==""&&nationality_id12==""&&phone_no12==""&&alcohol12=="") {

            }else{
                json = userFunction.addPerson(name12, gender12, dob12, physical_address12, address_box12, nationality_id12, phone_no12, casuality12, alcohol12, seat_helmet12, vehicle_no12, status12);
            }

            if (name13==""&&dob13==""&&physical_address13==""&&address_box13==""&&nationality_id13==""&&phone_no13==""&&alcohol13=="") {

            }else{
                json = userFunction.addPerson(name13, gender13, dob13, physical_address13, address_box13, nationality_id13, phone_no13, casuality13, alcohol13, seat_helmet13, vehicle_no13, status13);
            }

            if (name21==""&&dob21==""&&physical_address21==""&&address_box21==""&&nationality_id21==""&&phone_no21==""&&alcohol21=="") {

            }else{
                json = userFunction.addPerson(name21, gender21, dob21, physical_address21, address_box21, nationality_id21, phone_no21, casuality21, alcohol21, seat_helmet21, vehicle_no21, status21);
            }

            if (name22==""&&dob22==""&&physical_address22==""&&address_box22==""&&nationality_id22==""&&phone_no22==""&&alcohol22=="") {

            }else{
                json = userFunction.addPerson(name22, gender22, dob22, physical_address22, address_box22, nationality_id22, phone_no22, casuality22, alcohol22, seat_helmet22, vehicle_no22, status22);
            }

            if (name23==""&&dob23==""&&physical_address23==""&&address_box23==""&&nationality_id23==""&&phone_no23==""&&alcohol23=="") {

            }else{
                json = userFunction.addPerson(name23, gender23, dob23, physical_address23, address_box23, nationality_id23, phone_no23, casuality23, alcohol23, seat_helmet23, vehicle_no23, status23);
            }
            if (nameA==""&&dobA==""&&physical_addressA==""&&address_boxA==""&&nationality_idA==""&&phone_noA==""&&alcoholA=="") {

            }else{
                json = userFunction.addPerson(nameA, genderA, dobA, physical_addressA, address_boxA, nationality_idA, phone_noA, casualityA, alcoholA, seat_helmetA, vehicle_noA, statusA);
            }

            if (nameB==""&&dobB==""&&physical_addressB==""&&address_boxB==""&&nationality_idB==""&&phone_noB==""&&alcoholB=="") {

            }else{
                json = userFunction.addPerson(nameB, genderB, dobB, physical_addressB, address_boxB, nationality_idB, phone_noB, casualityB, alcoholB, seat_helmetB, vehicle_noB, statusB);
            }
            if (nameC==""&&dobC==""&&physical_addressC==""&&address_boxC==""&&nationality_idC==""&&phone_noC==""&&alcoholC=="") {

            }else{
                json = userFunction.addPerson(nameC, genderC, dobC, physical_addressC, address_boxC, nationality_idC, phone_noC, casualityC, alcoholC, seat_helmetC, vehicle_noC, statusC);
            }
            if (nameD==""&&dobD==""&&physical_addressD==""&&address_boxD==""&&nationality_idD==""&&phone_noD==""&&alcoholD=="") {

            }else{
                json = userFunction.addPerson(nameD, genderD, dobD, physical_addressD, address_boxD, nationality_idD, phone_noD, casualityD, alcoholD, seat_helmetD, vehicle_noD, statusD);
            }
            if (nameE==""&&dobE==""&&physical_addressE==""&&address_boxE==""&&nationality_idE==""&&phone_noE==""&&alcoholE=="") {

            }else{
                json = userFunction.addPerson(nameE, genderE, dobE, physical_addressE, address_boxE, nationality_idE, phone_noE, casualityE, alcoholE, seat_helmetE, vehicle_noE, statusE);
            }
             if (nameF==""&& dobF=="" && casualityF=="" && physical_addressF=="" && address_boxF=="" && nationality_idF=="" && phone_noF=="" && alcoholF=="") {

            }else{
                json = userFunction.addPerson(nameF, genderF, dobF, physical_addressF, address_boxF, nationality_idF, phone_noF, casualityF, alcoholF, seat_helmetF, vehicle_noF, statusF);
            }

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

                        Toast.makeText(getApplicationContext(), "Accident Reported.", Toast.LENGTH_SHORT).show();
                        restore_person_values();

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

    public void ProcessPassengerSQLLITE () {
        /**
         * Defining Process dialog
         */
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());


        //passenger one vehicle one details
        String name11=Passenger.Name11;
        String gender11=Passenger.Gender11;
        String dob11=Passenger.DateOfBirth11;
        String physical_address11=Passenger.PhysicalAddress11;
        String address_box11=Passenger.Address11;
        String nationality_id11=Passenger.NationalId11;
        String phone_no11=Passenger.PhoneNo11;
        String casuality11=Passenger.casuality11;
        String alcohol11=Passenger.drugs11;
        String seat_helmet11=Passenger.SeatbeltHelmet11;
        String vehicle_no11="1";
        String status11="passenger";
        Log.d("mat",name11);
        //passenger two vehicle onedetails
        String name12=Passenger.Name12;
        String gender12=Passenger.Gender12;
        String dob12=Passenger.DateOfBirth12;
        String physical_address12=Passenger.PhysicalAddress12;
        String address_box12=Passenger.Address12;
        String nationality_id12=Passenger.NationalId12;
        String phone_no12=Passenger.PhoneNo12;
        String casuality12=Passenger.casuality12;
        String alcohol12=Passenger.drugs12;
        String seat_helmet12=Passenger.SeatbeltHelmet12;
        String vehicle_no12="1";
        String status12="passenger";



        //passenger three vehicle one details
        String name13=Passenger.Name13;
        String gender13=Passenger.Gender13;
        String dob13=Passenger.DateOfBirth13;
        String physical_address13=Passenger.PhysicalAddress13;
        String address_box13=Passenger.Address13;
        String nationality_id13=Passenger.NationalId13;
        String phone_no13=Passenger.PhoneNo13;
        String casuality13=Passenger.casuality13;
        String alcohol13=Passenger.drugs13;
        String seat_helmet13=Passenger.SeatbeltHelmet13;
        String vehicle_no13="1";
        String status13="passenger";


        //passenger one vehicle two details
        String name21=Passenger.Name21;
        String gender21=Passenger.Gender21;
        String dob21=Passenger.DateOfBirth21;
        String physical_address21=Passenger.PhysicalAddress21;
        String address_box21=Passenger.Address21;
        String nationality_id21=Passenger.NationalId21;
        String phone_no21=Passenger.PhoneNo21;
        String casuality21=Passenger.casuality21;
        String alcohol21=Passenger.drugs21;
        String seat_helmet21=Passenger.SeatbeltHelmet21;
        String vehicle_no21="2";
        String status21="passenger";

        //passenger two vehicle two details
        String name22=Passenger.Name22;
        String gender22=Passenger.Gender22;
        String dob22=Passenger.DateOfBirth22;
        String physical_address22=Passenger.PhysicalAddress22;
        String address_box22=Passenger.Address22;
        String nationality_id22=Passenger.NationalId22;
        String phone_no22=Passenger.PhoneNo22;
        String casuality22=Passenger.casuality22;
        String alcohol22=Passenger.drugs22;
        String seat_helmet22=Passenger.SeatbeltHelmet22;
        String vehicle_no22="2";
        String status22="passenger";

        //passenger three vehicle two details
        String name23=Passenger.Name23;
        String gender23=Passenger.Gender23;
        String dob23=Passenger.DateOfBirth23;
        String physical_address23=Passenger.PhysicalAddress23;
        String address_box23=Passenger.Address23;
        String nationality_id23=Passenger.NationalId23;
        String phone_no23=Passenger.PhoneNo23;
        String casuality23=Passenger.casuality23;
        String alcohol23=Passenger.drugs23;
        String seat_helmet23=Passenger.SeatbeltHelmet23;
        String vehicle_no23="2";
        String status23="passenger";

        //Pedestrian A
        String nameA=Passenger.NameA;
        String genderA=Passenger.GenderA;
        String dobA=Passenger.DateOfBirthA;
        String physical_addressA=Passenger.PhysicalAddressA;
        String address_boxA=Passenger.AddressA;
        String nationality_idA=Passenger.NationalIdA;
        String phone_noA=Passenger.PhoneNoA;
        String casualityA=Passenger.casualityA;
        String alcoholA=Passenger.drugsA;
        String seat_helmetA="";
        String vehicle_noA="ALL";
        String statusA="pedestrian";

        //passenger one vehicle one details
        String nameB=Passenger.NameB;
        String genderB=Passenger.GenderB;
        String dobB=Passenger.DateOfBirthB;
        String physical_addressB=Passenger.PhysicalAddressB;
        String address_boxB=Passenger.AddressB;
        String nationality_idB=Passenger.NationalIdB;
        String phone_noB=Passenger.PhoneNoB;
        String casualityB=Passenger.casualityB;
        String alcoholB=Passenger.drugsB;
        String seat_helmetB="";
        String vehicle_noB="ALL";
        String statusB="pedestrian";

        //passenger one vehicle one details
        String nameC=Passenger.NameC;
        String genderC=Passenger.GenderC;
        String dobC=Passenger.DateOfBirthC;
        String physical_addressC=Passenger.PhysicalAddressC;
        String address_boxC=Passenger.AddressC;
        String nationality_idC=Passenger.NationalIdC;
        String phone_noC=Passenger.PhoneNoC;
        String casualityC=Passenger.casualityC;
        String alcoholC=Passenger.drugsC;
        String seat_helmetC="";
        String vehicle_noC="ALL";
        String statusC="pedestrian";

        //passenger one vehicle one details
        String nameD=Passenger.NameD;
        String genderD=Passenger.GenderD;
        String dobD=Passenger.DateOfBirthD;
        String physical_addressD=Passenger.PhysicalAddressD;
        String address_boxD=Passenger.AddressD;
        String nationality_idD=Passenger.NationalIdD;
        String phone_noD=Passenger.PhoneNoD;
        String casualityD=Passenger.casualityD;
        String alcoholD=Passenger.drugsD;
        String seat_helmetD="";
        String vehicle_noD="ALL";
        String statusD="Others";

        //passenger one vehicle one details
        String nameE=Passenger.NameE;
        String genderE=Passenger.GenderE;
        String dobE=Passenger.DateOfBirthE;
        String physical_addressE=Passenger.PhysicalAddressE;
        String address_boxE=Passenger.AddressE;
        String nationality_idE=Passenger.NationalIdE;
        String phone_noE=Passenger.PhoneNoE;
        String casualityE=Passenger.casualityE;
        String alcoholE=Passenger.drugsE;
        String seat_helmetE="";
        String vehicle_noE="ALL";
        String statusE="Others";

        //passenger one vehicle one details
        String nameF=Passenger.NameF;
        String genderF=Passenger.GenderF;
        String dobF=Passenger.DateOfBirthF;
        String physical_addressF=Passenger.PhysicalAddressF;
        String address_boxF=Passenger.AddressF;
        String nationality_idF=Passenger.NationalIdF;
        String phone_noF=Passenger.PhoneNoF;
        String seat_helmetF="";
        String alcoholF="";
        String casualityF="";
        String vehicle_noF="ALL";
        String statusF="Witness";


        db.addPerson(name11, gender11, dob11, physical_address11, address_box11, nationality_id11, phone_no11, casuality11, alcohol11, seat_helmet11, vehicle_no11, status11);
        db.addPerson(name12, gender12, dob12, physical_address12, address_box12, nationality_id12, phone_no12, casuality12, alcohol12, seat_helmet12, vehicle_no12, status12);

        db.addPerson(name13, gender13, dob13, physical_address13, address_box13, nationality_id13, phone_no13, casuality13, alcohol13, seat_helmet13, vehicle_no13, status13);

        db.addPerson(name21, gender21, dob21, physical_address21, address_box21, nationality_id21, phone_no21, casuality21, alcohol21, seat_helmet21, vehicle_no21, status21);

        db.addPerson(name22, gender22, dob22, physical_address22, address_box22, nationality_id22, phone_no22, casuality22, alcohol22, seat_helmet22, vehicle_no22, status22);

        db.addPerson(name23, gender23, dob23, physical_address23, address_box23, nationality_id23, phone_no23, casuality23, alcohol23, seat_helmet23, vehicle_no23, status23);

        db.addPerson(nameA, genderA, dobA, physical_addressA, address_boxA, nationality_idA, phone_noA, casualityA, alcoholA, seat_helmetA, vehicle_noA, statusA);

        db.addPerson(nameB, genderB, dobB, physical_addressB, address_boxB, nationality_idB, phone_noB, casualityB, alcoholB, seat_helmetB, vehicle_noB, statusB);

        db.addPerson(nameC, genderC, dobC, physical_addressC, address_boxC, nationality_idC, phone_noC, casualityC, alcoholC, seat_helmetC, vehicle_noC, statusC);

        db.addPerson(nameD, genderD, dobD, physical_addressD, address_boxD, nationality_idD, phone_noD, casualityD, alcoholD, seat_helmetD, vehicle_noD, statusD);

        db.addPerson(nameE, genderE, dobE, physical_addressE, address_boxE, nationality_idE, phone_noE, casualityE, alcoholE, seat_helmetE, vehicle_noE, statusE);

        db.addPerson(nameF, genderF, dobF, physical_addressF, address_boxF, nationality_idF, phone_noF, casualityF, alcoholF, seat_helmetF, vehicle_noF, statusF);

        restore_person_values();

    }

 public void restore_person_values(){
     //passenger one vehicle one details
     Passenger.Name11="";
     Passenger.Gender11="";
     Passenger.DateOfBirth11="";
     Passenger.PhysicalAddress11="";
     Passenger.Address11="";
     Passenger.NationalId11="";
     Passenger.PhoneNo11="";
     Passenger.casuality11="";
     Passenger.drugs11="";
     Passenger.SeatbeltHelmet11="";
    private class ProcessDriver1 extends AsyncTask<String, String, JSONObject> {
        /**
         * Defining Process dialog
         */
        private ProgressDialog pDialog;
        String fatal, severe, simple, not_injured;

     //passenger two vehicle onedetails
     Passenger.Name12="";
     Passenger.Gender12="";
     Passenger.DateOfBirth12="";
     Passenger.PhysicalAddress12="";
     Passenger.Address12="";
     Passenger.NationalId12="";
     Passenger.PhoneNo12="";
     Passenger.casuality12="";
     Passenger.drugs12="";
     Passenger.SeatbeltHelmet12="";

     //passenger three vehicle one details
     Passenger.Name13="";
     Passenger.Gender13="";
     Passenger.DateOfBirth13="";
     Passenger.PhysicalAddress13="";
     Passenger.Address13="";
     Passenger.NationalId13="";
     Passenger.PhoneNo13="";
     Passenger.casuality13="";
     Passenger.drugs13="";
     Passenger.SeatbeltHelmet13="";

     //passenger one vehicle two details
     Passenger.Name21="";
     Passenger.Gender21="";
     Passenger.DateOfBirth21="";
     Passenger.PhysicalAddress21="";
     Passenger.Address21="";
     Passenger.NationalId21="";
     Passenger.PhoneNo21="";
     Passenger.casuality21="";
     Passenger.drugs21="";
     Passenger.SeatbeltHelmet21="";

     //passenger two vehicle two details
     Passenger.Name22="";
     Passenger.Gender22="";
     Passenger.DateOfBirth22="";
     Passenger.PhysicalAddress22="";
     Passenger.Address22="";
     Passenger.NationalId22="";
     Passenger.PhoneNo22="";
     Passenger.casuality22="";
     Passenger.drugs22="";
     Passenger.SeatbeltHelmet22="";

     //passenger three vehicle two details
     Passenger.Name23="";
     Passenger.Gender23="";
     Passenger.DateOfBirth23="";
     Passenger.PhysicalAddress23="";
     Passenger.Address11="";
     Passenger.NationalId23="";
     Passenger.PhoneNo23="";
     Passenger.casuality23="";
     Passenger.drugs23="";
     Passenger.SeatbeltHelmet23="";

     //Pedestrian A
     Passenger.NameA="";
     Passenger.GenderA="";
     Passenger.DateOfBirthA="";
     Passenger.PhysicalAddressA="";
     Passenger.AddressA="";
     Passenger.NationalIdA="";
     Passenger.PhoneNoA="";
     Passenger.casualityA="";
     Passenger.drugsA="";

     //passenger one vehicle one details
     Passenger.NameB="";
     Passenger.GenderB="";
     Passenger.DateOfBirthB="";
     Passenger.PhysicalAddressB="";
     Passenger.AddressB="";
     Passenger.NationalIdB="";
     Passenger.PhoneNoB="";
     Passenger.casualityB="";
     Passenger.drugsB="";

     //passenger one vehicle one details
     Passenger.NameC="";
     Passenger.GenderC="";
     Passenger.DateOfBirthC="";
     Passenger.PhysicalAddressC="";
     Passenger.AddressC="";
     Passenger.NationalIdC="";
     Passenger.PhoneNoC="";
     Passenger.casualityC="";
     Passenger.drugsC="";

     //passenger one vehicle one details
     Passenger.NameD="";
     Passenger.GenderD="";
     Passenger.DateOfBirthD="";
     Passenger.PhysicalAddressD="";
     Passenger.AddressD="";
     Passenger.NationalIdD="";
     Passenger.PhoneNoD="";
     Passenger.casualityD="";
     Passenger.drugsD="";

     //passenger one vehicle one details
     Passenger.NameE="";
     Passenger.GenderE="";
     Passenger.DateOfBirthE="";
     Passenger.PhysicalAddressE="";
     Passenger.AddressE="";
     Passenger.NationalIdE="";
     Passenger.PhoneNoE="";
     Passenger.casualityE="";
     Passenger.drugsE="";

     //passenger one vehicle one details
     Passenger.NameF="";
     Passenger.GenderF="";
     Passenger.DateOfBirthF="";
     Passenger.PhysicalAddressF="";
     Passenger.AddressF="";
     Passenger.NationalIdF="";
     Passenger.PhoneNoF="";

 }
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

            pDialog = new ProgressDialog(AccidentTypeclassification.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Storing Data...");
            pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
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

    public void NetAsync(View view){
        new NetCheck().execute();

    }


    @Override
    public void onResume(){
        super.onResume();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPORT_RESULT) {
            if (resultCode == RESULT_OK) {
                // code for result
                final Bundle bundle = data.getExtras();
                Log.d("resulty", bundle.getString("item")+"############");
            }
            if (resultCode == RESULT_CANCELED) {
                // Write your code on no result return
            }
        }
    }


}

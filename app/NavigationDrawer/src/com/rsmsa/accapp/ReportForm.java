package com.rsmsa.accapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.os.AsyncTask;
import android.widget.Toast;


/**
 * Importing the location classes
 */
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.rsmsa.accapp.library.UserFunctions;


/**
 *  Created by Isaiah on 8/25/2014.
 */
public class ReportForm extends Activity implements LocationListener{

    /**
     *  JSON Response node names.
     **/
    private static String KEY_ERROR = "error";
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "id";
    private static String KEY_PLATE_NUMBER = "plateNumber";
    private static String KEY_OFFENSE = "offence";
    private static String KEY_COMMIT = "commit";
    private static String KEY_LICENSE = "license";
    private static String KEY_RANK_NO = "rankNo";
    private static String KEY_AMOUNT = "amount";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_LATITUDE = "latitude";
    private static String KEY_LONGITUDE = "longitude";
    public int id;

    /**
     * Location variables
     */
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    public String mLocation;
    public double mLat;
    public double mLong;

    /**
     * Database
     */
 //   public static Database db;

    public String dName;

    public String dLicense;

    public ArrayList<String> desc = new ArrayList<String>();

    public ArrayList<String> type = new ArrayList<String>();

    public int[] offenseTag = new int[10];

    public int count = 0;
    public int offenceCount = 0;

    boolean backFromChild = false;

    public TextView offense_type_text;

    public final int REPORT_RESULT = 1;

    public TextView submit;

    public TextView plateNo;

    public TextView license;

    public TextView date_text;

    public TextView submitText;

    public TextView LocationTitle;

    TextView registerErrorMsg;

    public String plateNumber;

    public RelativeLayout submit_layout;

    public String commit="PartB";

    HashMap User = new HashMap();

    public ArrayList<String> offensesToReport = new ArrayList<String>();

    public int amountToReport = 0;

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_form);
        final Bundle bundle=getIntent().getExtras();
        plateNumber = bundle.getString("plateNo");
        dLicense = bundle.getString("license");

        /**
         * initializing location variables
         */
        latituteField = (TextView) findViewById(R.id.latitude);

        /**
         * get the location manager
         */
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /**
         * define the criteria of how to select the location
         * provider -> use default
         */
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);


        /**
         * initialize location fields
         */
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latituteField.setText("Location error!");
            //longitudeField.setText("Location not available");
        }


        /**
         * connecting to the database
         */
        DatabaseHandlerOffence db = new DatabaseHandlerOffence(getApplicationContext());

        User = db.getUserDetails();

        submit = (TextView)findViewById(R.id.submit_text);

        plateNo =(TextView)findViewById(R.id.plate_no_);
        plateNo.setText(plateNumber);

        submitText = (TextView)findViewById(R.id.submit_text);

        license = (TextView)findViewById(R.id.license);
        license.setText(dLicense);

        submit_layout = (RelativeLayout)findViewById(R.id.submit_layout);
        submit_layout.setVisibility(View.GONE);

        date_text = (TextView)findViewById(R.id.date_text);

        registerErrorMsg = (TextView)findViewById(R.id.error_msg);

        progressBar = (ProgressBar)findViewById(R.id.pbar_report);

        TextView driverName = (TextView)findViewById(R.id.driver_name);
        driverName.setTypeface(MainOffence.Roboto_BoldCondensed);

        TextView plateNumberTitle = (TextView)findViewById(R.id.plate_no_title_);
        plateNumberTitle.setTypeface(MainOffence.Roboto_BoldCondensed);

        TextView driverLicense = (TextView)findViewById(R.id.driver_license);
        driverLicense.setTypeface(MainOffence.Roboto_BoldCondensed);

        TextView plateNumber = (TextView)findViewById(R.id.plate_no_title);
        plateNumber.setTypeface(MainOffence.Roboto_BoldCondensed);

        TextView issuerName = (TextView)findViewById(R.id.issuer_name);
        issuerName.setTypeface(MainOffence.Roboto_BoldCondensed);

        TextView policeName = (TextView)findViewById(R.id.issuer_name_text);


        TextView rank = (TextView)findViewById(R.id.rank_no);
        rank.setTypeface(MainOffence.Roboto_BoldCondensed);

        LocationTitle = (TextView) findViewById(R.id.location_title);
        LocationTitle.setTypeface(MainOffence.Roboto_BoldCondensed);

        TextView stationName = (TextView)findViewById(R.id.rank_no_text);


        TextView issueDate = (TextView)findViewById(R.id.date);
        issueDate.setTypeface(MainOffence.Roboto_BoldCondensed);


        RelativeLayout OffenseType = (RelativeLayout)findViewById(R.id.offense_type);
        OffenseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportForm.this, OffenseListActivity.class);
                ReportForm.this.startActivityForResult(intent, REPORT_RESULT);
            }
        });

        offense_type_text = (TextView)findViewById(R.id.offense_type_text);

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(dName);

        TextView license_number = (TextView)findViewById(R.id.license);
        license_number.setText(dLicense);


        final RadioButton court = (RadioButton)findViewById(R.id.court);
        court.setTypeface(MainOffence.Roboto_BoldCondensed);
        final RadioButton guilty = (RadioButton)findViewById(R.id.guilty);
        guilty.setTypeface(MainOffence.Roboto_BoldCondensed);

        guilty.setChecked(true);

        court.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    guilty.setChecked(false);
                    commit="PartA";
                }
            }
        });

        guilty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    court.setChecked(false);
                    commit="PartB";
                }
            }
        });

        /**
         * setting the default values for the user
         */

        policeName.setText((CharSequence) User.get("fullName"));
        stationName.setText((CharSequence) User.get("station"));
        date_text.setText((CharSequence) User.get("created_at"));

    }

    @Override
    public void onResume(){
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
        if (backFromChild){
            backFromChild = false;
            offense_type_text.setText(count+" offenses selected");

            if(count == 0){submit_layout.setVisibility(View.GONE);}
            else{ submit_layout.setVisibility(View.VISIBLE); }

            submit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // NetAsync(view);
                    if (  ( !license.getText().toString().equals("")) && ( !plateNo.getText().toString().equals("")) )
                    {
                        registerErrorMsg.setText("");
                        NetAsync(view);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),
                                "One or more fields are empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(ReportForm.this, Locale.getDefault());
        String address = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add =   "Country        : "+ obj.getCountryName();
            add = add + " , " + obj.getCountryCode();
            add = add + "\n Admin Area     : " + obj.getAdminArea();
            add = add + "\n Sub Admin Area : " + obj.getSubAdminArea();
            add = add + "\n Locality       : " + obj.getLocality();
            add = add + "\n Address Line   : " + obj.getAddressLine(0);
            //add = add + "\n" + obj.getSubThoroughfare();
            //add = add + "\n" + obj.getPostalCode();

            address = add;

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return address;
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());
        mLat = lat;
        mLong = lng;
        mLocation = getAddress(lat, lng);
        latituteField.setText(mLocation);
       // longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Async Task to check whether internet connection is working
     **/
    private class NetCheck extends AsyncTask< String, String, Boolean>
    {
        //private ProgressDialog nDialog;

        @Override
        protected void onPreExecute(){
            submitText.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
//            nDialog = new ProgressDialog(ReportForm.this);
//            nDialog.setMessage("Loading..");
//            nDialog.setTitle("Checking Network");
//            nDialog.setIndeterminate(false);
//            nDialog.setCancelable(true);
//            nDialog.show();
        }
        @Override
        protected Boolean doInBackground(String... args){
/**
 * Gets current device state and checks for working internet connection by trying Google.
 **/
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
                    urlconn.setConnectTimeout(3000);
                    urlconn.connect();
                    if (urlconn.getResponseCode() == 200) {
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
        protected void onPostExecute(Boolean th){
            if(th == true){
                submitText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                new ProcessRegister().execute();
            }
            else{
                submitText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                registerErrorMsg.setText("Error in Network Connection");
                //should store the data in sql lite temporary until there will be network
            }
        }
    }

    /**
     *To process the data from the offense form
     */

    private class ProcessRegister extends AsyncTask <String, String, JSONObject> {
        /**
         * Defining Process dialog
         **/
        //private ProgressDialog pDialog;
        String input_license,input_plateNumber,input_commit,input_offence,input_issuer_no, input_amount;
        CharSequence Input_issuer;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            submitText.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            Input_issuer =(CharSequence)User.get("rankNo");
            input_issuer_no=(String)Input_issuer;
            input_license = license.getText().toString();
            input_plateNumber = plateNo.getText().toString();
            input_commit = commit;
            input_amount="0";
            offenceCount=count;

//            pDialog = new ProgressDialog(ReportForm.this);
//            pDialog.setTitle("Contacting Servers");
//            pDialog.setMessage("Registering ...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions PFunction = new UserFunctions();
            JSONObject json;

            //Log.d("Database", "type at background is "+type.get(0));

            offensesToReport.add(desc.get(0));

            if (type.get(0).equals("20000")) {
                input_amount = "20000";
                amountToReport += 20000;
            }
            else if (type.get(0).equals("30000")){
                input_amount = "30000";
                amountToReport += 30000;
            }

            json = PFunction.registerOffence(input_license, input_plateNumber, input_commit, desc.get(0), input_issuer_no, input_amount, mLat, mLong);

            for(int i=1; i<count; i++) {
                if (type.get(i).equals("30000")) {
                    input_amount = "30000";
                    amountToReport += 30000;
                }
                else if (type.get(0).equals("20000")){
                    input_amount = "20000";
                    amountToReport += 20000;
                }
                offensesToReport.add(desc.get(i));
                json = PFunction.registerOffence(input_license, input_plateNumber, input_commit, desc.get(i), input_issuer_no, input_amount, mLat, mLong);
            }
             return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            try {
                if (json != null && json.getString(KEY_SUCCESS) != null){
                    registerErrorMsg.setText("");
                    String res = json.getString(KEY_SUCCESS);
                    String red = json.getString(KEY_ERROR);
                    if(Integer.parseInt(res) == 1){
//                        pDialog.setTitle("Getting Data");
//                        pDialog.setMessage("Loading Info");
                        DatabaseHandlerOffence db = new DatabaseHandlerOffence(getApplicationContext());
                        JSONObject json_user = json.getJSONObject("offenceDetails");
                        /**
                         *Stores registered data in SQl lite Database
                         **/
//                        PoliceFunctions logout = new PoliceFunctions();
//                        logout.logoutUser(getApplicationContext());
                                db.addOffence(json_user.getString(KEY_LICENSE),json_user.getString(KEY_OFFENSE),
                                json_user.getString(KEY_PLATE_NUMBER),json_user.getString(KEY_COMMIT),
                                json_user.getString(KEY_CREATED_AT), json_user.getString(KEY_RANK_NO),
                                json_user.getString(KEY_AMOUNT), json_user.getString(KEY_LATITUDE),
                                json_user.getString(KEY_LONGITUDE));
                        /**
                         * Launch Registered screen
                         **/
                        Intent registered = new Intent(getApplicationContext(), Registered.class);
                        /**
                         * Close all views before launching Registered screen
                         **/
                        registered.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        registered.putStringArrayListExtra("list" , offensesToReport);
                        registered.putExtra("amount", amountToReport);

                        submitText.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        startActivity(registered);
                        Log.d("REGISTERED", offensesToReport.toString());
                        finish();
                    }
                }
                else{
                    submitText.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    registerErrorMsg.setText("Error in sending data");
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPORT_RESULT) {
            if (resultCode == RESULT_OK) {
                // code for result
                final Bundle bundle = data.getExtras();
                count = bundle.getInt("count");
                Log.d("Database", "count at parent is "+count);
                offenseTag = bundle.getIntArray("tag");
                desc = bundle.getStringArrayList("desc");
                type = bundle.getStringArrayList("type");
                OffenseListActivity.offenseDesc.clear();
                backFromChild = true;
            }
            if (resultCode == RESULT_CANCELED) {
                // Write your code on no result return
            }
        }
    }

}

package com.rsmsa.accapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import adapters.GridViewAdapterOffence;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rsmsa.accapp.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *  Created by Isaiah on 8/27/2014.
 */

public class MainOffence extends Activity implements OnClickListener {


    /**
     *  JSON Response node names.
     **/
    private static String KEY_ERROR = "error";
    private static String KEY_SUCCESS = "success";
    private static String KEY_TYPE = "type";
    private static String KEY_MAKE = "make";
    private static String KEY_COLOR = "color";
    private static String KEY_NAME = "name";
    private static String KEY_ADDRESS = "address";
    private static String KEY_EXPIRY_DATE = "expiryDate";
    private static String KEY_STATUS = "status";
    private static String KEY_CLASS = "class";
    private static String KEY_OFFENCE= "offence";
    private static String KEY_DATE= "date";
    private static String KEY_COUNT= "count";
    public Button scanBtn;

    public Button reportBtn;

    public Button verifyBtn;

    public String driverName;

    public String licenseNumber;

    public String plateNumber;

    public String licenseNum;

    public boolean licenseHasValue = false;

    public boolean plateNoHasValue = false;

    public boolean isScanned = false;

    public TextView formatTxt, contentTxt;

    public boolean has = false;

    /**
     * Database
     */
    public static Database db;

    /**
     * Cursor to store queried information from database that is used to populate the cards in the gridview
     */
    public static Cursor mCursor;

    private static Cursor drivers;

    public String TAG = "Database";

    public String scanContent;

    public GridViewAdapterOffence gridViewAdapter;

    public String[] date;

    public String[] type;

    public String[] status;

    public EditText license;

    public Cursor offences;

    public EditText plate_no;

    public static Typeface Athletic, Fun_Raiser, Roboto_Condensed, Roboto_Black, Roboto_Light, Roboto_BoldCondensedItalic, Roboto_BoldCondensed, Rosario_Regular, Rosario_Bold, Rosario_Italic, Roboto_Regular, Roboto_Medium;

    /**
     * initializing the output text views
     */
    public GridView historyGridView;
    public String values;
    public String mlicense;

    public TextView historyDate1;
    public TextView historyDate1Title;
    public TextView historyOffence1;
    public TextView historyOffence1Title;
    public View stripOne;

    public TextView historyDate2Title;
    public TextView historyDate2;
    public TextView historyOffence2Title;
    public TextView historyOffence2;
    public View stripTwo;

    public TextView historyDate3Title;
    public TextView historyDate3;
    public TextView historyOffence3Title;
    public TextView historyOffence3;

    public TextView registerErrorMsg;

    public RelativeLayout histOne;
    public RelativeLayout histTwo;

    public ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        /**
         * get the instances of the buttons at our view
         */

        progressBar = (ProgressBar)findViewById(R.id.pbar_main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setTypeface(Rosario_Bold);
        reportBtn = (Button)findViewById(R.id.report_button);
        reportBtn.setTypeface(Rosario_Bold);
        verifyBtn = (Button)findViewById(R.id.verify);
        verifyBtn.setTypeface(Rosario_Bold);

        registerErrorMsg = (TextView)findViewById(R.id.error);
        registerErrorMsg.setTypeface(Rosario_Bold);

        historyDate1Title = (TextView)findViewById(R.id.date_one_title);
        historyDate2Title = (TextView)findViewById(R.id.date_two_title);
        historyDate3Title = (TextView)findViewById(R.id.date_three_title);

        historyOffence1Title = (TextView)findViewById(R.id.offense_one_title);
        historyOffence2Title = (TextView)findViewById(R.id.offense_two_title);
        historyOffence3Title = (TextView)findViewById(R.id.offense_three_title);

        stripOne = (View)findViewById(R.id.strip_one);
        stripTwo = (View)findViewById(R.id.strip_two);

        historyDate1 = (TextView)findViewById(R.id.date_one);
        historyDate1.setTypeface(Rosario_Bold);

        historyOffence1 = (TextView)findViewById(R.id.offense_one);
        historyOffence1.setTypeface(Rosario_Bold);

        historyDate2 = (TextView)findViewById(R.id.date_two);
        historyDate2.setTypeface(Rosario_Bold);

        historyOffence2 = (TextView)findViewById(R.id.offense_two);
        historyOffence2.setTypeface(Rosario_Bold);

        historyDate3 = (TextView)findViewById(R.id.date_three);
        historyDate3.setTypeface(Rosario_Bold);

        historyOffence3 = (TextView)findViewById(R.id.offense_three);
        historyOffence3.setTypeface(Rosario_Bold);

        histOne = (RelativeLayout)findViewById(R.id.hist_one);

        histTwo = (RelativeLayout)findViewById(R.id.hist_two);

        /**
         * getting the instances of our text fields in the activity
         */
        TextView license_title = (TextView)findViewById(R.id.license_title);
        license_title.setTypeface(Rosario_Bold);
        license = (EditText)findViewById(R.id.license_);
        license.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                reportBtn.setVisibility(View.VISIBLE);
                return false;
            }
        });

        TextView plate_no_title = (TextView)findViewById(R.id.plate_no_title);
        plate_no_title.setTypeface(Rosario_Bold);
        plate_no = (EditText)findViewById(R.id.plate_no);

        verifyBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                licenseNum = license.getText().toString();
                plateNumber = plate_no.getText().toString();

                if((licenseNum.equals("")) || (plateNumber.equals(""))){
                    Toast toast = Toast.makeText(MainOffence.this,
                            " Field(s) Empty!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    verifyBtn.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.bringToFront();
                    NetAsync(view);

                }
            }
        });



        /**
         * initialize the string that holds the scan result
         */
        scanContent = null;

        /**
         * initializing the database
         */
        if (db==null){
            db=new Database(this);
        }

        /**
         * adding the listener to the button
         */
        scanBtn.setOnClickListener(this);
        reportBtn.setOnClickListener(this);

        /**
         * get instance of the gridview used to display history of a scanned license
         */
        //historyGridView = (GridView)findViewById(R.id.history_list);
        //historyGridView.setAdapter(new GridViewAdapter(this, values));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
        // menu.findItem(R.id.new_refresh).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        //  Handle action buttons

        /**
         * Removes all the previous data in the SQlite database
         **/


        if (item.getItemId() == R.id.action_logout ) {
//
            SharedPreferences sharedpreferences = getSharedPreferences
                    (LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainOffence.this, LoginActivity.class);
            this.startActivity(intent);
            MainOffence.this.finish();
        }

        if (item.getItemId() == R.id.action_changePasswd ) {
            Intent intent = new Intent(MainOffence.this, ChangePassword.class);
            startActivity(intent);

        }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LoginActivity.justBack = true;
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {

        /**
         * cheking if our button have been clicked if yes we scan
         */
        if(view.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

        /**
         * checking if the clicked button is report button then we respond
         */
        if(view.getId()==R.id.report_button){

            licenseNum = license.getText().toString();
            plateNumber = plate_no.getText().toString();
            if((licenseNum.equals("")) || (plateNumber.equals(""))){
                Toast toast = Toast.makeText(MainOffence.this,
                        " Field(s) Empty!", Toast.LENGTH_SHORT);
                toast.show();
            }else{

                Intent intent = new Intent(this, ReportForm.class);
                intent.putExtra("license", licenseNum);
                intent.putExtra("plateNo", plateNumber);
                this.startActivity(intent);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK){
            //if (scanningResult != null) {

            /**
             * getting the scanned licence number to our temporary variable
             */
            scanContent = scanningResult.getContents();


            Log.d(TAG, scanContent+" SCANNED");

            license.setText(scanContent);
            licenseHasValue = true;

            display(scanContent);

            //we have a result
        }
        else if(resultCode == RESULT_CANCELED){
            Toast toast = Toast.makeText(this,
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void display(String licence){


        /**
         * Querying all course names from the database
         * used to populate the drawer
         */
        drivers=db.query("SELECT * FROM tbl_license GROUP BY name");
        /**
         * find out if database contains any values
         */
        if (drivers.getCount() == 0){
            Log.d(TAG, "ERROR!");
        }

        /**
         * traverse through all the obtained values to find if one matches with our scanned licence
         */
        while(drivers.moveToNext()) {
            String stringed = drivers.getString(1);
            if(licence.equals(stringed)) {
                /**
                 * setting the report button visible
                 */
                isScanned = true;
                reportBtn.setVisibility(View.VISIBLE);

                driverName = drivers.getString(2);
                licenseNumber = drivers.getString(1);
                mlicense = stringed;

                //outcode_name.setText(drivers.getString(2));
                // outcode_address.setText(drivers.getString(3));
                // outcode_class.setText(drivers.getString(4));

            }

        }

        offences =db.query("SELECT * FROM tbl_offenses WHERE "+
                " license = "+mlicense+" ORDER BY id ASC");

        int count = offences.getCount();
        Log.d(TAG, "Count = " + count);

        String[] mdate = new String[count];
        String[] mtype = new String[count];

        if(count != 0) {

            int j = 0;
            while (offences.moveToNext()) {
                mdate[j] = offences.getString(4);
                mtype[j] = offences.getString(3);
                j++;
            }

            for (int i = 0; i < count; i++) {
                offences.moveToPosition(i);
                Log.d(TAG, "Offense found");
                gridViewAdapter = new GridViewAdapterOffence(this, mdate, mtype);
                historyGridView.setAdapter(gridViewAdapter);
            }

        }

    }

    /**
     * Async Task to check whether internet connection is working
     **/
    private class NetCheck extends AsyncTask< String, String, Boolean>
    {
        //private ProgressDialog nDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            verifyBtn.setVisibility(View.GONE);
//            nDialog = new ProgressDialog(MainActivity.this);
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

                histOne.setVisibility(View.VISIBLE);
                histTwo.setVisibility(View.GONE);

                progressBar.setVisibility(View.GONE);
                verifyBtn.setVisibility(View.VISIBLE);
                verifyBtn.bringToFront();

                //nDialog.dismiss();
                if(( !license.getText().toString().equals("")) && ( !plate_no.getText().toString().equals(""))){
                    historyDate1.setText("");
                    historyDate2.setText("");
                    historyDate3.setText("");
                    historyOffence1.setText("");
                    historyOffence2.setText("");
                    historyOffence3.setText("");
                    registerErrorMsg.setText("");
                    new ProcessVerification().execute();
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                progressBar.setVisibility(View.GONE);
                verifyBtn.setVisibility(View.VISIBLE);
                verifyBtn.bringToFront();
                registerErrorMsg.setText("Error in Network Connection");
                //should store the data in sql lite temporary until there will be network
            }
        }
    }

    /**
     *To process the data from the offense form
     */

    private class ProcessVerification extends AsyncTask <String, String, JSONObject> {
        /**
         * Defining Process dialog
         **/
        //private ProgressDialog pDialog;
        String input_plateNumber,input_license,input_license1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.bringToFront();
            verifyBtn.setVisibility(View.GONE);

            input_license = license.getText().toString();
            input_plateNumber = plate_no.getText().toString();



//            pDialog = new ProgressDialog(MainActivity.this);
//            pDialog.setTitle("Contacting Servers");
//            pDialog.setMessage(" fetching detail ...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            UserFunctions PFunction = new UserFunctions();
            JSONObject json = PFunction.carAndLicenceVerification( input_license, input_plateNumber);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            /**
             * starting with history
             */
            input_license1 = license.getText().toString();
            registerErrorMsg.setText("");

            try {
                UserFunctions History = new UserFunctions();
                JSONObject json1 = History.getHistory(input_license1);
                if (json1 != null && json1.getString(KEY_SUCCESS) != null){

                    registerErrorMsg.setText("");
                    String res1 = json1.getString(KEY_SUCCESS);
                    String red1 = json1.getString(KEY_ERROR);
                    String cont = json1.getString(KEY_COUNT);

                    if (!(cont.equals("0"))){
                        histOne.setVisibility(View.GONE);
                        histTwo.setVisibility(View.VISIBLE);
                    }

                    if(Integer.parseInt(res1) == 1){
                        has = true;
                        Log.d("qq",res1);
                        Log.d("qq",cont);
                        if (cont.equals("1")){
                            JSONObject json_history = json1.getJSONObject("history0");
                            historyOffence1.setText(json_history.getString(KEY_OFFENCE));
                            historyDate1.setText(json_history.getString(KEY_DATE));

                            historyDate2Title.setVisibility(View.GONE);
                            historyDate2.setVisibility(View.GONE);
                            historyOffence2Title.setVisibility(View.GONE);
                            historyOffence2.setVisibility(View.GONE);

                            historyDate3Title.setVisibility(View.GONE);
                            historyDate3.setVisibility(View.GONE);
                            historyOffence3Title.setVisibility(View.GONE);
                            historyOffence3.setVisibility(View.GONE);

                            stripOne.setVisibility(View.GONE);
                            stripTwo.setVisibility(View.GONE);
                        }
                        else if(cont.equals("2")){
                            JSONObject json_history1 = json1.getJSONObject("history1");
                            historyOffence2.setText(json_history1.getString(KEY_OFFENCE));
                            historyDate2.setText(json_history1.getString(KEY_DATE));

                            JSONObject json_history = json1.getJSONObject("history0");
                            historyOffence1.setText(json_history.getString(KEY_OFFENCE));
                            historyDate1.setText(json_history.getString(KEY_DATE));

                            historyDate3Title.setVisibility(View.GONE);
                            historyDate3.setVisibility(View.GONE);
                            historyOffence3Title.setVisibility(View.GONE);
                            historyOffence3.setVisibility(View.GONE);

                            stripTwo.setVisibility(View.GONE);
                        }
                        else if(cont.equals("3")){
                            JSONObject json_history2 = json1.getJSONObject("history2");
                            historyOffence3.setText(json_history2.getString(KEY_OFFENCE));
                            historyDate3.setText(json_history2.getString(KEY_DATE));

                            JSONObject json_history1 = json1.getJSONObject("history1");
                            historyOffence2.setText(json_history1.getString(KEY_OFFENCE));
                            historyDate2.setText(json_history1.getString(KEY_DATE));

                            JSONObject json_history = json1.getJSONObject("history0");
                            historyOffence1.setText(json_history.getString(KEY_OFFENCE));
                            historyDate1.setText(json_history.getString(KEY_DATE));
                        }

                    }
                    else if (Integer.parseInt(red1) ==1){
                        verifyBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        verifyBtn.bringToFront();
                        registerErrorMsg.setText("unrecognised license number");
                    }

                }
                else{
                    verifyBtn.bringToFront();
                    verifyBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    registerErrorMsg.setText("Error in getting history");
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

            /**
             * for verification
             */

            try {
                if (json != null && json.getString(KEY_SUCCESS) != null){
                    registerErrorMsg.setText("");
                    String res = json.getString(KEY_SUCCESS);
                    String red = json.getString(KEY_ERROR);




                    if(Integer.parseInt(res) == 1){
//                        pDialog.setTitle("Getting Details");
//                        pDialog.setMessage("Loading Info");
                        registerErrorMsg.setText("");

                        JSONObject json_detail = json.getJSONObject("detail");
                        /**
                         * capturing values for the VerifyCarActivity
                         **/
                        Intent verifyCar = new Intent(MainOffence.this, VerifyCarActivity.class);
                        verifyCar.putExtra("license", licenseNum);
                        verifyCar.putExtra("plateNo", plateNumber);
                        verifyCar.putExtra("type", json_detail.getString(KEY_TYPE));
                        verifyCar.putExtra("make", json_detail.getString(KEY_MAKE));
                        verifyCar.putExtra("color", json_detail.getString(KEY_COLOR));
                        verifyCar.putExtra("name", json_detail.getString(KEY_NAME));
                        verifyCar.putExtra("address", json_detail.getString(KEY_ADDRESS));
                        verifyCar.putExtra("class", json_detail.getString(KEY_CLASS));
                        verifyCar.putExtra("expiryDate", json_detail.getString(KEY_EXPIRY_DATE));
                        verifyCar.putExtra("status", json_detail.getString(KEY_STATUS));
                        verifyBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        verifyBtn.bringToFront();
                        MainOffence.this.startActivity(verifyCar);
                    }
                    else if (Integer.parseInt(red) ==1){
                        if (!has) {
                            histOne.setVisibility(View.VISIBLE);
                            histTwo.setVisibility(View.GONE);
                        }
                        verifyBtn.bringToFront();
                        verifyBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        registerErrorMsg.setText("Unrecognised plate number");
                    }
                    else if (Integer.parseInt(red) ==2){
                        if (!has) {
                            histOne.setVisibility(View.VISIBLE);
                            histTwo.setVisibility(View.GONE);
                        }
                        verifyBtn.bringToFront();
                        verifyBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        registerErrorMsg.setText("Unrecognised License number/plate Number");
                    }
                    else if (Integer.parseInt(red) ==3){
                        if (!has) {
                            histOne.setVisibility(View.VISIBLE);
                            histTwo.setVisibility(View.GONE);
                        }
                        verifyBtn.bringToFront();
                        verifyBtn.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        registerErrorMsg.setText("Unrecognised plate number & license");
                    }
                }
                else{
                    if (!has) {
                        histOne.setVisibility(View.VISIBLE);
                        histTwo.setVisibility(View.GONE);
                    }
                    verifyBtn.bringToFront();
                    verifyBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    registerErrorMsg.setText("Error in getting  data");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void NetAsync(View view){
        new NetCheck().execute();
    }

}

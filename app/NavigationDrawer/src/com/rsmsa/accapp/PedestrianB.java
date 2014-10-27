package com.rsmsa.accapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.rsmsa.accapp.library.DatabaseHandler;


/**
 * Created by martha shaka on 10/22/2014.
 */
public class PedestrianB extends Activity {

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

    /**
     * Defining our class variables
     */
    public String Name11;
    public String Gender11;
    public String DateOfBirth11;
    public String Address11;
    public String PhysicalAddress11;
    public String NationalId11;
    public String PhoneNo11;
    public String SeatbeltHelmet11;
    public String casuality11;
    public String drugs11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        setContentView(R.layout.pedestrian_b);

        /**
         * call the init() method to instantiate all our class variables
         */
        init();
    }


    /**
     * the method to instantiate all our class variables
     */
    public void init(){

        /**
         * getting instances of our view elements
         */
        okay_button = (Button)findViewById(R.id.pass_next_btn);

        ViewName = (EditText)findViewById(R.id.name_edit);
        ViewDateOfBirth = (EditText)findViewById(R.id.dob_one);
        ViewAddress = (EditText)findViewById(R.id.address_box_one);
        ViewPhysicalAddress = (EditText)findViewById(R.id.physical_address_one);
        ViewNationalId = (EditText)findViewById(R.id.national_id_one);
        ViewPhoneNo = (EditText)findViewById(R.id.phone_no_one);
        ViewDrugsAlcohol = (EditText)findViewById(R.id.nationality_one);


        final RadioButton Male = (RadioButton)findViewById(R.id.male);
        final RadioButton Female = (RadioButton)findViewById(R.id.female);
        final RadioButton Fatal = (RadioButton)findViewById(R.id.fatal);
        final RadioButton severe = (RadioButton)findViewById(R.id.severe);
        final RadioButton Light = (RadioButton)findViewById(R.id.light);

        /**
         * getting values of our view elements
         */


        Male.setChecked(true);
        Fatal.setChecked(true);

        Male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Female.setChecked(false);
                    Gender11="male";
                }
            }
        });

        Female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Male.setChecked(false);
                    Gender11="female";
                }
            }
        });
        Fatal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    severe.setChecked(false);
                    Light.setChecked(false);
                    casuality11="fatal";
                }
            }
        });
        severe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Fatal.setChecked(false);
                    Light.setChecked(false);
                    casuality11="severe";
                }
            }
        });
        Light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    Fatal.setChecked(false);
                    severe.setChecked(false);
                    casuality11="light";
                }
            }
        });


        Name11=ViewName.getText().toString();
        DateOfBirth11=ViewDateOfBirth.getText().toString();
        Address11=ViewAddress.getText().toString();
        PhysicalAddress11=ViewPhysicalAddress.getText().toString();
        NationalId11=ViewNationalId.getText().toString();
        PhoneNo11=ViewPhoneNo.getText().toString();
        drugs11=ViewDrugsAlcohol.getText().toString();




        /**
         * checking for null values of our entered data from view elements
         */

        if(Name11=="") {
            Name11 ="No";
        }
        if(DateOfBirth11=="") {
            DateOfBirth11 ="No";
        }
        if(Address11=="") {
            Address11 ="No";
        }
        if(PhysicalAddress11=="") {
            PhysicalAddress11 ="No";
        }
        if(NationalId11=="") {
            NationalId11 ="No";
        }
        if(PhoneNo11=="") {
            PhoneNo11 ="No";
        }
        if(drugs11=="") {
            drugs11 ="No";
        }



        okay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Overiding h to return back results to the previous activity
     */
    @Override
    public void finish(){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        String name=Name11;
        String gender=Gender11;
        String dob=DateOfBirth11;
        String physical_address=PhysicalAddress11;
        String address_box=Address11;
        String nationality_id=NationalId11;
        String phone_no=PhoneNo11;
        String casuality=casuality11;
        String alcohol=drugs11;
        String seat_helmet=SeatbeltHelmet11;
        String vehicle_no="12";
        String status="pedestrian";
        String ary[];

        db.addPerson( name, gender,  dob,  physical_address,  address_box, nationality_id, phone_no,
                casuality, alcohol , seat_helmet,  vehicle_no, status);



        Intent returnIntent=new Intent();
        setResult(RESULT_OK,returnIntent);
        super.finish();
    }


}

///**
// * Async Task to check whether internet connection is working.
// **/
//public class NetCheck extends AsyncTask<String, Void, Boolean>
//{
//    private ProgressDialog nDialog;
//    @Override
//    protected void onPreExecute(){
//        super.onPreExecute();
//        nDialog = new ProgressDialog(OnePassengerOne);
//        nDialog.setTitle("Checking Network");
////        nDialog.setMessage("Loading..");
////        nDialog.setIndeterminate(false);
////        nDialog.setCancelable(true);
////        nDialog.show();
//    }
//    @Override
//    protected Boolean doInBackground(String... args){
///**
// * Gets current device state and checks for working internet connection by trying Google.
// **/
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnected()) {
//            try {
//                URL url = new URL("http://www.google.com");
//                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//                urlc.setConnectTimeout(3000);
//                urlc.connect();
//                if (urlc.getResponseCode() == 200) {
//                    return true;
//                }
//            } catch (MalformedURLException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//    @Override
//    protected void onPostExecute(Boolean th){
//        if(th == true){
//            nDialog.dismiss();
//            new ProcessPerson().execute();
//        }
//        else{
//            nDialog.dismiss();
//           // error.setText("Error in Network Connection");
//        }
//    }
//}
//
///**
// * Async Task to get and send data to MySql database through JSON response.
// **/
//private class ProcessPerson extends AsyncTask<String, Void, JSONObject> {
//    private ProgressDialog pDialog = new ProgressDialog(OnePassengerOne.this);
//    String User,Pword;
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//
//        pDialog.setTitle("Contacting Servers");
//        pDialog.setMessage("verifying user ...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(true);
//        pDialog.show();
//    }
//    @Override
//    protected JSONObject doInBackground(String... args) {
//        UserFunctions pFunction = new UserFunctions();
//        JSONObject json = pFunction.addPerson(User, Pword);
//        return json;
//    }
//    @Override
//    protected void onPostExecute(JSONObject json) {
//        try {
//
//
//
//            if (json != null && json.getString(KEY_SUCCESS) != null){
//                String res = json.getString(KEY_SUCCESS);
//                if(Integer.parseInt(res) == 1){
//
//                    /**
//                     * adding user information to shared preferences
//                     */
//
//
//                    pDialog.setMessage("Loading User Space");
//                    pDialog.setTitle("Getting Data");
//                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//                    DatabaseHandler db1 = new DatabaseHandler(getApplicationContext());
//                    JSONObject json_user = json.getJSONObject("user");
//                    /**
//                     * Clear all previous data in SQlite database.
//                     **/
//                    UserFunctions logout = new UserFunctions();
//                    logout.logoutUser(getApplicationContext());
//                    db.addUser(json_user.getString(KEY_USERNAME),json_user.getString(KEY_FULLNAME),
//                            json_user.getString(KEY_STATION),json_user.getString(KEY_CREATED_AT));
//
//
//                    /**
//                     * create the offenceNature on the database
//                     */
//                    AndroidXMLParsingActivity object = new AndroidXMLParsingActivity();
//
//                    object.createOffence(db1);
//
//                    /**
//                     *If JSON array details are stored in SQlite it launches the User Panel.
//                     **/
//                    Intent upanel = new Intent(getApplicationContext(), MainActivity.class);
//                    upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    pDialog.dismiss();
//                    startActivity(upanel);
//                    /**
//                     * Close Login Screen
//                     *
//                     *  finish();
//                     */
//                }else{
//                    pDialog.dismiss();
//                    error.setText("Incorrect username/password");
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        sharedpreferences=getSharedPreferences(MyPREFERENCES,
//                Context.MODE_PRIVATE);
//        if (sharedpreferences.contains(name))
//        {
//            if(sharedpreferences.contains(pass)){
//                if(justBack == true)
//                {
//                    finish();
//                    justBack = false;
//                }
//                else {
//                    Intent i = new Intent(this, MainActivity.class);
//                    startActivity(i);
//                }
//
//            }
//        }
//        super.onResume();
//    }
//
//    public void NetAsync(View view){
//
//        new NetCheck().execute();
//    }
//
//
//}





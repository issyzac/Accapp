package com.rsmsa.accapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsmsa.accapp.library.DatabaseHandler;

import customviews.TouchFeedbackEnabledRelativeLayout;


/**
 * Created by martha shaka on 10/22/2014.
 */
public class Atc extends Activity {




    /**
     * defining the instances of our view elements
     */

    public Button okay_button;
    public Spinner AccidentTypeSpinner;
    public Spinner JunctionStructureSpinner;
    public Spinner JunctionControlSpinner;
    public Spinner RoadTypeSpinner;
    public Spinner SurfaceTypeSpinner;
    public Spinner RoadStructureSpinner;
    public Spinner SurfaceStatusSpinner;
    public Spinner RoadSurfaceSpinner;
    public Spinner LightSpinner;
    public Spinner WeatherSpinner;
    public Spinner ControlSpinner;
    public Spinner ViolationVehicleOneSpinner;
    public Spinner ViolationVehicleTwoSpinner;
    public Spinner VehicleOneDefectsSpinner;
    public Spinner VehicleTwoDefectsSpinner;


    /**
     * Defining our class variables
     */
    public String AccidentType;
    public String JunctionStructure;
    public String JunctionControl;
    public String RoadType;
    public String SurfaceType;
    public String RoadStructure;
    public String SurfaceStatus;
    public String Light;
    public String Weather;
    public String Control;
    public String ViolationVehicleOne;
    public String ViolationVehicleTwo;
    public String VehicleOneDefects;
    public String VehicleTwoDefects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        setContentView(R.layout.atc);



        /**
         * call the init() method to instantiate all our class variables
         */
     //   init();
    }


//    /**
//     * the method to instantiate all our class variables
//     */
//    public void init(){
//
//        /**
//         * getting instances of our view elements
//         */
//        okay_button = (Button)findViewById(R.id.pass_next_btn);
//
//        ViewName = (EditText)findViewById(R.id.name_edit);
//        ViewDateOfBirth = (EditText)findViewById(R.id.dob_one);
//        ViewAddress = (EditText)findViewById(R.id.address_box_one);
//        ViewPhysicalAddress = (EditText)findViewById(R.id.physical_address_one);
//        ViewNationalId = (EditText)findViewById(R.id.national_id_one);
//        ViewPhoneNo = (EditText)findViewById(R.id.phone_no_one);
//
//        ViewSeatbeltHelmet = (CheckBox)findViewById(R.id.seat_belt_check);
//
//        final RadioButton Male = (RadioButton)findViewById(R.id.male);
//        final RadioButton Female = (RadioButton)findViewById(R.id.female);
//
//
//        /**
//         * getting values of our view elements
//         */
//
//
//        Male.setChecked(true);
//
//
//        Male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b == true){
//                    Female.setChecked(false);
//                    Gender11="male";
//                }
//            }
//        });
//
//        Female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b == true){
//                    Male.setChecked(false);
//                    Gender11="female";
//                }
//            }
//        });
//
//
//
//        Name11=ViewName.getText().toString();
//        DateOfBirth11=ViewDateOfBirth.getText().toString();
//        Address11=ViewAddress.getText().toString();
//        PhysicalAddress11=ViewPhysicalAddress.getText().toString();
//        NationalId11=ViewNationalId.getText().toString();
//        PhoneNo11=ViewPhoneNo.getText().toString();
//
//        SeatbeltHelmet11=ViewSeatbeltHelmet.getText().toString();
//
//
//
//        /**
//         * checking for null values of our entered data from view elements
//         */
//
//        if(Name11=="") {
//            Name11 ="No";
//        }
//        if(DateOfBirth11=="") {
//            DateOfBirth11 ="No";
//        }
//        if(Address11=="") {
//            Address11 ="No";
//        }
//        if(PhysicalAddress11=="") {
//            PhysicalAddress11 ="No";
//        }
//        if(NationalId11=="") {
//            NationalId11 ="No";
//        }
//        if(PhoneNo11=="") {
//            PhoneNo11 ="No";
//        }
//
//        if(SeatbeltHelmet11=="") {
//            SeatbeltHelmet11 ="No";
//        }
//
//
//        okay_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }

    /**
     * Overiding h to return back results to the previous activity
//     */
//    @Override
//    public void finish(){
//        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//        String name=Name11;
//        String gender=Gender11;
//        String dob=DateOfBirth11;
//        String physical_address=PhysicalAddress11;
//        String address_box=Address11;
//        String nationality_id=NationalId11;
//        String phone_no=PhoneNo11;
//        String casuality=casuality11;
//        String alcohol=drugs11;
//        String seat_helmet=SeatbeltHelmet11;
//        String vehicle_no="12";
//        String status="witness";
//        String ary[];
//
//        db.addPerson( name, gender,  dob,  physical_address,  address_box, nationality_id, phone_no,
//                casuality, alcohol , seat_helmet,  vehicle_no, status);
//
//
//
//        Intent returnIntent=new Intent();
//        setResult(RESULT_OK,returnIntent);
//        super.finish();
//    }

}

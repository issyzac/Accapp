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
public class Witness extends Activity {

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


        setContentView(R.layout.witness);

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

        ViewSeatbeltHelmet = (CheckBox)findViewById(R.id.seat_belt_check);

        final RadioButton Male = (RadioButton)findViewById(R.id.male);
        final RadioButton Female = (RadioButton)findViewById(R.id.female);


        /**
         * getting values of our view elements
         */


        Male.setChecked(true);


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



        Name11=ViewName.getText().toString();
        DateOfBirth11=ViewDateOfBirth.getText().toString();
        Address11=ViewAddress.getText().toString();
        PhysicalAddress11=ViewPhysicalAddress.getText().toString();
        NationalId11=ViewNationalId.getText().toString();
        PhoneNo11=ViewPhoneNo.getText().toString();

        SeatbeltHelmet11=ViewSeatbeltHelmet.getText().toString();



        /**
         * checking for null values of our entered data from view elements
         */



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



        Intent returnIntent=new Intent();
        setResult(RESULT_OK,returnIntent);
        super.finish();
    }


}

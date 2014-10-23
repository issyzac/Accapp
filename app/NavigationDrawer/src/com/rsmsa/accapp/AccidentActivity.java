package com.rsmsa.accapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rsmsa.accapp.library.DatabaseHandler;
import com.rsmsa.accapp.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PETER on 10/21/2014.
 */
public class AccidentActivity extends Activity {

    /**
     * JSON Response node names.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_FATAL = "fatal";
    private static String KEY_INJURY = "injury";
    private static String KEY_SIMPLE = "simple";
    private static String KEY_DAMAGE = "surname";
    private static String KEY_DATE= "other_names";
    private static String KEY_TIME = "physical_address";
    private static String KEY_AREA_NAME = "box_address";
    private static String KEY_DISTRICT = "national_id";
    private static String KEY_CITY = "phone_no";
    private static String KEY_ROAD_NAME = "gender";
    private static String KEY_ROAD_NUMBER = "dob";
    private static String KEY_ROAD_MARK = "nationality";
    private static String KEY_INTERSECTION_NAME= "license";
    private static String KEY_INTERSECTION_NO = "occupation";
    private static String KEY_INTERSECTION_MARK = "drug";
    private static String KEY_CREATED_AT = "created_at";



    /**
     * Defining layout items.
     */
    EditText inputFatal;
    EditText inputInjuries;
    EditText inputSimple;
    EditText damage;
    EditText date;
    EditText time;
    EditText area_name;
    EditText district;
    EditText city;
    EditText region;
    EditText road_name;
    EditText road_no;
    EditText road_mark;
    EditText intersection_name;
    EditText intersection_no;
    EditText intersection_mark;




    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);
        /**
         * Defining all layout items
         **/

        inputFatal = (EditText) findViewById(R.id.fatal_edit);
        inputInjuries = (EditText) findViewById(R.id.injury_edit);
        inputSimple = (EditText) findViewById(R.id.simple_edit);
        damage = (EditText) findViewById(R.id.not_injured_edit);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        area_name = (EditText) findViewById(R.id.name);
        district = (EditText) findViewById(R.id.district);
        city = (EditText) findViewById(R.id.city);
        region = (EditText) findViewById(R.id.city);
        road_name = (EditText) findViewById(R.id.road_name);
        road_no = (EditText) findViewById(R.id.road_number);
        road_mark = (EditText) findViewById(R.id.road_mark);
        intersection_name = (EditText) findViewById(R.id.intersection_name);
        intersection_no = (EditText) findViewById(R.id.intersection_name);
        intersection_mark = (EditText) findViewById(R.id.intersection_mark);

    }




}


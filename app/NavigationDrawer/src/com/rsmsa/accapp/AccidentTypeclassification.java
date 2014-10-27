package com.rsmsa.accapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.SpinnerAdapter;

/**
 *  Created by isaiah on 10/22/2014.
 */
public class AccidentTypeclassification extends Activity {

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
    public SpinnerAdapter dataAdapter;

    ImageView scroller;

    public TextView sthinSelected;

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
                Intent passOne = new Intent(AccidentTypeclassification.this, OnePassengerOne.class);
                startActivity(passOne);
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

    @Override
    public void onResume(){
        super.onResume();
            sthinSelected.setVisibility(View.VISIBLE);
            sthinSelected.setText("112 Selected");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPORT_RESULT) {
            if (resultCode == RESULT_OK) {
                // code for result
                final Bundle bundle = data.getExtras();
                Log.d("resulty", bundle.getString("item")+"############");
                positionSelected = bundle.getString("item");
                sthinSelected.setVisibility(View.VISIBLE);
                sthinSelected.setText("112 Selected");
            }
            if (resultCode == RESULT_CANCELED) {
                // Write your code on no result return
            }
        }
    }


}

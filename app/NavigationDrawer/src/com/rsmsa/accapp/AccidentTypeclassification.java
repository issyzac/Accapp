package com.rsmsa.accapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 *  Created by isaiah on 10/22/2014.
 */
public class AccidentTypeclassification extends Activity {

    /**
     * Select buttons
     */
    Button accidentTypeSelectButton;

    Button finishButton;

    /**
     *
     * Accident type classification spinner
     */
    Spinner atcSpinner;
    Spinner junctionStructureSpinner;
    Spinner junctionControlSpinner;
    Spinner roadTypeSpinner;
    Spinner surfaceTypeSpinner;
    Spinner roadStructureSpinner;
    Spinner surfaceStatusSpinner;
    Spinner roadSurfaceSpinner;
    Spinner lightSpinner;
    Spinner whetherSpinner;
    Spinner controlSpinner;
    Spinner violationOneSpinner;
    Spinner violationTwoSpinner;
    Spinner defectOneSpinner;
    Spinner defectTwoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atc);

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
        junctionStructureSpinner = (Spinner) findViewById(R.id.atc_spinner);
        junctionControlSpinner = (Spinner) findViewById(R.id.atc_spinner);
        roadTypeSpinner = (Spinner) findViewById(R.id.atc_spinner);
        surfaceTypeSpinner = (Spinner) findViewById(R.id.atc_spinner);
        roadStructureSpinner = (Spinner) findViewById(R.id.atc_spinner);
        surfaceStatusSpinner = (Spinner) findViewById(R.id.atc_spinner);
        roadSurfaceSpinner = (Spinner) findViewById(R.id.atc_spinner);
        lightSpinner = (Spinner) findViewById(R.id.atc_spinner);
        whetherSpinner = (Spinner) findViewById(R.id.atc_spinner);
        controlSpinner = (Spinner) findViewById(R.id.atc_spinner);
        violationOneSpinner = (Spinner) findViewById(R.id.atc_spinner);
        violationTwoSpinner = (Spinner) findViewById(R.id.atc_spinner);
        defectOneSpinner = (Spinner) findViewById(R.id.atc_spinner);
        defectTwoSpinner = (Spinner) findViewById(R.id.atc_spinner);


        List<String> junctionStructure = new ArrayList<String>();
        junctionStructure.add("Crossing Roads");
        junctionStructure.add("Round About");
        junctionStructure.add("T Junction");
        junctionStructure.add("Y Junction");
        junctionStructure.add("Staggered Junction");
        junctionStructure.add("Junc > 4 Arms");
        junctionStructure.add("Bridge/Fly over");
        junctionStructure.add("Rail Cros Manned");
        junctionStructure.add("Rail Cros no Sign");
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

        List<String> list = new ArrayList<String>();
        list.add("Single vehicle accident");
        list.add("Accidents between vehicles driving same travel direction (2 or more vehicles)");
        list.add("Accidents between vehicles driving opposite travel direction (2 or more vehicles)");
        list.add("Accidents at a junction turning in same or opposite direction (2 or more vehi.)");
        list.add("Collision at a junction between two or more participants");
        list.add("Accident w. parked vehicles");
        list.add("Pedestrian, animals and other accidents");

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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        atcSpinner.setAdapter(dataAdapter);

        dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,junctionStructure);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        junctionStructureSpinner.setAdapter(dataAdapter);

//        ArrayAdapter<String> junctionControlAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,junctionControl);
//        junctionControlAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        junctionControlSpinner.setAdapter(junctionControlAdapter);
//
//        ArrayAdapter<String> roadTypeAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,roadClass);
//        roadTypeAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        roadTypeSpinner.setAdapter(roadTypeAdapter);
//
//        ArrayAdapter<String> surfaceTypeAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,surfaceType);
//        surfaceTypeAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        surfaceTypeSpinner.setAdapter(surfaceTypeAdapter);
//
//        ArrayAdapter<String> roadStructureAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,roadStructure);
//        roadStructureAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        roadStructureSpinner.setAdapter(roadStructureAdapter);
//
//        ArrayAdapter<String> surfaceStatusAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,roadStatus);
//        surfaceStatusAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        surfaceStatusSpinner.setAdapter(surfaceStatusAdapter);
//
//        ArrayAdapter<String> roadSurfaceAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,roadSurface);
//        roadSurfaceAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        roadSurfaceSpinner.setAdapter(roadSurfaceAdapter);
//
//        ArrayAdapter<String> lightAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,light);
//        lightAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        lightSpinner.setAdapter(lightAdapter);
//
//        ArrayAdapter<String> weatherAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,weather);
//        weatherAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        whetherSpinner.setAdapter(weatherAdapter);
//
//        ArrayAdapter<String> controlAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,control);
//        controlAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        controlSpinner.setAdapter(controlAdapter);
//
//        ArrayAdapter<String> violationOneAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,violation);
//        violationOneAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        violationOneSpinner.setAdapter(violationOneAdapter);
//
//        ArrayAdapter<String> violationTwoAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,violation);
//        violationTwoAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        violationTwoSpinner.setAdapter(violationTwoAdapter);
//
//        ArrayAdapter<String> defectOneAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,defects);
//        defectOneAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        defectOneSpinner.setAdapter(defectOneAdapter);
//
//        ArrayAdapter<String> defectTwoAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_spinner_item,defects);
//        defectTwoAdapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        defectTwoSpinner.setAdapter(defectTwoAdapter);

        accidentTypeSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atcselectintent = new Intent(AccidentTypeclassification.this, AtcSelect.class);
                atcselectintent.putExtra("classification", atcSpinner.getSelectedItemId()+"");
                Log.d("selected", atcSpinner.getSelectedItem()+"");
                startActivity(atcselectintent);
            }
        });



    }
}

package com.rsmsa.accapp;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

import java.sql.DataTruncation;

import adapters.ATCgridAdapter;

/**
 *  Created by isaiah on 10/28/2014.
 */
public class VehicleType extends Activity {

    public String[] vt1 = {"Private Car","Foreign Car","Priv. Motorcycle","Priv. Pedalcycle","Pick Up","Private Truck","3 Wheeler","Guta","Mkokoteni"};
    public String[] vt1No = {"100","101","102","103","104","105","106","107","108"};

    public String[] vt2 = {"Truck","HDV/Semi Trailer","Truck & Trailer","Foreign Truck","Dangerous Goods","Abnormal Loads","Abnormal Dimension","Motorcycle","Tractor"};
    public String[] vt2No = {"200","201","202","203","204","205","206","207","208"};

    public String[] vt3 = {"Government Vehicle","Defence Force","Prison Vehicle","National Service","Police","Police Motorcycle","Dilplomatic Vehicles"};
    public String[] vt3No = {"300","301","302","303","304","305","306"};

    public String[] vt4 = {"Fire Brigade","Ambulance","Rescue Service"};
    public String[] vt4No = {"400","401","402"};

    public String[] vt5 = {"PSV Motorcycle","PSV 3 Wheeler","PSV Dtaxi Cab","PSV Daladala","Private Bus","PSV Bus","Foreign Bus"};
    public String[] vt5No = {"500","501","502","503","504","505","506"};

    private String SelectedItem;

    public GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        setContentView(R.layout.vehicle_type);

        final Bundle bundle=getIntent().getExtras();
        SelectedItem = bundle.getString("category");

        gridView = (GridView)findViewById(R.id.vehicle_type_grid);

        if (SelectedItem.equals("0")){
            TypedArray imgs = getResources().obtainTypedArray(R.array.vtOne);
            gridView.setAdapter(new ATCgridAdapter(this, vt1, vt1No, imgs));
        }
        else if (SelectedItem.equals("1")){
            TypedArray imgs = getResources().obtainTypedArray(R.array.vtTwo);
            gridView.setAdapter(new ATCgridAdapter(this, vt2, vt2No, imgs));
        }
        else if (SelectedItem.equals("2")){
            TypedArray imgs = getResources().obtainTypedArray(R.array.vtThree);
            gridView.setAdapter(new ATCgridAdapter(this, vt3, vt3No, imgs));
        }
        else if (SelectedItem.equals("3")){
            TypedArray imgs = getResources().obtainTypedArray(R.array.vtFour);
            gridView.setAdapter(new ATCgridAdapter(this, vt4, vt4No, imgs));
        }
        else{
            TypedArray imgs = getResources().obtainTypedArray(R.array.vtFive);
            gridView.setAdapter(new ATCgridAdapter(this, vt5, vt5No, imgs));
        }
    }

}

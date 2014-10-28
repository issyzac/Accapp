package com.rsmsa.accapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.CheckBox;
import android.widget.EditText;

import Fragments.OtherOne;
import Fragments.OtherTwo;
import Fragments.PedestrianA;
import Fragments.PedestrianB;
import Fragments.PedestrianC;
import Fragments.VehicleOnePassengerOneScreenSlide;
import Fragments.VehicleOnePassengerThree;
import Fragments.VehicleOnePassengerTwo;
import Fragments.VehicleTwoPassengerOne;
import Fragments.VehicleTwoPassengerThree;
import Fragments.VehicleTwoPassengerTwo;
import Fragments.Witness;
import customviews.AutoScrollViewPager;
import transformers.DepthPageTransformer;

/**
 * Created by isaiah on 10/15/2014.
 */
public class Passenger extends FragmentActivity{

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 6;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    public static AutoScrollViewPager mPager;

    public static AutoScrollViewPager pPager;
    /**
     * Defining our class variables
     */
    public static String Name11;
    public static String Gender11;
    public static String DateOfBirth11;
    public static String Address11;
    public static String PhysicalAddress11;
    public static String NationalId11;
    public static String PhoneNo11;
    public static String SeatbeltHelmet11;
    public static String casuality11;
    public static String drugs11;

    public static String Name12;
    public static String Gender12;
    public static String DateOfBirth12;
    public static String Address12;
    public static String PhysicalAddress12;
    public static String NationalId12;
    public static String PhoneNo12;
    public static String SeatbeltHelmet12;
    public static String casuality12;
    public static String drugs12;

    public static String Name13;
    public static String Gender13;
    public static String DateOfBirth13;
    public static String Address13;
    public static String PhysicalAddress13;
    public static String NationalId13;
    public static String PhoneNo13;
    public static String SeatbeltHelmet13;
    public static String casuality13;
    public static String drugs13;

    public static String Name21;
    public static String Gender21;
    public static String DateOfBirth21;
    public static String Address21;
    public static String PhysicalAddress21;
    public static String NationalId21;
    public static String PhoneNo21;
    public static String SeatbeltHelmet21;
    public static String casuality21;
    public static String drugs21;

    public static String Name22;
    public static String Gender22;
    public static String DateOfBirth22;
    public static String Address22;
    public static String PhysicalAddress22;
    public static String NationalId22;
    public static String PhoneNo22;
    public static String SeatbeltHelmet22;
    public static String casuality22;
    public static String drugs22;

    public static String Name23;
    public static String Gender23;
    public static String DateOfBirth23;
    public static String Address23;
    public static String PhysicalAddress23;
    public static String NationalId23;
    public static String PhoneNo23;
    public static String SeatbeltHelmet23;
    public static String casuality23;
    public static String drugs23;

    public static String NameA;
    public static String GenderA;
    public static String DateOfBirthA;
    public static String AddressA;
    public static String PhysicalAddressA;
    public static String NationalIdA;
    public static String PhoneNoA;
    public static String casualityA;
    public static String drugsA;

    public static String NameB;
    public static String GenderB;
    public static String DateOfBirthB;
    public static String AddressB;
    public static String PhysicalAddressB;
    public static String NationalIdB;
    public static String PhoneNoB;
    public static String casualityB;
    public static String drugsB;

    public static String NameC;
    public static String GenderC;
    public static String DateOfBirthC;
    public static String AddressC;
    public static String PhysicalAddressC;
    public static String NationalIdC;
    public static String PhoneNoC;
    public static String casualityC;
    public static String drugsC;

    public static String NameD;
    public static String GenderD;
    public static String DateOfBirthD;
    public static String AddressD;
    public static String PhysicalAddressD;
    public static String NationalIdD;
    public static String PhoneNoD;
    public static String casualityD;
    public static String drugsD;

    public static String NameE;
    public static String GenderE;
    public static String DateOfBirthE;
    public static String AddressE;
    public static String PhysicalAddressE;
    public static String NationalIdE;
    public static String PhoneNoE;
    public static String casualityE;
    public static String drugsE;

    public static String NameF;
    public static String GenderF;
    public static String DateOfBirthF;
    public static String AddressF;
    public static String PhysicalAddressF;
    public static String NationalIdF;
    public static String PhoneNoF;




    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private PagerAdapter pPagerAdapter;
    /**
     * Value holding the current page
     */
    public static int currentPage;

    public Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);


        /**
         * instantiate Passenger ViewPager
         */
        mPager = (AutoScrollViewPager) findViewById(R.id.passenger_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setAdapter(mPagerAdapter);

        /**
         * instantiate Pedestrian ViewPager
         */
        pPager = (AutoScrollViewPager) findViewById(R.id.pedestrian_pager);
        pPagerAdapter = new PedestrianScreenSlidePagerAdapter(getSupportFragmentManager());
        pPager.setPageTransformer(true, new DepthPageTransformer());
        pPager.setAdapter(pPagerAdapter);

        nextBtn = (Button)findViewById(R.id.pass_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent atc = new Intent(Passenger.this, AccidentTypeclassification.class);
                startActivity(atc);
            }
        });

    }



    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0){
                return new VehicleOnePassengerOneScreenSlide();
            }
            else if(position == 1){
                return new VehicleOnePassengerTwo();
            }
            else if(position == 2){
                return new VehicleOnePassengerThree();
            }
            else if(position == 3){
                return new VehicleTwoPassengerOne();
            }
            else if(position == 4){
                return new VehicleTwoPassengerTwo();
            }
            else{
                return new VehicleTwoPassengerThree();
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class PedestrianScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public PedestrianScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0){
                return new PedestrianA();
            }
            else if(position == 1){
                return new PedestrianB();
            }
            else if(position == 2){
                return new PedestrianC();
            }
            else if(position == 3){
                return new Witness();
            }
            else if(position == 4){
                return new OtherOne();
            }
            else{
                return new OtherTwo();
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

}

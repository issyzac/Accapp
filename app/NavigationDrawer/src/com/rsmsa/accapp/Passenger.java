package com.rsmsa.accapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import Fragments.VehicleOnePassengerOneScreenSlide;
import Fragments.VehicleOnePassengerThree;
import Fragments.VehicleOnePassengerTwo;
import Fragments.VehicleTwoPassengerOne;
import Fragments.VehicleTwoPassengerThree;
import Fragments.VehicleTwoPassengerTwo;
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

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    /**
     * Value holding the current page
     */
    public static int currentPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);

        /**
         * instantiate ViewPaget
         */
        mPager = (AutoScrollViewPager) findViewById(R.id.passenger_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setAdapter(mPagerAdapter);



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

}

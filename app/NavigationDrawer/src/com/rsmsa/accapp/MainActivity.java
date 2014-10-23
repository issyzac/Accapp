/*******************************************************************************
 * Copyright 2013 Gabriele Mariotti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.rsmsa.accapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import adapters.gridViewAdapter;
import customviews.AutoScrollViewPager;
import customviews.HeaderGridView;
import transformers.DepthPageTransformer;
import transformers.ZoomOutPageTransformer;

public class MainActivity extends FragmentActivity {

	private ListView mDrawerList;
	private DrawerLayout mDrawer;
    public ListView gridView;
	private CustomActionBarDrawerToggle mDrawerToggle;
	private String[] menuItems;
    public static boolean hasheader;

    /**
     * next button instance
     */
    public static Button nextButton;

    /**
     *
     * header declarations
     */
    public View header;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

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

    /**
     *
     * date and time pickers
     *
     */
    public Button datePicker;

    public Button timePicker;

    private Calendar cal;

    private int min;

    private int hour;

    private int day;

    private int month;

    private int year;

    public EditText mDate;

    public EditText mTime;



    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_drawer);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);


		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        /**
         * header instance
         */
        header = (View)getLayoutInflater().inflate(R.layout.activity_accident,null);

        mDate = (EditText)header.findViewById(R.id.date);

        mTime = (EditText)header.findViewById(R.id.time);

        cal = Calendar.getInstance();

        day = cal.get(Calendar.DAY_OF_MONTH);

        month = cal.get(Calendar.MONTH);

        year = cal.get(Calendar.YEAR);

        hour = cal.get(Calendar.HOUR_OF_DAY);

        min = cal.get(Calendar.MINUTE);

        datePicker = (Button)header.findViewById(R.id.date_picker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this ,datePickerListener,year, month, day);
                datePickerDialog.show();

            }
        });



        timePicker= (Button)header.findViewById(R.id.time_picker);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, timePickerListener, hour, min, true);
                timePickerDialog.show();
            }
        });

        // set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		_initMenu();

        mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawer);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passenger = new Intent(MainActivity.this, Passenger.class);
                Intent vehicle1 = new Intent(getApplicationContext(), Vehicle1.class);
                Intent vehicle2 = new Intent(getApplicationContext(), Vehicle2.class);
                Intent accident = new Intent(getApplicationContext(), AccidentActivity.class);
                startActivity(vehicle1);
               // startActivity(vehicle2);
               // startActivity(accident);
                //startActivity(passenger);
            }
        });
        mDrawer.setDrawerListener(mDrawerToggle);

        /**
         * instantiate ViewPaget
         */
        mPager = (AutoScrollViewPager) header.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setAdapter(mPagerAdapter);

        PageListener pageListener = new PageListener();
        mPager.setOnPageChangeListener(pageListener);



    }

    /**
     * Date change listender
     */
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mDate.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };

    /**
     * Time Change Listener
     */
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int hour;
            String am_pm;
            if (hourOfDay > 12) {
                hour = hourOfDay - 12;
                am_pm = "PM";
            } else {
                hour = hourOfDay;
                am_pm = "AM";
            }
            mTime.setText(hour + " : " + minute + " " + am_pm);
        }
    };

	private void _initMenu() {

        /**
         * instantiate next button
         */
        nextButton = (Button) header.findViewById(R.id.next_button);
        nextButton.setVisibility(View.GONE);


        hasheader = false;
		NsMenuAdapter mAdapter = new NsMenuAdapter(this);

		// Add Header
		//mAdapter.addHeader(R.string.ns_menu_main_header);

		// Add first block

		menuItems = getResources().getStringArray(
				R.array.ns_menu_items);
		String[] menuItemsIcon = getResources().getStringArray(
				R.array.ns_menu_items_icon);

		int res = 0;
		for (String item : menuItems) {

			int id_title = getResources().getIdentifier(item, "string",
					this.getPackageName());
			int id_icon = getResources().getIdentifier(menuItemsIcon[res],
					"drawable", this.getPackageName());

			NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
			//if (res==1) mItem.counter=12; //it is just an example...
			//if (res==3) mItem.counter=3; //it is just an example...
			mAdapter.addItem(mItem);
			res++;
		}

		//mAdapter.addHeader(R.string.ns_menu_main_header2);

		mDrawerList = (ListView) findViewById(R.id.drawer);
		if (mDrawerList != null)
			mDrawerList.setAdapter(mAdapter);


		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
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
        boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_save).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar home/up should open or close the drawer.
		 * ActionBarDrawerToggle will take care of this.
		 */
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }


    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

		public CustomActionBarDrawerToggle(Activity mActivity,DrawerLayout mDrawerLayout){
			super(
			    mActivity,
			    mDrawerLayout, 
			    R.drawable.ic_drawer,
			    R.string.ns_menu_open, 
			    R.string.ns_menu_close);
		}

		@Override
		public void onDrawerClosed(View view) {
			getActionBar().setTitle(getString(R.string.ns_menu_close));
			invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			getActionBar().setTitle(getString(R.string.ns_menu_open));
			invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		}
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

            if (position == 0){


                if(hasheader) {
                    gridView.removeHeaderView(header);
                    Log.d("headercheck", "yes there is header " + hasheader);
                }
                /**
                 * instance of a gridview
                 */
                gridView = (ListView) findViewById(R.id.headergridview);

                mDrawer.closeDrawer(mDrawerList);

                gridView.setAdapter(null);

                gridView.addHeaderView(header);

                MainActivity.hasheader = true;

                gridView.setAdapter(new gridViewAdapter(MainActivity.this));
            }

			// Highlight the selected item, update the title, and close the drawer
			// update selected item and title, then close the drawer
	        mDrawerList.setItemChecked(position, true);
            if (position == 1) {

                if(hasheader) {
                    gridView.removeHeaderView(header);
                    Log.d("headercheck", "yes there is header two " + hasheader);
                }

                String text = "menu click... should be implemented";
                //You should reset item counter
                mDrawer.closeDrawer(mDrawerList);
            }
			
		}
		
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
                return new ScreenSlidePageFragment();
            }
            else{
                return new ScreenSlidePageFragmentTwo();
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

    private static class PageListener extends ViewPager.SimpleOnPageChangeListener {
        public void onPageSelected(int position) {
            Log.i("vp", "page selected " + position);
            if(position == 0){
                nextButton.setVisibility(View.GONE);
            }
            else{
                nextButton.setVisibility(View.VISIBLE);
            }
            currentPage = position;
        }
    }


}

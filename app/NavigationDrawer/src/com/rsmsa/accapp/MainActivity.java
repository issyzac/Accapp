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
import android.net.Uri;
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
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;


import com.rsmsa.accapp.library.DatabaseHandler;

import org.json.JSONObject;

import java.util.Calendar;

import adapters.gridViewAdapter;
import customviews.AutoScrollViewPager;
import customviews.HeaderGridView;
import transformers.DepthPageTransformer;
import transformers.ZoomOutPageTransformer;

public class MainActivity extends FragmentActivity {

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "AccidentFile";

    private Uri fileUri; // file url to store image/video

    private ImageView imgPreview;
    private VideoView videoPreview;
    private Button btnCapturePicture, btnRecordVideo;

    //Accident Severity
    public static String accident_registration_no;

    //Accident Severity
    public static String fatal;
    public static String injury;
    public static String simple;
    public static String vehicle_damage;

    //Accident Time and Location
    public static String acc_date;
    public static String acc_time;
    public static String acc_area;
    public static String acc_district;
    public static String acc_region;

    public static String roadName;
    public static String roadNumber;
    public static String roadMark;
    public static String intersectionName;
    public static String intersectionNumber;
    public static String intersectionMark;

    //Vehicle1 details
    public static String V1_fatal;
    public static String V1_injury;
    public static String V1_simple;
    public static String V1_not_injured;

    //Vehicle1 driver details
    public static String V1_surname;
    public static String V1_othernames;
    public static String V1_physical_address_one;
    public static String V1_address_box_one;
    public static String V1_national_id_one;
    public static String V1_phone_no_one;
    public static String V1_gender;
    public static String V1_dob_one;
    public static String V1_nationality_one;
    public static String V1_license_one;
    public static String V1_occupation;
    public static String V1_drug_edit;
    public static String V1_alcohol_edit;
    public static String V1_phone_edit;
    public static String V1_seat_belt_edit;

    //Details for vehicle1
    public static String V1_type_one;
    public static String V1_registration_number_one;

    //Details for vehicle1 insurance
    public static String V1_company_one;
    public static String V1_insurance_type_one;
    public static String V1_insurance_phone;
    public static String V1_policy_period_one;
    public static String V1_policy_number_one;
    public static String V1_repair_amount_one;

    //Details for vehicle1 damage
    public static String V1_vehicle;
    public static String V1_vehicle_total;
    public static String V1_infrastructure;
    public static String V1_cost;
    public static String V1_path;

    //vehicle 2
    public static String V2_fatal;
    public static String V2_injury;
    public static String V2_simple;
    public static String V2_not_injured;

    //Driver2 details
    public static String V2_surname;
    public static String V2_othernames;
    public static String V2_physical_address_one;
    public static String V2_address_box_one;
    public static String V2_national_id_one;
    public static String V2_phone_no_one;
    public static String V2_gender;
    public static String V2_dob_one;
    public static String V2_nationality_one;
    public static String V2_license_one;
    public static String V2_occupation;
    public static String V2_drug_edit;
    public static String V2_alcohol_edit;
    public static String V2_phone_edit;
    public static String V2_seat_belt_edit;

    //Vehicle2 details
    public static String V2_type_one;
    public static String V2_registration_number_one;

    //Details for vehicle1 insurance
    public static String V2_company_one;
    public static String V2_insurance_type_one;
    public static String V2_insurance_phone;
    public static String V2_policy_period_one;
    public static String V2_policy_number_one;
    public static String V2_repair_amount_one;

    //Details for vehicle2 damage
    public static String V2_vehicle;
    public static String V2_vehicle_total;
    public static String V2_infrastructure;
    public static String V2_cost;
    public static String V2_path;


    /**
     * Defining layout items.
     */
    EditText inputFatal,inputReg,inputInjuries,inputSimple,damage,area_name,district,city,region,road_name,road_no,road_mark,intersection_name,intersection_no,intersection_mark;


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

    public static View tempHeader;

    public boolean tempFlag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        gridView = (ListView) findViewById(R.id.headergridview);

        /**
         * Temporary header
         */


        if (!hasheader){
            tempHeader = (View)getLayoutInflater().inflate(R.layout.temp,null);
            gridView.addHeaderView(tempHeader);
            gridView.setAdapter(new gridViewAdapter(MainActivity.this));
            hasheader = true;
            tempFlag = true;



            imgPreview = (ImageView) tempHeader.findViewById(R.id.imgPreview);
            videoPreview = (VideoView) tempHeader.findViewById(R.id.videoPreview);
            btnCapturePicture = (Button) tempHeader.findViewById(R.id.btnCapturePicture);
            btnRecordVideo = (Button) tempHeader.findViewById(R.id.btnCaptureVideo);
        }


        /**
         * Capture image button click event
         */
        btnCapturePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // capture picture
                captureImage();
            }
        });

        /**
         * Record video button click event
         */
        btnRecordVideo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // record video
                recordVideo();
            }
        });

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }

        /**
         * header instance
         */
        header = (View)getLayoutInflater().inflate(R.layout.activity_accident,null);
        /**
         * Defining all layout items
         **/
        inputReg = (EditText)header.findViewById(R.id.reg_ref);

        inputFatal = (EditText)header.findViewById(R.id.fatal_edit);

        inputInjuries = (EditText)header.findViewById(R.id.injury_edit);

        inputSimple = (EditText)header.findViewById(R.id.simple_edit);

        damage = (EditText)header.findViewById(R.id.not_injured_edit);

        area_name = (EditText)header.findViewById(R.id.name);

        district = (EditText)header.findViewById(R.id.district);

        region = (EditText)header.findViewById(R.id.city);

        road_name = (EditText)header.findViewById(R.id.road_name);

        road_no = (EditText)header.findViewById(R.id.road_number);

        road_mark = (EditText)header.findViewById(R.id.road_mark);

        intersection_name = (EditText)header.findViewById(R.id.intersection_name);

        intersection_no = (EditText)header.findViewById(R.id.intersection_number);

        intersection_mark = (EditText)header.findViewById(R.id.intersection_mark);

        mDate = (EditText)header.findViewById(R.id.date);

        mTime = (EditText)header.findViewById(R.id.time);



        //Textchanges

        inputReg.addTextChangedListener(new EditTextWatcher(inputReg));

        inputFatal.addTextChangedListener(new EditTextWatcher(inputFatal));
        inputInjuries.addTextChangedListener(new EditTextWatcher(inputInjuries));
        inputSimple.addTextChangedListener(new EditTextWatcher(inputSimple));
        damage.addTextChangedListener(new EditTextWatcher(damage));

        area_name.addTextChangedListener(new EditTextWatcher(area_name));
        district.addTextChangedListener(new EditTextWatcher(district));
        region.addTextChangedListener(new EditTextWatcher(region));

        mDate.addTextChangedListener(new EditTextWatcher(mDate));
        mTime.addTextChangedListener(new EditTextWatcher(mTime));

        road_name.addTextChangedListener(new EditTextWatcher(road_name));
        road_no.addTextChangedListener(new EditTextWatcher(road_no));
        road_mark.addTextChangedListener(new EditTextWatcher(road_mark));
        intersection_name.addTextChangedListener(new EditTextWatcher(intersection_name));
        intersection_no.addTextChangedListener(new EditTextWatcher(intersection_no));
        intersection_mark.addTextChangedListener(new EditTextWatcher(intersection_mark));



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
                startActivity(passenger);
            }
        });
        mDrawer.setDrawerListener(mDrawerToggle);

        /**
         * instantiate ViewPager
         */
        mPager = (AutoScrollViewPager) header.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setAdapter(mPagerAdapter);

        PageListener pageListener = new PageListener();
        mPager.setOnPageChangeListener(pageListener);



    }



    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    /**
     * Recording video
     */
    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
        // name

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // video successfully recorded
                // preview the recorded video
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /**
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {
            // hide video preview
            videoPreview.setVisibility(View.GONE);

            imgPreview.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            imgPreview.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Previewing recorded video
     */
    private void previewVideo() {
        try {
            // hide image preview
            imgPreview.setVisibility(View.GONE);

            videoPreview.setVisibility(View.VISIBLE);
            videoPreview.setVideoPath(fileUri.getPath());
            // start playing
            videoPreview.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    //TextWatcher
    private class EditTextWatcher implements TextWatcher {

        EditText v;

        public EditTextWatcher(EditText view) {
            this.v = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {

            // Only if the currently edited text field contains something
            if (v.getText().toString().length() > 0) {
                switch (v.getId()) {
                    case R.id.fatal_edit:
                        fatal = inputFatal.getText().toString();
                        break;
                    case R.id.reg_ref:
                        accident_registration_no = inputReg.getText().toString();
                        break;
                    case R.id.injury_edit:
                        injury = inputInjuries.getText().toString();
                        break;
                    case R.id.simple_edit:
                        simple = inputSimple.getText().toString();
                        break;
                    case R.id.not_injured_edit:
                        vehicle_damage = damage.getText().toString();
                        break;
                    case R.id.date:
                        acc_date = mDate.getText().toString();
                        break;
                    case R.id.time:
                        acc_time = mTime.getText().toString();
                        break;
                    case R.id.name:
                        acc_area = area_name.getText().toString();
                        break;
                    case R.id.district:
                        acc_district = district.getText().toString();

                    case R.id.city:
                        acc_region = region.getText().toString();
                        break;
                    case R.id.road_name:
                        roadName= road_name.getText().toString();
                        break;
                    case R.id.road_number:
                        roadNumber = road_no.getText().toString();
                        break;
                    case R.id.road_mark:
                        roadMark = road_mark.getText().toString();
                        break;
                    case R.id.intersection_name:
                        intersectionName = intersection_name.getText().toString();
                        break;
                    case R.id.intersection_number:
                        intersectionNumber = intersection_no.getText().toString();
                        break;

                    case R.id.intersection_mark:
                        intersectionMark = intersection_mark.getText().toString();
                        break;
                    default:
                }
            }


        }
    }



    /**
     * Date change listener
     */
    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
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

                gridView.removeHeaderView(tempHeader);

                if(hasheader) {
                    gridView.removeHeaderView(header);
                    gridView.removeHeaderView(tempHeader);
                    Log.d("headercheck", "yes there is header " + hasheader);
                }
                /**
                 * instance of a gridview
                 */

                mDrawer.closeDrawer(mDrawerList);

                gridView.setAdapter(null);

                gridView.addHeaderView(header);

                MainActivity.hasheader = true;

                gridView.deferNotifyDataSetChanged();

                gridView.setAdapter(new gridViewAdapter(MainActivity.this));
            }

            // Highlight the selected item, update the title, and close the drawer
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            if (position == 1) {

                if (tempFlag){
                    gridView.removeHeaderView(tempHeader);
                    tempHeader.setVisibility(View.GONE);
                }

                if(hasheader) {
                    gridView.removeHeaderView(header);
                    gridView.removeHeaderView(tempHeader);
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

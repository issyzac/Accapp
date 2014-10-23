package com.rsmsa.accapp.library;

/**
 * Created by PETER on 10/21/2014.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHandler extends SQLiteOpenHelper {

    // Logcat tag

    private static final String LOG = "DatabaseHelper";

    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name

    private static final String DATABASE_NAME = "AccidentManager";

    // Table Names

    private static final String TABLE_USER = "users";
    private static final String TABLE_ACCIDENT_LOCATION = "accident_location";
    private static final String TABLE_ACCIDENT_DATA = "accident_data";
    private static final String TABLE_PERSON = "person";
    private static final String TABLE_VEHICLE = "vehicle";
    private static final String TABLE_DRIVER = "driver";
    private static final String TABLE_INSURANCE = "insurance";
    private static final String TABLE_DAMAGE = "damage";
    private static final String TABLE_ROAD_TYPE = "road_type";
    private static final String TABLE_STREET_CONDITION = "street_condition";
    private static final String TABLE_JUNCTION_TYPE = "junction_type";
    private static final String TABLE_VEHICLE_DEFECTS = "vehicle_defects";
    private static final String TABLE_VIOLATIONS = "violations";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_OTHER_DAMAGES = "other_damages";
    private static final String TABLE_ACCIDENT_DESCRIPTION = "accident_description";
    private static final String TABLE_STANDARD = "standards";
    private static final String TABLE_STANDARD_DESCRIPTION = "standard_description";

    // Common column names

    private static final String KEY_ID = "id";
    private static final String KEY_VEHICLE1 = "vehicle1";
    private static final String KEY_VEHICLE2 = "vehicle2";
    private static final String KEY_VEHICLE_ID = "vehicle_id";
    private static final String KEY_VEHECLE1_ID = "vehicle1_id";
    private static final String KEY_VEHECLE2_ID= "vehicle2_id";
    private static final String KEY_NATIONALITY = "nationality";
    private static final String KEY_PHYSICAL_ADDRESS = "physical_address";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_ALCOHOL= "alcohol";
    private static final String KEY_SEAT_HELMET = "seatBelt_helmetUse";
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_SITE_DESCRIPTION = "site_description";

    // USERS Table - column names

    private static final String KEY_RANK = "rank_no";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_TOKEN = "rem_token";
    private static final String KEY_STATION = "station";

    // TABLE_ACCIDENT_LOCATION Table - column names

    private static final String KEY_AREA = "area";
    private static final String KEY_DISTRICT_ID = "district_id";
    private static final String KEY_REGION_ID = "region_id";
    private static final String KEY_ROAD_NAME = "road_name";
    private static final String KEY_ACC_DATA_ID = "accident_data_id";
    private static final String KEY_ROAD_NO = "road_no";
    private static final String KEY_ROAD_KILO_MARK = "road_kilo_mark";
    private static final String KEY_INTERSECTION_NAME = "intersection_name";
    private static final String KEY_INTERSECTION_NO = "intersection_no";
    private static final String KEY_INTERSECTION_KILO_MARK = "intersection_kilo_mark";


    // TABLE_ACCIDENT_DATA Table - column names

    private static final String KEY_DRIVER_ID1 = "driver_id1";
    private static final String KEY_DRIVER_ID2 = "driver_id2";
    private static final String KEY_ACC_REG_NUMBER = "accident_reg_no";
    private static final String KEY_ROAD_TYPE_ID = "road_type_id";
    private static final String KEY_STREET_CONDITION_ID = "street_condition_id";
    private static final String KEY_JUNCTION_TYPE_ID = "junction_type_id";
    private static final String KEY_VEHICLE_DEFECT_ID = "vehicle_defect_id";
    private static final String KEY_DAMAGE_ID = "damage_id";
    private static final String KEY_VIOLATION_ID = "violation_id";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_OTHER_DAMAGE_ID = "other_damage_id";

    // TABLE_PERSON Table - column names
    private static final String KEY_ADDRESS_BOX= "address_box";
    private static final String KEY_NATIONALITY_NATIONAL_ID = "nationality_id";
    private static final String KEY_PHONE_NO = "phone_no";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_DRIVING_LICENSE = "driving_license";
    private static final String KEY_STATUS = "status";
    private static final String KEY_CASUALITY = "casuality";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_VEHICLE_NO = "vehicle_no";

    // TABLE_VEHICLE Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_VEHICLE_TYPE = "vehicle_type";
    private static final String KEY_VEHICLE_REG_NO = "vehicle_reg_no";
    private static final String KEY_OWNER_FULL_NAME = "owner_full_name";
    private static final String KEY_VEHICLE_MODEL = "vehicle_model";
    private static final String KEY_YEAR_MANUFACTURED = "year_of_manufacture";
    private static final String KEY_CHASIS = "chasis_number";
    private static final String KEY_DRUGS = "drugs";
    private static final String KEY_PHONE_USE = "phone_use";

    // TABLE_DRIVER Table - column names
    private static final String KEY_DRIVER_NAME = "driver_name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_OTHER_NAMES = "other_names";
    private static final String KEY_NATIONAL_ID = "national_id";
    private static final String KEY_DRIVER_LICENSE = "license";

    // TABLE_INSURANCE Table - column names

    private static final String KEY_INSURANCE_COMPANY = "insurance_company";
    private static final String KEY_INSURANCE__TYPE = "insurance_type";
    private static final String KEY_INSURANCE_PHONE_NO = "insurance_phone_no";
    private static final String KEY_POLICY_NO = "policy_no";
    private static final String KEY_POLICY_PERIOD = "policy_period";
    private static final String KEY_EXPIRATION = "expiration";
    private static final String KEY_ESTIMATED_REPAIR = "estimated_repair";
    private static final String KEY_OCCUPATION = "occupation";

    // TABLE_DAMAGE Table - column names

    private static final String KEY_VEHICLE = "vehicle";
    private static final String KEY_VEHICLE_TOTAL = "vehicle_total";
    private static final String KEY_INFRASTRUCTURE = "infrastructure";
    private static final String KEY_RESCUE_COST = "rescue_cost";
    private static final String KEY_IMAGE_PATH = "image_path";


    // TABLE_ROAD_TYPE Table - column names

    private static final String KEY_ROAD_CLASS = "road_class";
    private static final String KEY_SURFACE_TYPE = "surface_type";
    private static final String KEY_ROAD_STRUCTURE = "road_structure";
    private static final String KEY_ROAD_STATUS = "road_status";

    // TABLE_STREET_CONDITION Table - column names
    private static final String KEY_ROAD_SURFACE = "road_surface";
    private static final String KEY_LIGHT = "light";
    private static final String KEY_WEATHER = "weather";
    private static final String KEY_ROAD_CONTROL = "road_control";

    // TABLE_JUNCTION_TYPE Table - column names
    private static final String KEY_JUNCTION_STRUCTURE = "junction_structure";
    private static final String KEY_JUNCTION_CONTROL = "junction_control";

    // TABLE_VEHICLE_DEFECTS Table - column names
    private static final String KEY_DEFECT_NUMBER = "defect_no";
    private static final String KEY_DEFECT_VALUE = "defect_value";

    // TABLE_VIOLATIONS Table - column names
    private static final String KEY_VIOLATION_NUMBER = "violation_no";
    private static final String KEY_VIOLATION_VALUE = "violation_value";

    // TABLE_CATEGORY Table - column names
    private static final String KEY_CAT_NAME = "cat_name";
    private static final String KEY_CAT_NUMBER = "cat_number";
    private static final String KEY_CAT_DESCRIPTION = "cat_description";

    // TABLE_OTHER_DAMAGES Table - column names

    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_OTHER_OWNER_NAME = "other_onwer_name";
    private static final String KEY_OTHER_ESTIMATED_COSTS = "estimated_cost";

    // TABLE_STANDARD Table - column names

    private static final String KEY_STANDARD_ID = "standard_id";
    private static final String KEY_STANDARD_NAME = "standard_name";
    private static final String KEY_STANDARD_DESC_ID = "standard_description_id";

    // TABLE_STANDARD_DESCRIPTION Table - column names

    private static final String KEY_DESCRIPTION_NAME = "description_name";

    // Table Create Statements


    // 1.USERS Table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_RANK+ " TEXT UNIQUE NOT NULL,"
            + KEY_FULL_NAME + " TEXT NOT NULL,"
            + KEY_EMAIL + " TEXT NOT NULL UNIQUE,"
            + KEY_STATION + " TEXT NOT NULL,"
            + KEY_REGION_ID + " TEXT NOT NULL,"
            + KEY_DISTRICT_ID + " TEXT NOT NULL,"
            + KEY_PASSWORD + " TEXT NOT NULL,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // 2. TABLE_ACCIDENT_LOCATION table create statement
    private static final String CREATE_TABLE_ACCIDENT_LOCATION = "CREATE TABLE " + TABLE_ACCIDENT_LOCATION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement NOT NULL,"
            + KEY_AREA + " TEXT not null,"
            + KEY_DISTRICT_ID + " INTEGER not null,"
            + KEY_REGION_ID + " INTEGER not null,"
            + KEY_ROAD_NAME + " TEXT not null,"
            + KEY_ROAD_NO + " TEXT not null,"
            + KEY_ROAD_KILO_MARK + " TEXT not null,"
            + KEY_INTERSECTION_NAME + " TEXT not null,"
            + KEY_INTERSECTION_NO + " TEXT not null,"
            + KEY_INTERSECTION_KILO_MARK + " TEXT not null" + ")";



    // 3. TABLE_ACCIDENT_DATA table create statement
    private static final String CREATE_TABLE_ACCIDENT_DATA = "CREATE TABLE " + TABLE_ACCIDENT_DATA
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement NOT NULL,"
            + KEY_ACC_REG_NUMBER + " TEXT NOT NULL,"
            + KEY_DRIVER_ID1 + " INTEGER NOT NULL,"
            + KEY_DRIVER_ID2 + " INTEGER NOT NULL,"
            + KEY_VEHECLE1_ID + " INTEGER NOT NULL,"
            + KEY_VEHECLE2_ID + " INTEGER NOT NULL,"
            + KEY_ROAD_TYPE_ID + " INTEGER NOT NULL,"
            + KEY_STREET_CONDITION_ID + " INTEGER NOT NULL,"
            + KEY_JUNCTION_TYPE_ID + " INTEGER NOT NULL,"
            + KEY_VEHICLE_DEFECT_ID + " INTEGER NOT NULL,"
            + KEY_DAMAGE_ID + " INTEGER NOT NULL,"
            + KEY_VIOLATION_ID + " INTEGER NOT NULL,"
            + KEY_CATEGORY_ID + " INTEGER NOT NULL,"
            + KEY_OTHER_DAMAGE_ID + " INTEGER" + ")";


    // 4. TABLE_PERSON table create statement
    private static final String CREATE_TABLE_PERSON = "CREATE TABLE " + TABLE_PERSON
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_NAME + " TEXT ,"
            + KEY_GENDER + " TEXT ,"
            + KEY_DOB + " TEXT ,"
            + KEY_PHYSICAL_ADDRESS + " TEXT ,"
            + KEY_ADDRESS_BOX + " TEXT ,"
            + KEY_NATIONALITY_NATIONAL_ID + " TEXT ,"
            + KEY_PHONE_NO + " TEXT ,"
            + KEY_CASUALITY + " TEXT ,"
            + KEY_ALCOHOL + " TEXT ,"
            + KEY_SEAT_HELMET + " TEXT ,"
            + KEY_VEHICLE_NO + " TEXT ,"
            + KEY_STATUS + " TEXT " + ")";


    // 5. TABLE_VEHICLE table create statement
    private static final String CREATE_TABLE_VEHICLE = "CREATE TABLE " + TABLE_VEHICLE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_VEHICLE_TYPE + " TEXT not null,"
            + KEY_VEHICLE_REG_NO + " TEXT not null,"
            + KEY_OWNER_FULL_NAME + " TEXT not null,"
            + KEY_NATIONALITY + " TEXT not null,"
            + KEY_PHYSICAL_ADDRESS + " TEXT not null,"
            + KEY_ADDRESS_BOX + " TEXT not null,"
            + KEY_VEHICLE_MODEL + " TEXT not null,"
            + KEY_YEAR_MANUFACTURED + " TEXT not null,"
            + KEY_CHASIS + " TEXT not null" + ")";

    // 6. TABLE_DRIVER table create statement
    private static final String CREATE_TABLE_DRIVER = "CREATE TABLE " + TABLE_DRIVER
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_SURNAME + " TEXT not null,"
            + KEY_OTHER_NAMES + " TEXT not null,"
            + KEY_PHYSICAL_ADDRESS + " TEXT not null,"
            + KEY_ADDRESS_BOX + " TEXT not null,"
            + KEY_NATIONAL_ID + " TEXT not null,"
            + KEY_PHONE_NO + " TEXT not null, "
            + KEY_GENDER + " TEXT not null,"
            + KEY_DOB + " TEXT not null,"
            + KEY_NATIONALITY + " TEXT not null,"
            + KEY_DRIVING_LICENSE + " TEXT ,"
            + KEY_OCCUPATION + " TEXT ,"
            + KEY_ALCOHOL + " TEXT not null,"
            + KEY_DRUGS + " TEXT not null,"
            + KEY_PHONE_USE + " TEXT not null,"
            + KEY_SEAT_HELMET + " TEXT not null" +")";


    // 7. TABLE_INSURANCE table create statement
    private static final String CREATE_TABLE_INSURANCE = "CREATE TABLE " + TABLE_INSURANCE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_INSURANCE_COMPANY + " TEXT not null,"
            + KEY_INSURANCE__TYPE + " TEXT not null,"
            + KEY_INSURANCE_PHONE_NO + " TEXT not null,"
            + KEY_POLICY_NO + " TEXT not null,"
            + KEY_POLICY_PERIOD + " INTEGER not null,"
            + KEY_EXPIRATION + " TEXT not null,"
            + KEY_ESTIMATED_REPAIR + " TEXT not null" + ")";



    // 8. TABLE_DAMAGE table create statement
    private static final String CREATE_TABLE_DAMAGE = "CREATE TABLE " + TABLE_DAMAGE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_VEHICLE + " TEXT"
            + KEY_VEHICLE_TOTAL + " INTEGER"
            + KEY_INFRASTRUCTURE + " TEXT"
            + KEY_RESCUE_COST + " REAL"
            + KEY_IMAGE_PATH + " TEXT" + ")";


    // 9.TABLE_ROAD_TYPE table create statement
    private static final String CREATE_TABLE_ROAD_TYPE = "CREATE TABLE " + TABLE_ROAD_TYPE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_ROAD_CLASS + " TEXT"
            + KEY_SURFACE_TYPE + " TEXT"
            + KEY_ROAD_STRUCTURE + " TEXT"
            + KEY_INFRASTRUCTURE + " TEXT"
            + KEY_ROAD_STATUS + " TEXT" + ")";

    // 10. TABLE_STREET_CONDITION table create statement
    private static final String CREATE_TABLE_STREET_CONDITION = "CREATE TABLE " + TABLE_STREET_CONDITION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_ROAD_SURFACE+ " TEXT"
            + KEY_LIGHT + " TEXT"
            + KEY_WEATHER + " TEXT"
            + KEY_ROAD_CONTROL + " TEXT" + ")";

    // 11. TABLE_JUNCTION_TYPE table create statement
    private static final String CREATE_TABLE_JUNCTION_TYPE = "CREATE TABLE " + TABLE_JUNCTION_TYPE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_JUNCTION_STRUCTURE+ " TEXT"
            + KEY_JUNCTION_CONTROL + " TEXT" + ")";


    // 12. TABLE_VIOLATIONS table create statement
    private static final String CREATE_TABLE_VIOLATIONS = "CREATE TABLE " + TABLE_VIOLATIONS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_VEHECLE1_ID+ " TEXT NOT NULL,"
            + KEY_VEHECLE2_ID + " TEXT NOT NULL,"
            + KEY_ACC_DATA_ID+ " TEXT NOT NULL"+ ")";

    // 13. TABLE_VEHICLE_DEFECTS table create statement
    private static final String CREATE_TABLE_VEHICLE_DEFECTS = "CREATE TABLE " + TABLE_VEHICLE_DEFECTS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_VEHECLE1_ID+ " TEXT NOT NULL,"
            + KEY_VEHECLE2_ID + " TEXT NOT NULL,"
            + KEY_ACC_DATA_ID+ " TEXT NOT NULL" + ")";

    // 14. TABLE_CATEGORY table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_CAT_NAME+ " TEXT"
            + KEY_CAT_NUMBER + " INTEGER"
            + KEY_CAT_DESCRIPTION + " TEXT" + ")";


    // 15. TABLE_OTHER_DAMAGES table create statement
    private static final String CREATE_TABLE_OTHER_DAMAGES = "CREATE TABLE " + TABLE_OTHER_DAMAGES
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement,"
            + KEY_DESCRIPTION+ " TEXT not null,"
            + KEY_OTHER_OWNER_NAME + " TEXT not null,"
            + KEY_OTHER_ESTIMATED_COSTS + " REAL not null" + ")";


    // 16. TABLE_ACCIDENT_DESCRIPTION table create statement
    private static final String CREATE_TABLE_ACCIDENT_DESCRIPTION = "CREATE TABLE " + TABLE_ACCIDENT_DESCRIPTION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement NOT NULL,"
            + KEY_SITE_DESCRIPTION+ " TEXT NOT NULL,"
            + KEY_DIRECTION + " TEXT NOT NULL,"
            + KEY_IMAGE_PATH + " TEXT NOT NULL" + ")";


    // 17. TABLE_STANDARD table create statement
    private static final String CREATE_TABLE_STANDARD = "CREATE TABLE " + TABLE_STANDARD
            + "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement NOT NULL,"
            + KEY_STANDARD_ID+ " INTEGER NOT NULL,"
            + KEY_STANDARD_DESC_ID + " TEXT NOT NULL,"
            + KEY_STANDARD_NAME + " TEXT NOT NULL" + ")";


    // 18. TABLE_STANDARD_DESCRIPTION table create statement
    private static final String CREATE_TABLE_STANDARD_DESCRIPTION = "CREATE TABLE " + TABLE_STANDARD_DESCRIPTION
            + "(" + KEY_ID + " INTEGER PRIMARY KEY  NOT NULL,"
            + KEY_DESCRIPTION_NAME + " TEXT NOT NULL" + ")";


    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ACCIDENT_LOCATION);
        db.execSQL(CREATE_TABLE_ACCIDENT_DATA);
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_VEHICLE);
        db.execSQL(CREATE_TABLE_DRIVER);
        db.execSQL(CREATE_TABLE_INSURANCE);
        db.execSQL(CREATE_TABLE_DAMAGE);
        db.execSQL(CREATE_TABLE_ROAD_TYPE);
        db.execSQL(CREATE_TABLE_STREET_CONDITION);
        db.execSQL(CREATE_TABLE_JUNCTION_TYPE);
        db.execSQL(CREATE_TABLE_VEHICLE_DEFECTS);
        db.execSQL(CREATE_TABLE_VIOLATIONS);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_OTHER_DAMAGES);
        db.execSQL(CREATE_TABLE_ACCIDENT_DESCRIPTION);
        db.execSQL(CREATE_TABLE_STANDARD);
        db.execSQL(CREATE_TABLE_STANDARD_DESCRIPTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCIDENT_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCIDENT_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSURANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROAD_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STREET_CONDITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUNCTION_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLE_DEFECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIOLATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER_DAMAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCIDENT_DESCRIPTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STANDARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STANDARD_DESCRIPTION);

        // create new tables
        onCreate(db);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    //Storing user details in database
    public void addUser(String rank, String full_name,String email,String station,int region_id,int district_id,String password, String created_at) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RANK, rank); // Rank number
        values.put(KEY_FULL_NAME, full_name); // Name
        values.put(KEY_EMAIL, email); // email
        values.put(KEY_STATION, station); // email
        values.put(KEY_REGION_ID, region_id); // email
        values.put(KEY_DISTRICT_ID, district_id); // email
        values.put(KEY_PASSWORD, password); // email
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    //Storing accident location details in database
    public void addLocation(String area, String road_name, String road_no, String road_kilo_mark, String intersection_name,String intersection_no,String intersection_kilo_mark) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AREA, area); // area
        values.put(KEY_ROAD_NAME, road_name); // road_name
        values.put(KEY_ROAD_NO, road_no); // road_no
        values.put(KEY_ROAD_KILO_MARK, road_kilo_mark); // road_kilo_mark
        values.put(KEY_INTERSECTION_NAME, intersection_name); // intersection_name
        values.put(KEY_INTERSECTION_NO, intersection_no); // intersection_no
        values.put(KEY_INTERSECTION_KILO_MARK, intersection_kilo_mark); // intersection_kilo_mark

        // Inserting Row
        db.insert(TABLE_ACCIDENT_LOCATION, null, values);
        db.close(); // Closing database connection
    }
/*
    //Storing accident details in database
    public void addAccident(String accident_reg_no, int driver_id1,int driver_id2, int vehicle1_id, int vehicle2_id, int road_type_id, int street_condition_id, int junction_type_id, int vehicle_defect_id,int damage_id, int violation_id , int category_id, int others_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ACC_REG_NUMBER, accident_reg_no); // accident_reg_no
        values.put(KEY_DRIVER_ID1 ,driver_id1); //driver_id1
        values.put(KEY_DRIVER_ID2 ,driver_id2); //driver_id2
        values.put(KEY_VEHECLE1_ID ,vehicle1_id); //vehicle1_id
        values.put(KEY_VEHECLE2_ID ,vehicle2_id); //vehicle2_id
        values.put(KEY_ROAD_TYPE_ID ,road_type_id); //road_type_id
        values.put(KEY_STREET_CONDITION_ID ,street_condition_id); //street_condition_id
        values.put(KEY_JUNCTION_TYPE_ID ,junction_type_id); //junction_type_id
        values.put(KEY_VEHICLE_DEFECT_ID ,vehicle_defect_id); //vehicle_defect_id
        values.put(KEY_DAMAGE_ID ,damage_id); //damage_id
        values.put(KEY_VIOLATION_ID ,violation_id); //violation_id
        values.put(KEY_CATEGORY_ID ,category_id); //category_id
        values.put(KEY_OTHER_DAMAGE_ID ,others_id); //others_id

        // Inserting Row
        db.insert(TABLE_ACCIDENT_DATA, null, values);
        db.close(); // Closing database connection
    }
    */


    //Storing persons details in database
    public void addPerson(String name, String gender, String dob, String physical_address, String address_box, String nationality_id, String phone_no,String casuality, String alcohol ,String seat_helmet, String vehicle_no, String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // name
        values.put(KEY_GENDER ,gender); //gender
        values.put(KEY_DOB ,dob); //dob
        values.put(KEY_PHYSICAL_ADDRESS ,physical_address); //physical_address
        values.put(KEY_ADDRESS_BOX ,address_box); //address_box
        values.put(KEY_NATIONALITY_NATIONAL_ID ,nationality_id); //nationality
        values.put(KEY_PHONE_NO ,phone_no); //phone_no
        values.put(KEY_CASUALITY ,casuality); //casuality
        values.put(KEY_ALCOHOL ,alcohol); //alcohol
        values.put(KEY_SEAT_HELMET ,seat_helmet); //seat_helmet
        values.put(KEY_VEHICLE_NO ,vehicle_no); //vehicle_no
        values.put(KEY_STATUS ,status); //status
        // Inserting Row
        db.insert(TABLE_PERSON, null, values);
        db.close(); // Closing database connection
    }

    //Storing vehicle details in database
    public void addVehicle(String vehicle_type, String vehicle_reg_no ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VEHICLE_TYPE, vehicle_type); // vehicle_type
        values.put(KEY_VEHICLE_REG_NO ,vehicle_reg_no); //vehicle_reg_no

        // Inserting Row
        db.insert(TABLE_VEHICLE, null, values);
        db.close(); // Closing database connection
    }
    //Storing driver details in database
    public void addDriver(String surname, String other_names,String physical_address,String address_box,String national_id,String phone_no,String gender,String dob, String nationality,String driving_licence, String occupation, String alcohol, String drugs,String phone_use, String seat_helmet) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SURNAME, surname); // surname
        values.put(KEY_OTHER_NAMES ,other_names); //other_names
        values.put(KEY_PHYSICAL_ADDRESS ,physical_address); //physical_address
        values.put(KEY_ADDRESS_BOX ,address_box); //address_box
        values.put(KEY_NATIONAL_ID ,national_id); //national_id
        values.put(KEY_PHONE_NO ,phone_no); //phone_no
        values.put(KEY_GENDER ,gender); //gender
        values.put(KEY_DOB ,dob); //dob
        values.put(KEY_NATIONALITY ,nationality); //nationality
        values.put(KEY_DRIVING_LICENSE ,driving_licence); //driving_licence
        values.put(KEY_OCCUPATION ,occupation); //occupation
        values.put(KEY_ALCOHOL ,alcohol); //alcohol
        values.put(KEY_DRUGS ,drugs); //drugs
        values.put(KEY_PHONE_USE ,phone_use); //phone_use
        values.put(KEY_SEAT_HELMET,seat_helmet); // seat_helmet
        // Inserting Row
        db.insert(TABLE_DRIVER, null, values);
        db.close(); // Closing database connection
    }

    //Storing insurance details in database
    public void addInsurance(String insurance_company_name, String insurance_type, String insurance_phone_no, String policy_no, String expiration_period, String estimated_repair_costs ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INSURANCE_COMPANY ,insurance_company_name); //insurance_company_name
        values.put(KEY_INSURANCE__TYPE ,insurance_type); //insurance_type
        values.put(KEY_INSURANCE_PHONE_NO ,insurance_phone_no); //insurance_phone_no
        values.put(KEY_POLICY_NO ,policy_no); //policy_no
        //values.put(KEY_POLICY_PERIOD ,policy_period); //policy_period
        values.put(KEY_EXPIRATION ,expiration_period); //expiration_period
        values.put(KEY_ESTIMATED_REPAIR ,estimated_repair_costs); //estimated_repair_costs
        // Inserting Row
        db.insert(TABLE_INSURANCE, null, values);
        db.close(); // Closing database connection
    }

    //Storing damage details in database
    public void addDamage(String vehicle, String vehicle_total, String infrastructure, String rescue_costs, String image_path ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VEHICLE ,vehicle); //vehicle
        values.put(KEY_VEHICLE_TOTAL ,vehicle_total); //vehicle_total
        values.put(KEY_INFRASTRUCTURE ,infrastructure); //infrastructure
        values.put(KEY_RESCUE_COST ,rescue_costs); //rescue_costs
        values.put(KEY_IMAGE_PATH ,image_path); //image_path
        // Inserting Row
        db.insert(TABLE_DAMAGE, null, values);
        db.close(); // Closing database connection
    }

    //Storing road_type details in database
    public void addRoadType(String road_class, String surface_type, String road_structure, String infrastructure, String road_status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ROAD_CLASS, road_class); // road_class
        values.put(KEY_SURFACE_TYPE ,surface_type); //surface_type
        values.put(KEY_ROAD_STRUCTURE ,road_structure); //vehicle_total
        values.put(KEY_INFRASTRUCTURE ,infrastructure); //infrastructure
        values.put(KEY_ROAD_STATUS ,road_status); //road_status
        // Inserting Row
        db.insert(TABLE_ROAD_TYPE, null, values);
        db.close(); // Closing database connection
    }

    //Storing STREET_CONDITION details in database
    public void addStreetCondition(String road_surface, String light, String weather, String road_control ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ROAD_SURFACE, road_surface); // road_surface
        values.put(KEY_LIGHT ,light); //light
        values.put(KEY_WEATHER ,weather); //weather
        values.put(KEY_ROAD_CONTROL ,road_control); //road_control
        // Inserting Row
        db.insert(TABLE_STREET_CONDITION, null, values);
        db.close(); // Closing database connection
    }

    //Storing JUNCTION_TYPE details in database
    public void addJunctionType(String junction_structure, String junction_control) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_JUNCTION_STRUCTURE, junction_structure); // junction_structure
        values.put(KEY_JUNCTION_CONTROL ,junction_control); //junction_control
        // Inserting Row
        db.insert(TABLE_JUNCTION_TYPE, null, values);
        db.close(); // Closing database connection
    }

    //Storing VIOLATIONS details in database
    public void addViolation(int vehicle1_id, int vehicle2_id, int acc_data_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VEHECLE1_ID, vehicle1_id); // vehicle1_id
        values.put(KEY_VEHECLE2_ID ,vehicle2_id); //vehicle2_id
        values.put(KEY_ACC_DATA_ID ,acc_data_id); //acc_data_id
        // Inserting Row
        db.insert(TABLE_VIOLATIONS, null, values);
        db.close(); // Closing database connection
    }

    //Storing VEHICLE_DEFECTS details in database
    public void addDefects(int vehicle1_id, int vehicle2_id, int acc_data_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_VEHECLE1_ID, vehicle1_id); // vehicle1_id
        values.put(KEY_VEHECLE2_ID ,vehicle2_id); //vehicle2_id
        values.put(KEY_ACC_DATA_ID ,acc_data_id); //acc_data_id
        // Inserting Row
        db.insert(TABLE_VEHICLE_DEFECTS, null, values);
        db.close(); // Closing database connection
    }

    //Storing CATEGORY details in database
    public void addCategory(int cat_no, String cat_name, String cat_description ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CAT_NUMBER, cat_no); // cat_no
        values.put(KEY_CAT_NAME ,cat_name); //cat_name
        values.put(KEY_CAT_DESCRIPTION ,cat_description); //cat_description
        // Inserting Row
        db.insert(TABLE_CATEGORY, null, values);
        db.close(); // Closing database connection
    }

    //Storing OTHER_DAMAGES details in database
    public void addOtherDamages(String description, String owner_name, double estimated_costs ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, description); // description
        values.put(KEY_OTHER_OWNER_NAME ,owner_name); //owner_name
        values.put(KEY_OTHER_ESTIMATED_COSTS ,estimated_costs); //estimated_costs
        // Inserting Row
        db.insert(TABLE_OTHER_DAMAGES, null, values);
        db.close(); // Closing database connection
    }

    //Storing ACCIDENT_DESCRIPTION details in database
    public void addAccidentDescription(String site_description, String direction, String image_path ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SITE_DESCRIPTION, site_description); // site_description
        values.put(KEY_DIRECTION ,direction); //direction
        values.put(KEY_IMAGE_PATH ,image_path); //image_path
        // Inserting Row
        db.insert(TABLE_ACCIDENT_DESCRIPTION, null, values);
        db.close(); // Closing database connection
    }


    //Storing ACCIDENT_DESCRIPTION details in database

    public void addStandard() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 17, 'others')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 17, 'tyre burst')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 17, 'bad tyre')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 17, 'bad light')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 17, 'brakes')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 12, 16, 'others')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 11, 16, 'zebra crossing')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 10, 16, 'unsecured load')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 9, 16, 'careless ped')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 8, 16, 'drink and drive')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 7, 16, 'wrong direction')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 16, 'overtaking')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 16, 'red light')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 16, 'white lane cross')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 16, 'distance keeping')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 16, 'overload')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 16, 'over speed')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 15, 'speed limit/sign')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 15, 'lane marking')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 15, 'no traffic signal')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 15, 'traffic signal')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 14, 'fog')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 14, 'storm')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 14, 'cloudy')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 14, 'clear')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 13, 'street light')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 13, 'smoke')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 13, 'night')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 13, 'twilight')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 13, 'day')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 12, 'debris')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 12, 'muddy')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 12, 'water')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 12, 'rain')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 12, 'wet')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 12, 'dry')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 11, 'road works')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 11, 'dip(hole/dirt)')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 11, 'hump/bump')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 11, 'steep slope')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 11, 'gentile slope')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 11, 'flat road')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 10, 'sharp curve')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 10, 'slight curve')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 10, 'straight')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 10, 'hard shoulders')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 10, '1,2 or 3 lanes')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 10, 'no lanes')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 9, 'sandy')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 9, 'gravel')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 9, 'metal bridge')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 9, 'concrete')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 9, 'unpaved')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 9, 'paved')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 8, 'bridge')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 8, 'rural roads')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 8, 'city roads')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 8, 'district roads')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 8, 'regional roads')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 8, 'trunk roads')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 7, 'none')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 7, 'flashing signal')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 7, 'traffic light')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 7, 'traffic signs')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 7, 'police officer')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 7, 'uncontrolled')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 11, 6, 'none')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 10, 6, 'pedestrian cross')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 9, 6, 'rail cross no sign')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 8, 6, 'rail cross manned')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 7, 6, 'bridge/fly over')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 6, 6, 'junction > 4 arms')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 5, 6, 'staggered junction')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 4, 6, 'Y junction')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 3, 6, 'T junction')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 2, 6, 'round about',)");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 1, 6, 'crossing roads')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 600, 0, 'others')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 506, 5, 'foreign bus')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 505, 5, 'PSV bus')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 504, 5, 'private bus')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 503, 5, 'PSV daladala')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 502, 5, 'PSV dtaxi cab')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 501, 5, 'PSV 3 wheeler')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 500, 5, 'PSV motorcycle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 402, 4, 'rescue service')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 401, 4, 'ambulance')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 400, 4, 'fire brigade')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 306, 3, 'diplomatic vehicle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 305, 3, 'police motorcycle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 304, 3, 'police");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 303, 3, 'national service')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 302, 3, 'prison vehicle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 301, 3, 'defence force')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 300, 3, 'government vehicle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 208, 2, 'tractor')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 207, 2, 'motorcycle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 206, 2, 'abnormal dimension')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 205, 2, 'abnormal dimension')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 204, 2, 'dangerous goods')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 202, 2, 'truck and trailer')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 201, 2, 'HDV/semi trailer')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 200, 2, 'truck')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 108, 1, 'mkokoteni),");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 107, 1, 'guta')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 106, 1, '3 wheeler')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 105, 1, 'private truck')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 104, 1, 'pick up')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 103, 1, 'private pedalcycle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 102, 1, 'private motorcycle')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 101, 1, 'foreign car')");
        db.execSQL("INSERT INTO TABLE_STANDARD VALUES('', 100, 1, 'private car')");

        db.close(); // Closing database connection
    }

    //Storing STANDARD_DESCRIPTION details in database
    public void addStandardDescription( ) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES ('4',''emergency')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (5, 'passenger service vehicles')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (6, 'junction structure')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (7, 'junction control')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (8, 'road class')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (9, 'surface type')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (10, 'road structure')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (11, 'road status')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (12, 'road surface')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (13, 'light')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (14, 'weather'')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (15, 'control')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (16, 'violation')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (17, 'vehicle defects')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (3, 'government')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (2, 'commercial')");
        db.execSQL("INSERT INTO TABLE_STANDARD_DESCRIPTION VALUES (1, 'private')");
        db.close(); // Closing database connection
    }



    /**
     * Getting user login status
     * return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }
    /**
     * Re create database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();
    }
}


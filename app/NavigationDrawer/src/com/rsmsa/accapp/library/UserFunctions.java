package com.rsmsa.accapp.library;

/**
 * Created by PETER on 10/21/2014.
 */

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

import com.rsmsa.accapp.Passenger;

public class UserFunctions {
    private JSONParser jsonParser;

    //URL of the PHP API

    public static String ipAddress = "192.168.42.140";
    //URL of the PHP API 41.86.176.252 10.0.2.2 192.168.43.162 162.243.26.128
    private static String loginURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String registerURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String forpassURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String verificationURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String chgpassURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String HistoryURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String defectURL  = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String driverURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String insuranceURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String damageURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String acc_descURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String categoryURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String accident_locationURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String accident_dataURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String other_damageURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String vehicleURL= "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String personURL= "http://"+ipAddress+"/PSMS/public/android/index.php";


    private static String road_typeURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String junctionURL  = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String street_conditionURL = "http://"+ipAddress+"/PSMS/public/android/index.php";
    private static String violationURL = "http://"+ipAddress+"/PSMS/public/android/index.php";

    //tags
    private static String login_tag = "login";
    private static String accident_location_tag = "location";
    private static String accident_data_tag = "accident_data";
    private static String person_tag = "person";
    private static String vehicle_tag = "vehicle";
    private static String driver_tag = "driver";
    private static String insurance_tag = "insurance";
    private static String damage_tag = "damage";
    private static String street_cond_tag = "street_condition";
    private static String road_type_tag = "road_type";
    private static String acc_desc_tag = "damage";
    private static String violation_tag = "violation";
    private static String defect_tag = "defects";
    private static String category_tag = "category";
    private static String junction_type_tag = "junction_type";
    private static String other_damage_tag = "road_type";
    private static String forget_pass_tag = "forpass";
    private static String change_pass_tag = "chgpass";
    private static String history_tag = "history";
    private static String verification_tag = "verification";
    private static String register_tag = "register";

    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
    /**
     * Function to Login
     **/
    public JSONObject loginPolice(String RankNO, String password){
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("username", RankNO));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }

    /**
     * Function to change password
     **/
    public JSONObject chgPass(String newpas, String email){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", change_pass_tag));
        params.add(new BasicNameValuePair("newpas", newpas));
        params.add(new BasicNameValuePair("email", email));
        JSONObject json = jsonParser.getJSONFromUrl(chgpassURL, params);
        return json;
    }
    /**
     * Function to reset the password
     **/
    public JSONObject forPass(String forgotpassword){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", forget_pass_tag));
        params.add(new BasicNameValuePair("forgotpassword", forgotpassword));
        JSONObject json = jsonParser.getJSONFromUrl(forpassURL, params);
        return json;
    }

    /**
     * Function to  add AccidentLocation
     **/
    public JSONObject addAccidentLocation(String area,String district, String region,String road_name, String road_no, String road_kilo_mark, String intersection_name,String intersection_no,String intersection_kilo_mark){
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", accident_location_tag));
        params.add(new BasicNameValuePair("accident_area", area));
        params.add(new BasicNameValuePair("district", district));
        params.add(new BasicNameValuePair("region", region));
        params.add(new BasicNameValuePair("road_name", road_name ));
        params.add(new BasicNameValuePair("road_no", road_no));
        params.add(new BasicNameValuePair("road_kilo_mark", road_kilo_mark));
        params.add(new BasicNameValuePair("intersection_name", intersection_name));
        params.add(new BasicNameValuePair("intersection_no", intersection_no));
        params.add(new BasicNameValuePair("intersection_kilo_mark", intersection_kilo_mark));

        JSONObject json = jsonParser.getJSONFromUrl(accident_locationURL,params);
        return json;
    }

    public JSONObject addAccidentData(String accident_reg_no) {
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", accident_data_tag));
        params.add(new BasicNameValuePair("accident_reg_no", accident_reg_no));


    //    params.add(new BasicNameValuePair("others_id", others_id+""));

        JSONObject json = jsonParser.getJSONFromUrl(accident_dataURL,params);
        return json;
    }

    /**
     * Function to  Register
     **/
    public JSONObject addPerson(String name, String gender, String dob, String physical_address, String address_box, String nationality_id, String phone_no,String casuality, String alcohol ,String seat_helmet, String vehicle_no, String status,String registration){
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", person_tag));
        params.add(new BasicNameValuePair("person_name", name));
        params.add(new BasicNameValuePair("person_gender", gender));
        params.add(new BasicNameValuePair("dob", dob));
        params.add(new BasicNameValuePair("physical_address", physical_address));
        params.add(new BasicNameValuePair("address_box", address_box));
        params.add(new BasicNameValuePair("nationality_id", nationality_id));
        params.add(new BasicNameValuePair("phone_no", phone_no));
        params.add(new BasicNameValuePair("casuality", casuality));
        params.add(new BasicNameValuePair("alcohol", alcohol));
        params.add(new BasicNameValuePair("seat_helmet", seat_helmet));
        params.add(new BasicNameValuePair("vehicle_no", vehicle_no));
        params.add(new BasicNameValuePair("status", status));
        params.add(new BasicNameValuePair("reg_id", registration));



        JSONObject json = jsonParser.getJSONFromUrl(personURL,params);
        return json;
    }

    public JSONObject addVehicle(String vehicle_type, String vehicle_reg_no ) {
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", vehicle_tag));
        params.add(new BasicNameValuePair("vehicle_type", vehicle_type));
        params.add(new BasicNameValuePair("vehicle_reg_no", vehicle_reg_no));

        JSONObject json = jsonParser.getJSONFromUrl(vehicleURL,params);
        return json;
    }

    public JSONObject addDriver(String surname, String other_names,String physical_address,String address_box,String national_id,String phone_no,String gender,String dob, String nationality,String driving_licence, String occupation, String alcohol, String drugs,String phone_use, String seat_helmet) {

        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", driver_tag));
        params.add(new BasicNameValuePair("surname", surname));
        params.add(new BasicNameValuePair("other_names", other_names));
        params.add(new BasicNameValuePair("physical_address", physical_address));
        params.add(new BasicNameValuePair("address_box", address_box));
        params.add(new BasicNameValuePair("national_id", national_id));
        params.add(new BasicNameValuePair("phone_no", phone_no));
        params.add(new BasicNameValuePair("gender", gender));
        params.add(new BasicNameValuePair("dob", dob));
        params.add(new BasicNameValuePair("nationality", nationality));
        params.add(new BasicNameValuePair("driving_licence", driving_licence));
        params.add(new BasicNameValuePair("occupation", occupation));
        params.add(new BasicNameValuePair("alcohol", alcohol));
        params.add(new BasicNameValuePair("drugs", drugs));
        params.add(new BasicNameValuePair("phone_use", phone_use));
        params.add(new BasicNameValuePair("seat_helmet", seat_helmet));

        JSONObject json = jsonParser.getJSONFromUrl(driverURL,params);
        return json;
    }

    public JSONObject addInsurance(String insurance_company_name, String insurance_type, String insurance_phone_no, String policy_no, String expiration_period, String estimated_repair_costs ) {
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", insurance_tag));
        params.add(new BasicNameValuePair("company_name", insurance_company_name));
        params.add(new BasicNameValuePair("insurance_type", insurance_type));
        params.add(new BasicNameValuePair("insurance_phone_no", insurance_phone_no));
        params.add(new BasicNameValuePair("policy_no", policy_no));
        params.add(new BasicNameValuePair("expiration_period", expiration_period ));
        params.add(new BasicNameValuePair("estimated_repair_costs", estimated_repair_costs));

        JSONObject json = jsonParser.getJSONFromUrl(insuranceURL,params);
        return json;
    }

    public JSONObject addDamage(String vehicle, String vehicle_total, String infrastructure, String rescue_costs ) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", damage_tag));
        params.add(new BasicNameValuePair("vehicle", vehicle));
        params.add(new BasicNameValuePair("vehicle_total", vehicle_total));
        params.add(new BasicNameValuePair("infrastructure", infrastructure));
        params.add(new BasicNameValuePair("rescue_costs", rescue_costs));
       // params.add(new BasicNameValuePair("image_path", image_path));

        JSONObject json = jsonParser.getJSONFromUrl(damageURL,params);
        return json;
    }

    public JSONObject addRoadType(int surface_type, int road_structure, int infrastructure, int road_status) {
Log.d("mat",surface_type+"");
        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", road_type_tag));
       // params.add(new BasicNameValuePair("road_class", road_class+""));
        params.add(new BasicNameValuePair("surface_type", surface_type+""));
        params.add(new BasicNameValuePair("road_structure", road_structure+""));
        params.add(new BasicNameValuePair("infrastructure", infrastructure+""));
        params.add(new BasicNameValuePair("road_status", road_status+""));

        JSONObject json = jsonParser.getJSONFromUrl(road_typeURL,params);
        return json;
    }

    public JSONObject addStreetCondition(int road_surface, int light, int weather, int road_control ) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", street_cond_tag));
        params.add(new BasicNameValuePair("road_surface", road_surface+""));
        params.add(new BasicNameValuePair("light", light+""));
        params.add(new BasicNameValuePair("weather", weather+""));
        params.add(new BasicNameValuePair("road_control", road_control+""));

        JSONObject json = jsonParser.getJSONFromUrl(street_conditionURL,params);
        return json;
    }

    public JSONObject addJunctionType(int junction_structure, int junction_control) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", junction_type_tag));
        params.add(new BasicNameValuePair("junction_structure", junction_structure+""));
        params.add(new BasicNameValuePair("junction_control", junction_control+""));

        JSONObject json = jsonParser.getJSONFromUrl(junctionURL,params);
        return json;
    }

    public JSONObject addViolation(int number, int violation, String acc_data_id) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", violation_tag));
        params.add(new BasicNameValuePair("number", number +""));
        params.add(new BasicNameValuePair("violation", violation +""));
        params.add(new BasicNameValuePair("reg", acc_data_id ));

        JSONObject json = jsonParser.getJSONFromUrl(violationURL,params);
        return json;
    }


    public JSONObject addDefects(int vehicle1_id, int vehicle2_id, String acc_data_id) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", defect_tag));
        params.add(new BasicNameValuePair("number", vehicle1_id + ""));
        params.add(new BasicNameValuePair("defect", vehicle2_id + ""));
        params.add(new BasicNameValuePair("accident_reg", acc_data_id));

        JSONObject json = jsonParser.getJSONFromUrl(defectURL,params);
        return json;
    }


    public JSONObject addCategory(String cat_no, String cat_name, String cat_description ) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", category_tag));
        params.add(new BasicNameValuePair("cat_no", cat_no));
        params.add(new BasicNameValuePair("cat_name", cat_name));
        params.add(new BasicNameValuePair("cat_description", cat_description));

        JSONObject json = jsonParser.getJSONFromUrl(categoryURL,params);
        return json;
    }

    public JSONObject addOtherDamages(String description, String owner_name, double estimated_costs ) {
        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", other_damage_tag));
        params.add(new BasicNameValuePair("road_surface", description));
        params.add(new BasicNameValuePair("owner_name", owner_name));
        params.add(new BasicNameValuePair("estimated_costs", estimated_costs+""));

        JSONObject json = jsonParser.getJSONFromUrl(other_damageURL,params);
        return json;
    }

    public JSONObject addAccidentDescription(String site_description, String direction, String image_path ) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", acc_desc_tag));
        params.add(new BasicNameValuePair("site_description", site_description));
        params.add(new BasicNameValuePair("direction", direction));
        params.add(new BasicNameValuePair("image_path", image_path));

        JSONObject json = jsonParser.getJSONFromUrl(acc_descURL,params);
        return json;
    }


    public JSONObject registerOffence(String license, String plateNumber, String commit, String offence,String issuer_no, String amount, double lat, double longi){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("license", license));
        params.add(new BasicNameValuePair("plateNumber", plateNumber));
        params.add(new BasicNameValuePair("commit", commit));
        params.add(new BasicNameValuePair("offence", offence));
        params.add(new BasicNameValuePair("RankNo", issuer_no));
        params.add(new BasicNameValuePair("amount", amount));
        params.add(new BasicNameValuePair("latitude", lat+""));
        params.add(new BasicNameValuePair("longitude", longi+""));


        JSONObject json = jsonParser.getJSONFromUrl(registerURL,params);
        return json;
    }

    /**
     * Function to  get history
     **/
    public JSONObject getHistory(String license){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", history_tag));
        params.add(new BasicNameValuePair("license", license));
        JSONObject json = jsonParser.getJSONFromUrl(HistoryURL,params);
        return json;
    }


    /**
     * Function to  Register
     **/
    public JSONObject carAndLicenceVerification(String license, String plateNumber){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", verification_tag));
        params.add(new BasicNameValuePair("license", license));
        params.add(new BasicNameValuePair("plateNumber", plateNumber));


        JSONObject json = jsonParser.getJSONFromUrl(verificationURL,params);
        return json;
    }
    /**
     * Function to logout user
     * Resets the temporary data stored in SQLite Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
}



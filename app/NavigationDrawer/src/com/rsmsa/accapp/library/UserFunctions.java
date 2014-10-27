package com.rsmsa.accapp.library;

/**
 * Created by PETER on 10/21/2014.
 */

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;

public class UserFunctions {
    private JSONParser jsonParser;

    //URL of the PHP API
    private static String loginURL = "http://inventory.unnett.com/android/";
    private static String vehicleURL, accident_locationURL,accident_dataURL, driverURL, insuranceURL, damageURL, road_typeURL,street_conditionURL,categoryURL, other_damageURL, acc_descURL, junctionURL,violationURL, defectURL  = "http://inventory.unnett.com/android/";
    private static String forpassURL = "http://inventory.unnett.com/android/";
    private static String chgpassURL = "http://inventory.unnett.com/android/";
    private static String personURL = "http://inventory.unnett.com/android/";

    private static String login_tag = "login";
    private static String accident_location_tag = "accident_location";
    private static String accident_data_tag = "accident_data";
    private static String person_tag = "person";
    private static String vehicle_tag = "vehicle";
    private static String driver_tag = "driver";
    private static String insurance_tag = "insurance";
    private static String damage_tag = "damage";
    private static String street_cond_tag = "street_condition";
    private static String road_type_tag = "road_type";
    private static String acc_desc_tag = "damage";
    private static String violation_tag = "street_condition";
    private static String defect_tag = "street_condition";
    private static String category_tag = "street_condition";
    private static String junction_type_tag = "street_condition";
    private static String other_damage_tag = "road_type";
    private static String forget_pass_tag = "forpass";
    private static String change_pass_tag = "chgpass";
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
    /**
     * Function to Login
     **/
    public JSONObject loginUser(String RankNO, String password){
        // Building Parameters
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
     * Function to  Register
     **/
    public JSONObject addAccidentLocation(String area, String accident_data_id,int district_id, int region_id, String road_name, String road_no, String road_kilo_mark, String intersection_name,String intersection_no,String intersection_kilo_mark){
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", accident_location_tag));
        params.add(new BasicNameValuePair("accident_area", area));
        params.add(new BasicNameValuePair("accident_data_id", accident_data_id));
        params.add(new BasicNameValuePair("district_id", district_id +""));
        params.add(new BasicNameValuePair("region_id", region_id +""));
        params.add(new BasicNameValuePair("road_name", road_name));
        params.add(new BasicNameValuePair("road_no", road_no));
        params.add(new BasicNameValuePair("road_kilo_mark", road_kilo_mark));
        params.add(new BasicNameValuePair("intersection_name", intersection_name));
        params.add(new BasicNameValuePair("intersection_no", intersection_no));
        params.add(new BasicNameValuePair("intersection_kilo_mark", intersection_kilo_mark));

        JSONObject json = jsonParser.getJSONFromUrl(accident_locationURL,params);
        return json;
    }
/*
    public JSONObject addAccidentData(String accident_reg_no, int driver_id1,int driver_id2, int vehicle1_id, int vehicle2_id, int road_type_id, int street_condition_id, int junction_type_id, int vehicle_defect_id,int damage_id, int violation_id , int category_id, int others_id) {
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", accident_data_tag));
        params.add(new BasicNameValuePair("accident_reg_no", accident_reg_no));
        params.add(new BasicNameValuePair("driver_id1", driver_id1 + ""));
        params.add(new BasicNameValuePair("driver_id2", driver_id2 +""));
        params.add(new BasicNameValuePair("vehicle1_id", vehicle1_id +""));
        params.add(new BasicNameValuePair("vehicle2_id", vehicle2_id +""));
        params.add(new BasicNameValuePair("road_type_id", road_type_id +""));
        params.add(new BasicNameValuePair("street_condition_id", street_condition_id +""));
        params.add(new BasicNameValuePair("junction_type_id", junction_type_id +""));
        params.add(new BasicNameValuePair("vehicle_defect_id", vehicle_defect_id+""));
        params.add(new BasicNameValuePair("damage_id", damage_id +""));
        params.add(new BasicNameValuePair("violation_id", violation_id +""));
        params.add(new BasicNameValuePair("category_id", category_id +""));
        params.add(new BasicNameValuePair("others_id", others_id+""));

        JSONObject json = jsonParser.getJSONFromUrl(accident_dataURL,params);
        return json;
    }
*/
    /**
     * Function to  Register
     **/
    public JSONObject addPerson(String name, String gender, String dob, String physical_address, String address_box, String nationality_id, String phone_no,String casuality, String alcohol ,String seat_helmet, String vehicle_no, String status){
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
        //  params.add(new BasicNameValuePair("driving_license", driving_license));
        // params.add(new BasicNameValuePair("occupation", occupation));
        params.add(new BasicNameValuePair("casuality", casuality));
        params.add(new BasicNameValuePair("alcohol", alcohol));
        //   params.add(new BasicNameValuePair("signature", signature));
        params.add(new BasicNameValuePair("seat_helmet", seat_helmet));
        params.add(new BasicNameValuePair("vehicle_no", vehicle_no));
        //   params.add(new BasicNameValuePair("acc_data_id", acc_data_id + ""));
        params.add(new BasicNameValuePair("status", status));

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
        params.add(new BasicNameValuePair("address_box", address_box));
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
        params.add(new BasicNameValuePair("expiration_period", expiration_period +""));
        params.add(new BasicNameValuePair("estimated_repair_costs", estimated_repair_costs + ""));

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

    public JSONObject addRoadType(String road_class, String surface_type, String road_structure, String infrastructure, String road_status) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", road_type_tag));
        params.add(new BasicNameValuePair("road_class", road_class));
        params.add(new BasicNameValuePair("surface_type", surface_type));
        params.add(new BasicNameValuePair("road_structure", road_structure));
        params.add(new BasicNameValuePair("infrastructure", infrastructure));
        params.add(new BasicNameValuePair("road_status", road_status));

        JSONObject json = jsonParser.getJSONFromUrl(road_typeURL,params);
        return json;
    }


    public JSONObject addStreetCondition(String road_surface, String light, String weather, String road_control ) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", street_cond_tag));
        params.add(new BasicNameValuePair("road_surface", road_surface));
        params.add(new BasicNameValuePair("light", light));
        params.add(new BasicNameValuePair("weather", weather));
        params.add(new BasicNameValuePair("road_control", road_control));

        JSONObject json = jsonParser.getJSONFromUrl(street_conditionURL,params);
        return json;
    }

    public JSONObject addJunctionType(String junction_structure, String junction_control) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", junction_type_tag));
        params.add(new BasicNameValuePair("junction_structure", junction_structure));
        params.add(new BasicNameValuePair("junction_control", junction_control));

        JSONObject json = jsonParser.getJSONFromUrl(junctionURL,params);
        return json;
    }
    /*
        public JSONObject addViolation(int vehicle1_id, int vehicle2_id, int acc_data_id) {

            // Building Parameters

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

            params.add(new BasicNameValuePair("tag", violation_tag));
            params.add(new BasicNameValuePair("vehicle1_id", vehicle1_id +""));
            params.add(new BasicNameValuePair("vehicle2_id", vehicle2_id +""));
            params.add(new BasicNameValuePair("acc_data_id", acc_data_id +""));

            JSONObject json = jsonParser.getJSONFromUrl(violationURL,params);
            return json;
        }
    */
   /*
    public JSONObject addDefects(int vehicle1_id, int vehicle2_id, String acc_data_id) {

        // Building Parameters

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

        params.add(new BasicNameValuePair("tag", defect_tag));
        params.add(new BasicNameValuePair("road_class", vehicle1_id + ""));
        params.add(new BasicNameValuePair("surface_type", vehicle2_id + ""));
        params.add(new BasicNameValuePair("road_structure", acc_data_id));

        JSONObject json = jsonParser.getJSONFromUrl(defectURL,params);
        return json;
    }

*/
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



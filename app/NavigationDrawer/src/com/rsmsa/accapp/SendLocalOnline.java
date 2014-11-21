package com.rsmsa.accapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.rsmsa.accapp.library.DatabaseHandler;
import com.rsmsa.accapp.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PETER on 11/3/2014.
 */
public class SendLocalOnline extends Activity {

    /**
     * JSON Response node names.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";

    //location details

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ProcessVehicleDetails().execute();


    }


    private class ProcessVehicleDetails extends AsyncTask<String, String, JSONObject> {


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        //location details

        String area,district, region ,roadName,roadNo ,roadMark,IntersectionName,IntersectionNo,IntersectionMark;

        //driver  details
        String surname,other_names ,physical_address,po_box,national_id ,driver_phone_no,dob,gender ,nationality,licence,occupation,drug ,alcohol,phone_use,seatbelt_helmet;

        //Vehicle  details
        String type,reg_no;

        //insurance  data
        String company_name,company_type ,phone_no ,policy_no,expire_period ,repair_costs ;

        String vehicle,total,infrastructure ,costs;




        @Override
        protected void onPreExecute() {
            super.onPreExecute();




        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONObject json=new JSONObject();
            UserFunctions userFunction = new UserFunctions();

            ArrayList<HashMap<String, String>> LocList = db.getLocation();

            for (HashMap<String, String> location:LocList)
            {
                area = location.get("area");
                roadName = location.get("road_name");
                roadNo = location.get("road_no");
                roadMark = location.get("road_mark");
                IntersectionName = location.get("interName");
                IntersectionNo = location.get("interNo");
                IntersectionMark = location.get("interMark");

                //accident Location
                if (area == "" && roadName == "" &&  roadNo =="" && roadMark == "" && IntersectionName =="" && IntersectionNo=="" && IntersectionMark=="")
                {}
                else{
                    json = userFunction.addAccidentLocation(area,district,district,roadName,roadNo,roadMark,IntersectionName,IntersectionNo,IntersectionMark );
                }

            }


            ArrayList<HashMap<String, String>> VehicleList = db.getVehicle();
            for (HashMap<String, String> vehicle:VehicleList)
            {
                type = vehicle.get("type");
                reg_no = vehicle.get("reg_no");
                //vehicle
                if (type==""&&reg_no=="") {

                }else{
                    json = userFunction.addVehicle(type, reg_no);
                }

            }


            ArrayList<HashMap<String, String>> DriverList = db.getDriver();
            for (HashMap<String, String> driver:DriverList)
            {
                surname = driver.get("surname");
                other_names = driver.get("names");
                physical_address = driver.get("address");
                po_box = driver.get("po_box");
                national_id = driver.get("national_id");
                driver_phone_no = driver.get("phone");
                gender = driver.get("gender");
                dob = driver.get("dob");
                nationality = driver.get("nationality");
                licence = driver.get("licence");
                occupation = driver.get("occupation");
                alcohol = driver.get("alcohol");
                drug = driver.get("drugs");
                phone_use = driver.get("phone_use");
                seatbelt_helmet = driver.get("helmet");

                //driver

                if (surname==""&&other_names==""&&physical_address==""&&po_box==""&&national_id==""&&driver_phone_no==""&&gender==""&&dob=="" && nationality=="" && licence =="" && occupation =="" && drug=="" &&alcohol=="" &&phone_use=="" &&seatbelt_helmet =="") {

                }else{
                    json = userFunction.addDriver(surname, other_names, physical_address, po_box, national_id, phone_no, gender, dob, nationality, licence, occupation, drug, alcohol, phone_use, seatbelt_helmet);
                }

            }


            ArrayList<HashMap<String, String>> InsuranceList = db.getInsurance();
            for (HashMap<String, String> insurance:InsuranceList)
            {
                company_name = insurance.get("company_name");
                company_type = insurance.get("company_type");
                phone_no = insurance.get("company_phone");
                policy_no = insurance.get("policy_no");
                expire_period = insurance.get("expire");
                repair_costs = insurance.get("costs");

                //insurance
                if (company_name == "" && company_type=="" &phone_no=="" &policy_no=="" &&expire_period=="" &&repair_costs =="") {

                }else{
                    json = userFunction.addInsurance(company_name, company_type, phone_no, policy_no, expire_period,repair_costs);
                }

            }


            ArrayList<HashMap<String, String>> DamageList = db.getDamage();
            for (HashMap<String, String> damage:DamageList)
            {
                vehicle = damage.get("vehicle");
                total = damage.get("total");
                infrastructure = damage.get("infrastructure");
                costs = damage.get("costs");

                //damage
                if (vehicle==""&&total==""&&infrastructure==""&&infrastructure == "") {

                }else{
                    json = userFunction.addDamage(vehicle,total,infrastructure,costs);
                }


            }



            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            /**
             * Checks for success message.
             **/
            try {
                if (json != null && json.getString(KEY_SUCCESS) != null) {
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {

                        Toast.makeText(getApplicationContext(), "On internet Access Data Sent To Server",Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Error Occurred.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

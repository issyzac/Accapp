 package com.rsmsa.accapp;


import android.app.Activity;
import android.util.Log;

import com.rsmsa.accapp.library.UserFunctions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.HashMap;

public class AndroidXMLParsingActivity extends Activity {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "psms";


    // All static variables
    static final String URL = "http://"+ UserFunctions.ipAddress+"/PSMS/public/android/daily_report.php";
    // XML node keys
    static final String KEY_ITEM = "natureOfOffence"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "nature";
    static final String KEY_AMOUNT = "amount";
    static final String KEY_RELATING = "relating";

    public void AndroidXMLParsingActivity() {

    }

    public void createOffence(DatabaseHandlerOffence db){
        //  DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        // looping through all item nodes <offence>

        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            String id = parser.getValue(e, KEY_ID);
            String name = parser.getValue(e, KEY_NAME);
            String amount = parser.getValue(e, KEY_AMOUNT);
            String relating = parser.getValue(e, KEY_RELATING);
            Log.d("tag","id: "+id +"name:" + name + " amount:" + amount + "  relating:" + relating + "");
            db.addNature(id,name,amount, relating);

        }

    }


}

package edu.aku.hassannaqvi.naunehal_hhlisting_app.core;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;

import java.util.HashMap;
import java.util.Map;

import edu.aku.hassannaqvi.naunehal_hhlisting_app.contracts.ListingContract;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.contracts.SignupContract;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.otherClasses.TypefaceUtil;

/**
 * Created by hassan.naqvi on 10/15/2016.
 */

public class MainApp extends Application {

//    public static final String _IP = "http://f38158";// .TEST server
    public static final String _IP = "https://vcoe1.aku.edu";// .LIVE server
    public static final String _HOST_URL = MainApp._IP + "/naunehal/api/";
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    public static String _UPDATE_URL = MainApp._IP + "/naunehal/app/listings/";
    public static String DeviceURL = "devices.php";

    public static String TAG = "MainApp";
    public static Boolean admin = false;
    public static String IMEI;
    public static String DIST_ID;
    public static ListingContract lc;
    public static int hh01txt;
    public static String hh02txt;
    public static int hh03txt = 0;
    public static String hh07txt;
    public static int fCount = 0;
    public static int fTotal = 0;
    public static int cCount = 0;

    public static String enumCode = "";
    public static String clusterCode = "";
    public static String enumStr = "";

    public static SignupContract signContract;

    public static int cTotal = 0;
    public static SharedPreferences sharedPref;
    public static String userEmail = "0000";
    public static int versionCode;
    public static String versionName;
    public static boolean validateFlag;
    public static String tabCheck = "";
    public static String formDate;

    public static void updatePSU(String psuCode, String structureNo) {

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(psuCode, structureNo);

        editor.apply();
        Log.d(TAG, "updatePSU: " + psuCode + " " + structureNo);

    }

    public static void updateTabNo(String psuCode, String structureNo) {

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("T" + psuCode, structureNo);

        editor.apply();
        Log.d(TAG, "updatePSU: " + psuCode + " " + structureNo);

    }

    public static Boolean PSUExist(String psuCode) {
        Log.d(TAG, "PSUExist: " + psuCode);
        MainApp.hh03txt = Integer.parseInt(sharedPref.getString(psuCode, "0"));
        Log.d(TAG, "PSUExist (Test): " + sharedPref.getString(psuCode, "0"));

        MainApp.tabCheck = sharedPref.getString("T" + psuCode, "");

        if (MainApp.hh03txt == 0) {
            Log.d(TAG, "PSUExist (False): " + MainApp.hh03txt);

            return false;
        } else {
            Log.d(TAG, "PSUExist (True): " + MainApp.hh03txt);

            return true;
        }
    }

    public static String getTagName(Context mContext) {
        SharedPreferences sharedPref = mContext.getSharedPreferences("tagName", MODE_PRIVATE);
        return sharedPref.getString("tagName", null);
    }

    public static HashMap<String, String> getTagValues(Context mContext) {
        SharedPreferences sharedPref = mContext.getSharedPreferences("tagName", MODE_PRIVATE);

        HashMap<String, String> map = new HashMap<>();
        map.put("tag", sharedPref.getString("tagName", null));
        map.put("org", sharedPref.getString("countryID", null));
        map.put("listing", sharedPref.getString("listing", null));
        map.put("date", sharedPref.getString("date", null));

        return map;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("App", "Creating...");
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/JameelNooriNastaleeq.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
        sharedPref = getSharedPreferences("PSUCodes", Context.MODE_PRIVATE);
    }

}

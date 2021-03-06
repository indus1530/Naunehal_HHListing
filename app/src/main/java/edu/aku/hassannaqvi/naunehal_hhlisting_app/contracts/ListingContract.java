package edu.aku.hassannaqvi.naunehal_hhlisting_app.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 10/18/2016.
 */
public class ListingContract {

    private String ID;
    private String UID;
    private String hhDT;
    private String hhDT01;
    private String enumCode;
    private String clusterCode;
    private String enumStr;
    private String hh01;
    private String hh02;
    private String hh03;
    private String hh04;
    private String hh04other;
    private String hh05;
    private String hh06;
    private String hh07;
    private String hh08a;
    private String hh08;
    private String hh09;
    private String delHH;
    private String hh10;
    private String hh11;
    private String hh12;
    private String hh13;
    private String hh14;
    private String hh15;
    private String tabNo;
    private String hhadd;
    private String isNewHH;
    private String counter;
    private String DeviceID;
    private String AppVer;
    private String tagId;
    private String username;

    public ListingContract() {
    }

    public ListingContract(String ID) {
        this.ID = ID;
    }

    public String getHh04other() {
        return hh04other;
    }

    public void setHh04other(String hh04other) {
        this.hh04other = hh04other;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public void setClusterCode(String clusterCode) {
        this.clusterCode = clusterCode;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHhDT() {
        return hhDT;
    }

    public void setHhDT(String hhDT) {
        this.hhDT = hhDT;
    }


    public String getEnumCode() {
        return enumCode;
    }

    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }


    public String getEnumStr() {
        return enumStr;
    }

    public void setEnumStr(String enumStr) {
        this.enumStr = enumStr;
    }


    public String getHh01() {
        return hh01;
    }

    public void setHh01(String hh01) {
        this.hh01 = hh01;
    }

    public String getHh02() {
        return hh02;
    }

    public void setHh02(String hh02) {
        this.hh02 = hh02;
    }

    public String getHh03() {
        return hh03;
    }

    public void setHh03(String hh03) {
        this.hh03 = hh03;
    }

    public String getHh04() {
        return hh04;
    }

    public void setHh04(String hh04) {
        this.hh04 = hh04;
    }

    public String getHh14() {
        return hh14;
    }

    public void setHh14(String hh14) {
        this.hh14 = hh14;
    }


    public String getHh15() {
        return hh15;
    }

    public void setHh15(String hh15) {
        this.hh15 = hh15;
    }


    public String getHh05() {
        return hh05;
    }

    public void setHh05(String hh05) {
        this.hh05 = hh05;
    }

    public String getHh06() {
        return hh06;
    }

    public void setHh06(String hh06) {
        this.hh06 = hh06;
    }

    public String getHh07() {
        return hh07;
    }

    public void setHh07(String hh07) {
        this.hh07 = hh07;
    }

    public String getHh08() {
        return hh08;
    }

    public void setHh08(String hh08) {
        this.hh08 = hh08;
    }

    public String getHh09() {
        return hh09;
    }

    public void setHh09(String hh09) {
        this.hh09 = hh09;
    }

    public String getDelHH() {
        return delHH;
    }

    public void setDelHH(String delHH) {
        this.delHH = delHH;
    }

    public String getHh10() {
        return hh10;
    }

    public void setHh10(String hh10) {
        this.hh10 = hh10;
    }

    public String getHh11() {
        return hh11;
    }

    public void setHh11(String hh11) {
        this.hh11 = hh11;
    }

    public String getHh12() {
        return hh12;
    }

    public void setHh12(String hh12) {
        this.hh12 = hh12;
    }

    public String getHh13() {
        return hh13;
    }

    public void setHh13(String hh13) {
        this.hh13 = hh13;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getHhadd() {
        return hhadd;
    }

    public void setHhadd(String hhadd) {
        this.hhadd = hhadd;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getAppVer() {
        return AppVer;
    }

    public void setAppVer(String appVer) {
        AppVer = appVer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTabNo() {
        return tabNo;
    }

    public void setTabNo(String tabNo) {
        this.tabNo = tabNo;
    }

    public String getIsNewHH() {
        return isNewHH;
    }

    public void setIsNewHH(String isNewHH) {
        this.isNewHH = isNewHH;
    }

    public String getHh08a() {
        return hh08a;
    }

    public void setHh08a(String hh08a) {
        this.hh08a = hh08a;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getHhDT01() {
        return hhDT01;
    }

    public void setHhDT01(String hhDT02) {
        this.hhDT01 = hhDT02;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("projectname", "Naunehal-LINELISTING");
        json.put(ListingEntry._ID, this.ID);
        json.put(ListingEntry.COLUMN_NAME_UID, this.UID);
        json.put(ListingEntry.COLUMN_NAME_HHDATETIME, this.hhDT);
        json.put(ListingEntry.COLUMN_NAME_HHDATETIME01, this.hhDT01);
        json.put(ListingEntry.COLUMN_NAME_ENUMCODE, this.enumCode);
        json.put(ListingEntry.COLUMN_NAME_CLUSTERCODE, this.clusterCode);
        json.put(ListingEntry.COLUMN_NAME_ENUMSTR, this.enumStr);
        json.put(ListingEntry.COLUMN_NAME_HH01, this.hh01);
        json.put(ListingEntry.COLUMN_NAME_HH02, this.hh02);
        json.put(ListingEntry.COLUMN_NAME_HH03, this.hh03);
        json.put(ListingEntry.COLUMN_NAME_HH04, this.hh04);
        json.put(ListingEntry.COLUMN_NAME_HH04OTHER, this.hh04other);
        json.put(ListingEntry.COLUMN_NAME_HH05, this.hh05);
        json.put(ListingEntry.COLUMN_NAME_HH06, this.hh06);
        json.put(ListingEntry.COLUMN_NAME_HH07, this.hh07);
        json.put(ListingEntry.COLUMN_NAME_HH08, this.hh08);
        json.put(ListingEntry.COLUMN_NAME_HH09, this.hh09);
        json.put(ListingEntry.COLUMN_NAME_DELHH, this.delHH);
        json.put(ListingEntry.COLUMN_NAME_HH08A, this.hh08a);
        json.put(ListingEntry.COLUMN_NAME_HH10, this.hh10);
        json.put(ListingEntry.COLUMN_NAME_HH11, this.hh11);
        json.put(ListingEntry.COLUMN_NAME_HH12, this.hh12);
        json.put(ListingEntry.COLUMN_NAME_HH13, this.hh13);
        json.put(ListingEntry.COLUMN_NAME_HH14, this.hh14);
        json.put(ListingEntry.COLUMN_NAME_HH15, this.hh15);
        json.put(ListingEntry.COLUMN_NAME_TABNO, this.tabNo);
        json.put(ListingEntry.COLUMN_ADDRESS, this.hhadd);
        json.put(ListingEntry.COLUMN_NAME_DEVICEID, this.DeviceID);
        json.put(ListingEntry.COLUMN_APPVER, this.AppVer);
        json.put(ListingEntry.COLUMN_USERNAME, this.username);
        json.put(ListingEntry.COLUMN_TAGID, this.tagId);
        json.put(ListingEntry.COLUMN_ISNEWHH, this.isNewHH);
        if (!this.counter.equals("")) {
            json.put(ListingEntry.COLUMN_COUNTER, new JSONObject(this.counter));
        }

        return json;
    }

    public ListingContract hydrate(Cursor c) {
        ListingContract lc = new ListingContract(c.getString(c.getColumnIndex(ListingEntry._ID)));
        lc.setUID(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_UID))));
        lc.setHhDT(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HHDATETIME))));
        lc.setHhDT01(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HHDATETIME01))));
        lc.setEnumCode(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_ENUMCODE))));
        lc.setClusterCode(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_CLUSTERCODE))));
        lc.setEnumStr(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_ENUMSTR))));
        lc.setHh01(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH01))));
        lc.setHh02(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH02))));
        lc.setHh03(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH03))));
        lc.setHh04(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH04))));
        lc.setHh04other(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH04OTHER))));
        lc.setHh05(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH05))));
        lc.setHh06(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH06))));
        lc.setHh07(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH07))));
        lc.setHh08(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH08))));
        lc.setHh09(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH09))));
        lc.setDelHH(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_DELHH))));
        lc.setHh08a(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH08A))));
        lc.setHh10(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH10))));
        lc.setHh11(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH11))));
        lc.setHh12(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH12))));
        lc.setHh13(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH13))));
        lc.setHh14(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH14))));
        lc.setHh15(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_HH15))));
        lc.setTabNo(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_TABNO))));
        lc.setHhadd(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_ADDRESS))));
        lc.setDeviceID(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_NAME_DEVICEID))));
        lc.setAppVer(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_APPVER))));
        lc.setTagId(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_TAGID))));
        lc.setUsername(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_USERNAME))));
        lc.setIsNewHH(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_ISNEWHH))));
        lc.setCounter(String.valueOf(c.getString(c.getColumnIndex(ListingEntry.COLUMN_COUNTER)) != null ?
                c.getString(c.getColumnIndex(ListingEntry.COLUMN_COUNTER)) : ""));

        return lc;
    }

    public static abstract class ListingEntry implements BaseColumns {

        public static final String TABLE_NAME = "listings";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_UID = "uid";
        public static final String COLUMN_NAME_HHDATETIME = "sysdate";
        public static final String COLUMN_NAME_HHDATETIME01 = "formdate";
        public static final String COLUMN_NAME_ENUMCODE = "enumcode";
        public static final String COLUMN_NAME_CLUSTERCODE = "clustercode";
        public static final String COLUMN_NAME_ENUMSTR = "enumstr";
        public static final String COLUMN_NAME_HH01 = "chkconfirm";
        public static final String COLUMN_NAME_HH02 = "hh02";
        public static final String COLUMN_NAME_HH03 = "hh03";
        public static final String COLUMN_NAME_HH04 = "hh04";
        public static final String COLUMN_NAME_HH04OTHER = "hh04other";
        public static final String COLUMN_NAME_HH05 = "hh05";
        public static final String COLUMN_NAME_HH06 = "hh06";
        public static final String COLUMN_NAME_HH07 = "hh07";
        public static final String COLUMN_NAME_HH08 = "hh08";
        public static final String COLUMN_NAME_HH09 = "hh09";
        public static final String COLUMN_NAME_HH08A = "hh08a";
        public static final String COLUMN_NAME_DELHH = "delhh";
        public static final String COLUMN_NAME_HH10 = "hh10";
        public static final String COLUMN_NAME_HH11 = "hh11";
        public static final String COLUMN_NAME_HH12 = "hh12";
        public static final String COLUMN_NAME_HH13 = "hh13";
        public static final String COLUMN_NAME_HH14 = "hh14";
        public static final String COLUMN_NAME_HH15 = "hh15";
        public static final String COLUMN_NAME_TABNO = "tabNo";
        public static final String COLUMN_ADDRESS = "hhadd";
        public static final String COLUMN_ISNEWHH = "isnewhh";
        public static final String COLUMN_COUNTER = "counter";
        public static final String COLUMN_NAME_DEVICEID = "deviceid";
        public static final String COLUMN_APPVER = "appver";
        public static final String COLUMN_TAGID = "tagId";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_USERNAME = "username";
        public static final String _URL = "sync.php";
    }
}
package edu.aku.hassannaqvi.naunehal_hhlisting_app.activities.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import edu.aku.hassannaqvi.naunehal_hhlisting_app.R;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.activities.other.LoginActivity;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.activities.other.MainActivity;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.contracts.ListingContract;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.core.DatabaseHelper;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.core.MainApp;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.databinding.ActivitySetupBinding;

import static edu.aku.hassannaqvi.naunehal_hhlisting_app.core.MainApp.lc;
import static edu.aku.hassannaqvi.naunehal_hhlisting_app.core.MainApp.userEmail;

public class SetupActivity extends Activity {
    private static final String TAG = "Setup Activity";
    private ActivitySetupBinding bi;

    @Override
    protected void onResume() {
        super.onResume();
        if (userEmail == null || userEmail.equals("")) {
            Toast.makeText(this, "Username not found. Kindly, re-start app!!", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_setup);
        bi.setCallback(this);
        this.setTitle("Structure Information");

        bi.hh02.setText(MainApp.clusterCode);
        bi.hh02.setEnabled(false);

        if (MainApp.hh02txt == null) {
            MainApp.hh03txt = 1;
        } else {
            MainApp.hh03txt++;
            bi.hh02.setText(MainApp.clusterCode);
            bi.hh02.setEnabled(false);
        }
        MainApp.hh07txt = "1";
        String StructureNumber = MainApp.tabCheck + "-" + String.format("%04d", MainApp.hh03txt);
        bi.hh03.setTextColor(Color.RED);
        bi.hh03.setText(StructureNumber);
        bi.hh07.setText(new StringBuilder(getString(R.string.hh07)).append(":").append(MainApp.hh07txt));

        bi.hh04.setOnCheckedChangeListener((group, checkedId) -> {

            if (bi.hh04a.isChecked()) {
                //Moved to Add next Family button: MainApp.hh07txt = String.valueOf((char) MainApp.hh07txt.charAt(0) + 1);
                MainApp.hh07txt = "1";
            } else {
                MainApp.hh07txt = "";
            }

            if (bi.hh04h.isChecked() || bi.hh04i.isChecked()) {
                Clear.clearAllFields(bi.fldGrpHH12);
                bi.fldGrpHH12.setVisibility(View.GONE);
                bi.btnNextStructure.setVisibility(View.GONE);
                bi.btnChangePSU.setVisibility(View.VISIBLE);
                if (bi.hh04h.isChecked()) {
                    bi.btnChangePSU.setText(R.string.logout);
                } else {
                    bi.btnChangePSU.setText(R.string.change_enumeration_block);
                }
            } else if (bi.hh04g.isChecked()) {
                Clear.clearAllFields(bi.fldGrpHH12);
                bi.fldGrpHH12.setVisibility(View.GONE);
                bi.btnNextStructure.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpHH12.setVisibility(View.VISIBLE);
                bi.btnChangePSU.setVisibility(View.GONE);
            }
        });

        bi.hh08a1.setOnCheckedChangeListener((group, checkedId) -> {
            MainApp.hh07txt = "1";
            bi.hh07.setText(new StringBuilder(getString(R.string.hh07)).append(":").append(MainApp.hh07txt));
            if (bi.hh08a1a.isChecked()) {
                bi.fldGrpHH04.setVisibility(View.VISIBLE);
                bi.btnNextStructure.setVisibility(View.GONE);
            } else {
                Clear.clearAllFields(bi.fldGrpHH04);
                bi.fldGrpHH04.setVisibility(View.GONE);
                bi.hh05.setChecked(false);
                bi.hh06.setText(null);
                bi.btnNextStructure.setVisibility(View.VISIBLE);
            }
        });

        bi.hh05.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                MainApp.hh07txt = "1";
                bi.hh07.setText(String.format("%s: %s", getString(R.string.hh07), MainApp.hh07txt));
                bi.hh06.setVisibility(View.VISIBLE);
                bi.hh06.requestFocus();

            } else {
                MainApp.hh07txt = "1";
                bi.hh07.setText(String.format("%s: %s", getString(R.string.hh07), MainApp.hh07txt));
                bi.hh06.setVisibility(View.GONE);
                bi.hh06.setText(null);
            }
        });


    }

    private void SaveDraft() {

        lc = new ListingContract();
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        lc.setTagId(sharedPref.getString("tagName", null));
        lc.setAppVer(MainApp.versionName + "." + MainApp.versionCode);
        lc.setHhDT(new SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        lc.setEnumCode(MainApp.enumCode);
        lc.setClusterCode(MainApp.clusterCode);
        lc.setHhDT01(MainApp.formDate);
        lc.setEnumStr(MainApp.enumStr);
        lc.setHh01(String.valueOf(MainApp.hh01txt));
        lc.setHh02(MainApp.hh02txt);
        lc.setHh03(String.valueOf(MainApp.hh03txt));
        lc.setHh04(
                bi.hh04a.isChecked() ? "1" :
                        bi.hh04b.isChecked() ? "2" :
                                bi.hh04c.isChecked() ? "3" :
                                        bi.hh04d.isChecked() ? "4" :
                                                bi.hh04e.isChecked() ? "5" :
                                                        bi.hh04f.isChecked() ? "6" :
                                                                bi.hh04g.isChecked() ? "7" :
                                                                        bi.hh04h.isChecked() ? "8" :
                                                                                bi.hh04i.isChecked() ? "9" :
                                                                                        bi.hh0496.isChecked() ? "96" :
                                                                                                "-1");
        lc.setHh04other(bi.hh0496x.getText().toString());
        lc.setUsername(MainApp.userEmail);
        lc.setHh05(bi.hh05.isChecked() ? "1" : "2");
        lc.setHh06(Objects.requireNonNull(bi.hh06.getText()).toString());
        lc.setHh07(MainApp.hh07txt);
        lc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        lc.setTabNo(MainApp.tabCheck);
        lc.setHh08a(bi.hh08a1a.isChecked() ? "1" : bi.hh08a1b.isChecked() ? "2" : "-1");
        lc.setDelHH(bi.hh04a.isChecked() ? "1" : "2");
        MainApp.fTotal = bi.hh06.getText().toString().isEmpty() ? 0 : Integer.parseInt(bi.hh06.getText().toString());
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSecA01);
    }

    private boolean updateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d(TAG, "UpdateDB: Structure" + lc.getHh03());

        long updcount = db.addForm(lc);

        lc.setID(String.valueOf(updcount));

        if (updcount != 0) {


            lc.setUID(
                    (lc.getDeviceID() + lc.getID()));

            db.updateListingUID();

        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void onBtnAddHHClick() {

        if (MainApp.hh02txt == null) {
            MainApp.hh02txt = bi.hh02.getText().toString();
        }
        if (formValidation()) {
            SaveDraft();
            MainApp.fCount++;
            finish();
            Intent fA = new Intent(this, FamilyListingActivity.class);
            startActivity(fA);
        }

    }

    public void onBtnChangePSUClick() {

        finish();

        Intent fA;
        if (bi.hh04h.isChecked()) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            SaveDraft();

            if (updateDB()) {
                MainApp.hh02txt = null;

                fA = new Intent(this, MainActivity.class);
                startActivity(fA);
            }
        }

    }

    public void onBtnNextStructureClick() {
        if (MainApp.hh02txt == null) {
            MainApp.hh02txt = bi.hh02.getText().toString();
        }
        if (formValidation()) {

            SaveDraft();
            if (updateDB()) {
                MainApp.fCount = 0;
                MainApp.fTotal = 0;
                MainApp.cCount = 0;
                MainApp.cTotal = 0;
                finish();
                Intent fA = new Intent(this, SetupActivity.class);
                startActivity(fA);

            }
        }
    }
}



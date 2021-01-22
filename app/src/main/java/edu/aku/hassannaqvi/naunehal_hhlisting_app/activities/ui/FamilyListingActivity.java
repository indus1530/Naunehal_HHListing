package edu.aku.hassannaqvi.naunehal_hhlisting_app.activities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.util.Locale;
import java.util.Objects;

import edu.aku.hassannaqvi.naunehal_hhlisting_app.R;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.core.DatabaseHelper;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.core.MainApp;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.databinding.ActivityFamilyListingBinding;
import edu.aku.hassannaqvi.naunehal_hhlisting_app.models.Members;

import static edu.aku.hassannaqvi.naunehal_hhlisting_app.core.MainApp.lc;

public class FamilyListingActivity extends AppCompatActivity {

    public static String TAG = "FamilyListingActivity";
    static Boolean familyFlag = false;
    ActivityFamilyListingBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_family_listing);
        bi.setCallback(this);
        Members.txtTeamNoWithFam.set(MainApp.tabCheck + "-" +
                String.format(Locale.ENGLISH, "%04d", MainApp.hh03txt)
                + "-" +
                String.format(Locale.ENGLISH, "%03d", Integer.valueOf(MainApp.hh07txt)));
        setupButtons();

        bi.isNewHH.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.btnAddNewHousehold.setVisibility(View.VISIBLE);
                bi.btnAddHousehold.setVisibility(View.GONE);
                if (MainApp.hh07txt.equals("1")) {
                    MainApp.hh07txt = "1";
                }
            } else {
                bi.btnAddNewHousehold.setVisibility(View.GONE);
                setupButtons();
                if (MainApp.fTotal == 0) {
                    if (MainApp.hh07txt.equals("1")) {
                        MainApp.hh07txt = "1";
                    }
                }
            }
            Members.txtTeamNoWithFam.set(
                    MainApp.tabCheck + "-S" +
                            String.format(Locale.ENGLISH, "%04d", MainApp.hh03txt) + "-H" +
                            String.format(Locale.ENGLISH, "%03d", Integer.valueOf(MainApp.hh07txt)));
        });

        bi.delHH.setOnCheckedChangeListener((compoundButton, b) -> Clear.clearAllFields(bi.fldGrpSecB01, !b));

    }

    public void onTextChangedHH11(CharSequence s, int start, int before, int count) {
        if (Objects.requireNonNull(bi.hh11.getText()).toString().trim().isEmpty()) return;
        bi.hh13.setMaxvalue(Float.parseFloat(bi.hh11.getText().toString()));
    }

    public void onTextChangedHH16(CharSequence s, int start, int before, int count) {
        if (Objects.requireNonNull(bi.hh09.getText()).toString().trim().isEmpty()) return;
        bi.hh11.setMaxvalue(Float.parseFloat(bi.hh09.getText().toString()) - 1);
    }

    public void setupButtons() {
        if (MainApp.fCount < MainApp.fTotal) {
            bi.btnAddFamily.setVisibility(View.VISIBLE);
            bi.btnAddHousehold.setVisibility(View.GONE);
            bi.isNewHH.setVisibility(View.GONE);
        } else {
            bi.btnAddFamily.setVisibility(View.GONE);
            bi.btnAddHousehold.setVisibility(View.VISIBLE);
            bi.isNewHH.setVisibility(View.VISIBLE);
            bi.delHH.setVisibility(View.VISIBLE);
        }
    }

    private void saveDraft() {
        lc.setHh07(MainApp.hh07txt);
        lc.setHh08(bi.hh08.getText().toString());
        lc.setHh09(bi.hh09.getText().toString());
        lc.setHh10(bi.hh10a.isChecked() ? "1" : bi.hh10b.isChecked() ? "2" : "-1");
        lc.setHh11(bi.hh11.getText().toString().isEmpty() ? "-1" : bi.hh11.getText().toString());
        lc.setHh12(bi.hh12a.isChecked() ? "1" : bi.hh12b.isChecked() ? "2" : "-1");
        lc.setHh13(bi.hh13.getText().toString().isEmpty() ? "-1" : bi.hh13.getText().toString());
        lc.setHh14(bi.hh14a.isChecked() ? "1" : bi.hh14b.isChecked() ? "2" : "-1");
        lc.setHh15(bi.hh15.getText().toString().isEmpty() ? "-1" : bi.hh15.getText().toString());

        lc.setDelHH(bi.delHH.isChecked() ? "1" : "-1");
        lc.setIsNewHH(bi.isNewHH.isChecked() ? "1" : "2");
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSecB01);
    }

    private boolean updateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        long updcount = db.addForm(lc);
        lc.setID(String.valueOf(updcount));
        if (updcount > 0) {
            lc.setUID((lc.getDeviceID() + lc.getID()));
            db.updateListingUID();
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void onBtnAddNewHouseHoldClick() {

        if (formValidation()) {

            saveDraft();
            if (updateDB()) {
                if (familyFlag)
                    MainApp.hh07txt = String.valueOf(Integer.parseInt(MainApp.hh07txt) + 1);
                else {
                    MainApp.hh07txt = String.valueOf(Integer.parseInt(MainApp.hh07txt) + 1);
                    familyFlag = true;
                }
                lc.setHh07(MainApp.hh07txt);
                MainApp.fCount++;

                finish();
                Intent fA = new Intent(this, FamilyListingActivity.class);
                startActivity(fA);
            }
        }

    }

    public void onBtnAddFamilyClick() {
        if (formValidation()) {

            saveDraft();
            if (updateDB()) {
                MainApp.hh07txt = String.valueOf(Integer.parseInt(MainApp.hh07txt) + 1);
                lc.setHh07(MainApp.hh07txt);
                MainApp.fCount++;

                finish();
                Intent fA = new Intent(this, FamilyListingActivity.class);
                startActivity(fA);
            }

        }

    }

    public void onBtnAddHouseholdClick() {
        if (formValidation()) {

            saveDraft();
            if (updateDB()) {
                MainApp.fCount = 0;
                MainApp.fTotal = 0;
                MainApp.cCount = 0;
                MainApp.cTotal = 0;
                familyFlag = false;
                finish();
                Intent fA = new Intent(this, SetupActivity.class);
                startActivity(fA);
            }
        }

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back Button NOT Allowed!", Toast.LENGTH_SHORT).show();

    }
}

package com.rumahdev.exo.chanyeol.ui.dashboard.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.R;
import com.rumahdev.exo.chanyeol.databinding.ActivityDashboardSearchBinding;
import com.rumahdev.exo.chanyeol.ui.common.PSAppCompactActivity;
import com.rumahdev.exo.chanyeol.utils.Constants;
import com.rumahdev.exo.chanyeol.utils.MyContextWrapper;
import com.rumahdev.exo.chanyeol.utils.Utils;

public class DashboardSearchActivity extends PSAppCompactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDashboardSearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_search);

        initUI(binding);
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String LANG_CURRENT = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);

        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, LANG_CURRENT, CURRENT_LANG_COUNTRY_CODE, true));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Utils.psLog("Inside Result MainActivity");
        super.onActivityResult(requestCode,resultCode,data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initUI(ActivityDashboardSearchBinding binding) {
        // setup Fragment

        initToolbar(binding.toolbar, getIntent().getStringExtra(Constants.INTENT__WALLPAPER_NAME));
        setupFragment(new DashboardSearchFragment());
    }

}

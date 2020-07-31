package com.rumahdev.exo.chanyeol.ui.privacypolicy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.databinding.DataBindingUtil;

import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.R;
import com.rumahdev.exo.chanyeol.databinding.ActivityPrivacyPolicyBinding;
import com.rumahdev.exo.chanyeol.ui.common.PSAppCompactActivity;
import com.rumahdev.exo.chanyeol.utils.Constants;
import com.rumahdev.exo.chanyeol.utils.MyContextWrapper;
//import com.cruetiktok.pswallpaper.databinding.ActivityPrivacyPolicyBinding;

public class PrivacyPolicyActivity extends PSAppCompactActivity {


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPrivacyPolicyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_privacy_policy);

        // Init all UI
        initUI(binding);

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        String LANG_CURRENT = preferences.getString(Constants.LANGUAGE_CODE, Config.DEFAULT_LANGUAGE);

        String CURRENT_LANG_COUNTRY_CODE = preferences.getString(Constants.LANGUAGE_COUNTRY_CODE, Config.DEFAULT_LANGUAGE_COUNTRY_CODE);

        super.attachBaseContext(MyContextWrapper.wrap(newBase, LANG_CURRENT, CURRENT_LANG_COUNTRY_CODE, true));

    }
    //endregion


    //region Private Methods

    private void initUI(ActivityPrivacyPolicyBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar, getResources().getString(R.string.menu__privacy_policy));

        // setup Fragment
        setupFragment(new PrivacyPolicyFragment());

    }
}

//endregion



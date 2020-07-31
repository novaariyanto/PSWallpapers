package com.rumahdev.exo.chanyeol.ui.apploading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.R;
import com.rumahdev.exo.chanyeol.binding.FragmentDataBindingComponent;
import com.rumahdev.exo.chanyeol.databinding.FragmentAppLoadingBinding;
import com.rumahdev.exo.chanyeol.ui.common.PSFragment;
import com.rumahdev.exo.chanyeol.utils.AutoClearedValue;
import com.rumahdev.exo.chanyeol.utils.Constants;
import com.rumahdev.exo.chanyeol.utils.PSDialogMsg;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewmodel.apploading.AppLoadingViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.clearalldata.ClearAllDataViewModel;
import com.rumahdev.exo.chanyeol.viewobject.PSAppInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppLoadingFragment extends PSFragment {


    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private PSDialogMsg psDialogMsg;
    private String startDate = Constants.ZERO;
    private String endDate = Constants.ZERO;

    private AppLoadingViewModel appLoadingViewModel;
    private ClearAllDataViewModel clearAllDataViewModel;

    @VisibleForTesting
    private AutoClearedValue<FragmentAppLoadingBinding> binding;

    //endregion Variables

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAppLoadingBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_loading, container, false, dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }


    @Override
    protected void initUIAndActions() {

        psDialogMsg = new PSDialogMsg(getActivity(), false);

    }

    @Override
    protected void initViewModels() {
        appLoadingViewModel = new ViewModelProvider(this, viewModelFactory).get(AppLoadingViewModel.class);
        clearAllDataViewModel = new ViewModelProvider(this, viewModelFactory).get(ClearAllDataViewModel.class);
    }

    @Override
    protected void initAdapters() {
    }

    @Override
    protected void initData() {

        if (connectivity.isConnected()) {
            if (startDate.equals(Constants.ZERO)) {

                startDate = getDateTime();
                Utils.setDatesToShared(startDate, endDate, pref);
            }

            endDate = getDateTime();
            appLoadingViewModel.setDeleteHistoryObj(startDate, endDate);

        } else {
            navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());

            if (getActivity() != null) {
                getActivity().finish();
            }
        }

        appLoadingViewModel.getDeleteHistoryData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {

                    case SUCCESS:

                        if (result.data != null) {
                            appLoadingViewModel.psAppInfo = result.data;
                            checkVersionNumber(result.data);
                            startDate = endDate;
                        }
                        break;

                    case ERROR:

                        break;
                }
            }

        });

        clearAllDataViewModel.getDeleteAllDataData().observe(this, result -> {

            if (result != null) {
                switch (result.status) {

                    case ERROR:
                        break;

                    case SUCCESS:
                        checkForceUpdate(appLoadingViewModel.psAppInfo);
                        break;
                }
            }
        });

    }

    private void checkVersionNumber(PSAppInfo psAppInfo) {
        if (!Config.APP_VERSION.equals(psAppInfo.psAppVersion.versionNo)) {
//            if (psAppInfo.psAppVersion.versionForceUpdate.equals(Constants.ONE)) {
//
//                pref.edit().putString(Constants.APPINFO_PREF_VERSION_NO, psAppInfo.psAppVersion.versionNo).apply();
//                pref.edit().putBoolean(Constants.APPINFO_PREF_FORCE_UPDATE, true).apply();
//                pref.edit().putString(Constants.APPINFO_FORCE_UPDATE_TITLE, psAppInfo.psAppVersion.versionTitle).apply();
//                pref.edit().putString(Constants.APPINFO_FORCE_UPDATE_MSG, psAppInfo.psAppVersion.versionMessage).apply();
//
//                navigationController.navigateToForceUpdateActivity(this.getActivity(), psAppInfo.psAppVersion.versionTitle, psAppInfo.psAppVersion.versionMessage);
//
//            } else if (psAppInfo.psAppVersion.versionForceUpdate.equals(Constants.ZERO)) {
//
//                pref.edit().putBoolean(Constants.APPINFO_PREF_FORCE_UPDATE, false).apply();
//
//                psDialogMsg.showAppInfoDialog(getString(R.string.force_update__button_update), getString(R.string.message__cancel_close), psAppInfo.psAppVersion.versionTitle, psAppInfo.psAppVersion.versionMessage);
//                psDialogMsg.show();
//                psDialogMsg.okButton.setOnClickListener(v -> {
//                    psDialogMsg.cancel();
//                    navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());
//                    navigationController.navigateToPlayStore(AppLoadingFragment.this.getActivity());
//                    if (getActivity() != null) {
//                        getActivity().finish();
//                    }
//
//                });
//
//                psDialogMsg.cancelButton.setOnClickListener(v -> {
//                    psDialogMsg.cancel();
//
//                    navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());
//                    if (AppLoadingFragment.this.getActivity() != null) {
//                        AppLoadingFragment.this.getActivity().finish();
//                    }
//                });
//
//                psDialogMsg.getDialog().setCancelable(false);
//
//            }

            if (psAppInfo.psAppVersion.versionNeedClearData.equals(Constants.ONE)) {
                psDialogMsg.cancel();
                clearAllDataViewModel.setDeleteAllDataObj();
            } else {
                checkForceUpdate(appLoadingViewModel.psAppInfo);
            }
        } else {
            pref.edit().putBoolean(Constants.APPINFO_PREF_FORCE_UPDATE, false).apply();
            navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());
            if (getActivity() != null) {
                getActivity().finish();
            }
        }

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void checkForceUpdate(PSAppInfo psAppInfo) {
//        if (!Config.APP_VERSION.equals(psAppInfo.psAppVersion.versionNo)) {
            if (psAppInfo.psAppVersion.versionForceUpdate.equals(Constants.ONE)) {

                pref.edit().putString(Constants.APPINFO_PREF_VERSION_NO, psAppInfo.psAppVersion.versionNo).apply();
                pref.edit().putBoolean(Constants.APPINFO_PREF_FORCE_UPDATE, true).apply();
                pref.edit().putString(Constants.APPINFO_FORCE_UPDATE_MSG, psAppInfo.psAppVersion.versionMessage).apply();
                pref.edit().putString(Constants.APPINFO_FORCE_UPDATE_TITLE, psAppInfo.psAppVersion.versionTitle).apply();

                navigationController.navigateToForceUpdateActivity(this.getActivity(), psAppInfo.psAppVersion.versionMessage, psAppInfo.psAppVersion.versionTitle);

            } else if (psAppInfo.psAppVersion.versionForceUpdate.equals(Constants.ZERO)) {

                pref.edit().putBoolean(Constants.APPINFO_PREF_FORCE_UPDATE, false).apply();
                psDialogMsg.showAppInfoDialog(getString(R.string.force_update__button_update), getString(R.string.message__cancel_close), psAppInfo.psAppVersion.versionTitle, psAppInfo.psAppVersion.versionMessage);
                psDialogMsg.show();


                psDialogMsg.okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        psDialogMsg.cancel();
                        navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());
                        navigationController.navigateToPlayStore(getActivity());
                        if (AppLoadingFragment.this.getActivity() != null) {
                            AppLoadingFragment.this.getActivity().finish();
                        }
                    }
                });

                psDialogMsg.cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        psDialogMsg.cancel();

                        navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());
                        if (AppLoadingFragment.this.getActivity() != null) {
                            AppLoadingFragment.this.getActivity().finish();
                        }
                    }
                });
                psDialogMsg.getDialog().setCancelable(false);
            }else {
                navigationController.navigateToMainActivity(AppLoadingFragment.this.getActivity());
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
//        }
    }
}



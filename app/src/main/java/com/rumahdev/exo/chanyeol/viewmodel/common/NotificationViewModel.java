package com.rumahdev.exo.chanyeol.viewmodel.common;

import android.content.Context;

import com.rumahdev.exo.chanyeol.repository.aboutus.AboutUsRepository;
import com.rumahdev.exo.chanyeol.ui.common.BackgroundTaskHandler;
import com.rumahdev.exo.chanyeol.ui.common.NotificationTaskHandler;
import com.rumahdev.exo.chanyeol.utils.Utils;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

/**
 * Created by Panacea-Soft on 1/4/18.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class NotificationViewModel extends PSViewModel {

    private final NotificationTaskHandler backgroundTaskHandler;

    public boolean pushNotificationSetting = false;
    public boolean isLoading = false;

    @Inject
    NotificationViewModel(AboutUsRepository repository) {
        Utils.psLog("Inside NewsViewModel");

        backgroundTaskHandler = new NotificationTaskHandler(repository);
    }

    public void registerNotification(Context context, String platform, String token) {

        if(token == null || platform == null) return;

        if(platform.equals("")) return;

        backgroundTaskHandler.registerNotification(context, platform, token);
    }

    public void unregisterNotification(Context context, String platform, String token) {

        if(token == null || platform == null) return;

        if(platform.equals("")) return;

        backgroundTaskHandler.unregisterNotification(context, platform, token);
    }

    public LiveData<BackgroundTaskHandler.LoadingState> getLoadingStatus() {
        return backgroundTaskHandler.getLoadingState();
    }



}

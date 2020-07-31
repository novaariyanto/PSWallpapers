package com.rumahdev.exo.chanyeol.viewmodel.contactus;


import com.rumahdev.exo.chanyeol.repository.contactus.ContactUsRepository;
import com.rumahdev.exo.chanyeol.ui.common.BackgroundTaskHandler;
import com.rumahdev.exo.chanyeol.ui.contactus.ContactUsBackgroundTaskHandler;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewmodel.common.PSViewModel;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

/**
 * Created by Panacea-Soft on 6/2/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */

public class ContactUsViewModel extends PSViewModel {

    private final ContactUsBackgroundTaskHandler backgroundTaskHandler;
    public boolean isLoading = false;
    public String contactName = "";
    public String contactEmail = "";
    public String contactDesc = "";
    public String contactPhone = "";


    @Inject
    ContactUsViewModel(ContactUsRepository repository) {
        Utils.psLog("Inside ContactUsViewModel");
        backgroundTaskHandler = new ContactUsBackgroundTaskHandler(repository);
    }

    //for news pagination
    public LiveData<BackgroundTaskHandler.LoadingState> getLoadingStatus() {
        return backgroundTaskHandler.getLoadingState();
    }

    public void postContactUs(String apiKey, String contactName, String contactEmail, String contactDesc, String contactPhone) {
        backgroundTaskHandler.postContactUs(apiKey, contactName, contactEmail, contactDesc, contactPhone);
    }

}

package com.rumahdev.exo.chanyeol.viewmodel.aboutus;


import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.repository.aboutus.AboutUsRepository;
import com.rumahdev.exo.chanyeol.utils.AbsentLiveData;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewmodel.common.PSViewModel;
import com.rumahdev.exo.chanyeol.viewobject.AboutUs;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;
import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

/**
 * Created by Panacea-Soft on 12/30/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class AboutUsViewModel extends PSViewModel {


    //region Variables

    // Get AboutUs
    private final LiveData<Resource<AboutUs>> aboutUsLiveData;
    private MutableLiveData<String> aboutUsObj = new MutableLiveData<>();

    //endregion


    //region Constructors

    @Inject
    AboutUsViewModel(AboutUsRepository repository) {
        Utils.psLog("AboutUs ViewModel...");

        aboutUsLiveData = Transformations.switchMap(aboutUsObj, newsId -> {
            if (newsId.isEmpty()) {
                return AbsentLiveData.create();
            }
            return repository.getAboutUs(Config.API_KEY);
        });

    }

    //endregion


    //region Public Methods

    public void setAboutUsObj(String aboutUsObj) {
        this.aboutUsObj.setValue(aboutUsObj);
    }

    public LiveData<Resource<AboutUs>> getAboutUsData() {
        return aboutUsLiveData;
    }

    //endregion

}

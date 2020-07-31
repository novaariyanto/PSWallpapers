package com.rumahdev.exo.chanyeol.repository.contactus.task;



import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.api.ApiResponse;
import com.rumahdev.exo.chanyeol.api.PSApiService;
import com.rumahdev.exo.chanyeol.db.PSCoreDb;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewobject.ApiStatus;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Response;

/**
 * Created by Panacea-Soft on 7/2/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */

public class PostContactUsTask implements Runnable {

    //region Variables
    private final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

    private final PSApiService service;
    private final PSCoreDb db;
    private final String contactName;
    private final String contactEmail;
    private final String contactDesc;
    private final String contactPhone;

    public PostContactUsTask(PSApiService service, PSCoreDb db, String contactName, String contactEmail, String contactDesc, String contactPhone) {
        this.service = service;
        this.db = db;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactDesc = contactDesc;
        this.contactPhone = contactPhone;
    }

    //region Override Methods

    @Override
    public void run() {
        try {

            // Call the API Service
            Response<ApiStatus> response = service.rawPostContact(Config.API_KEY, contactName, contactEmail, contactDesc, contactPhone).execute();

            // Wrap with APIResponse Class
            ApiResponse<ApiStatus> apiResponse = new ApiResponse<>(response);

            Utils.psLog("apiResponse " + apiResponse);
            // If response is successful
            if (apiResponse.isSuccessful()) {

                statusLiveData.postValue(Resource.success(true));
            } else {

                statusLiveData.postValue(Resource.error(apiResponse.errorMessage, true));
            }
        } catch (Exception e) {
            statusLiveData.postValue(Resource.error(e.getMessage(), true));
        }
    }

    //endregion

    //region public methods

    /**
     * This function will return Status of Process
     * @return statusLiveData
     */
    public LiveData<Resource<Boolean>> getStatusLiveData() {
        return statusLiveData;
    }

    //endregion

}

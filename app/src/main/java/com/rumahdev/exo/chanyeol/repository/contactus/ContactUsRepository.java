package com.rumahdev.exo.chanyeol.repository.contactus;


import com.rumahdev.exo.chanyeol.AppExecutors;
import com.rumahdev.exo.chanyeol.api.PSApiService;
import com.rumahdev.exo.chanyeol.db.PSCoreDb;
import com.rumahdev.exo.chanyeol.repository.common.PSRepository;
import com.rumahdev.exo.chanyeol.repository.contactus.task.PostContactUsTask;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

/**
 * Created by Panacea-Soft on 7/2/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */

public class ContactUsRepository extends PSRepository {

    @Inject
    ContactUsRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);

    }

    /**
     * Post Contact Us
     * @param apiKey APIKey to access Web Service
     * @param contactName Name
     * @param contactEmail Email
     * @param contactDesc Desc
     * @return Status of Post
     */
    public LiveData<Resource<Boolean>> postContactUs(String apiKey, String contactName, String contactEmail, String contactDesc, String contactPhone) {

        PostContactUsTask postContactUsTask = new PostContactUsTask(
                psApiService,  db, contactName, contactEmail, contactDesc, contactPhone);

        appExecutors.networkIO().execute(postContactUsTask);

        return postContactUsTask.getStatusLiveData();

    }

}

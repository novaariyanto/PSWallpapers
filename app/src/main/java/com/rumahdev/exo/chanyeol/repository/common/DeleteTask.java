package com.rumahdev.exo.chanyeol.repository.common;

import com.rumahdev.exo.chanyeol.api.PSApiService;
import com.rumahdev.exo.chanyeol.db.PSCoreDb;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewobject.User;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


/**
 * General Delete Task Sample
 * Created by Panacea-Soft on 12/14/17.
 * Contact Email : teamps.is.cool@gmail.com
 */
public class DeleteTask implements Runnable {


    //region Variables

    private final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

    private final PSApiService service;
    private final PSCoreDb db;
    private final Object obj;

    //endregion


    //region Constructor

    public DeleteTask(PSApiService service, PSCoreDb db, Object obj) {
        this.service = service;
        this.db = db;
        this.obj = obj;
    }

    //endregion


    //region Override Methods

    @Override
    public void run() {
        try {
//            try {
//                db.beginTransaction();
//
//                if (obj instanceof User) {
//                    db.userDao().deleteUserLogin();
//
//                    db.setTransactionSuccessful();
//                }
//
//            } finally {
//                db.endTransaction();
//            }

            try {
                db.runInTransaction(() -> {
                    if (obj instanceof User) {
                        db.userDao().deleteUserLogin();

                    }
                });
            } catch (Exception ex) {
                Utils.psErrorLog("Error at ", ex);
            }

            statusLiveData.postValue(Resource.success(true));
        } catch (Exception e) {
            statusLiveData.postValue(Resource.error(e.getMessage(), true));
        }
    }

    //endregion


    //region public SyncCategory Methods

    /**
     * This function will return Status of Process
     *
     * @return statusLiveData
     */
    public LiveData<Resource<Boolean>> getStatusLiveData() {
        return statusLiveData;
    }

    //endregion
}
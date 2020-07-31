package com.rumahdev.exo.chanyeol.repository.clearpackage;

import com.rumahdev.exo.chanyeol.AppExecutors;
import com.rumahdev.exo.chanyeol.api.PSApiService;
import com.rumahdev.exo.chanyeol.db.PSCoreDb;
import com.rumahdev.exo.chanyeol.repository.common.PSRepository;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ClearPackageRepository extends PSRepository {

    @Inject
    ClearPackageRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db) {
        super(psApiService, appExecutors, db);

        Utils.psLog("Inside CategoryRepository");
    }

    public LiveData<Resource<Boolean>> clearAllTheData() {

        final MutableLiveData<Resource<Boolean>> statusLiveData = new MutableLiveData<>();

        appExecutors.networkIO().execute(() -> {

//            try {
//                db.beginTransaction();
//
//                db.categoryDao().deleteTable();
//                db.colorDao().deleteColor();
//                db.deletedObjectDao().deleteAll();
//                db.imageDao().deleteTable();
//                db.psAppInfoDao().deleteAll();
//                db.psAppVersionDao().deleteAll();
//
//                db.wallpaperMapDao().deleteAll();
//                db.wallpaperMapDao().deleteAll();
//
//                db.setTransactionSuccessful();
//            } catch (NullPointerException ne) {
//                Utils.psErrorLog("Null Pointer Exception : ", ne);
//
//                statusLiveData.postValue(Resource.error(ne.getMessage(), false));
//            } catch (Exception e) {
//                Utils.psErrorLog("Exception : ", e);
//
//                statusLiveData.postValue(Resource.error(e.getMessage(), false));
//            } finally {
//                db.endTransaction();
//            }

            try {
                db.runInTransaction(() -> {

                    db.categoryDao().deleteTable();
                    db.colorDao().deleteColor();
                    db.deletedObjectDao().deleteAll();
                    db.imageDao().deleteTable();
                    db.psAppInfoDao().deleteAll();
                    db.psAppVersionDao().deleteAll();

                    db.wallpaperMapDao().deleteAll();
                    db.wallpaperMapDao().deleteAll();

                });
            } catch (Exception ex) {
                Utils.psErrorLog("Error at ", ex);
                statusLiveData.postValue(Resource.error(ex.getMessage(), false));
            }

            statusLiveData.postValue(Resource.success(true));


        });

        return statusLiveData;
    }

}

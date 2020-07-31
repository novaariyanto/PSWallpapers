package com.rumahdev.exo.chanyeol.repository.color;

import com.rumahdev.exo.chanyeol.AppExecutors;
import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.api.ApiResponse;
import com.rumahdev.exo.chanyeol.api.PSApiService;
import com.rumahdev.exo.chanyeol.db.ColorDao;
import com.rumahdev.exo.chanyeol.db.PSCoreDb;
import com.rumahdev.exo.chanyeol.repository.common.NetworkBoundResource;
import com.rumahdev.exo.chanyeol.repository.common.PSRepository;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewobject.Color;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * Created by Panacea-Soft on 2/10/19.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class ColorRepository extends PSRepository {

    private final ColorDao colorDao;

    @Inject
    protected ColorRepository(PSApiService psApiService, AppExecutors appExecutors, PSCoreDb db, ColorDao colorDao) {
        super(psApiService, appExecutors, db);
        this.colorDao = colorDao;
    }

    //GetColor
    public LiveData<Resource<List<Color>>> getColorList(String limit, String offset) {

        String functionKey = "getColorList";

        return new NetworkBoundResource<List<Color>, List<Color>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Color> item) {
                Utils.psLog("SaveCallResult of get color list");

//                db.beginTransaction();
//                try {
//
//                    db.colorDao().deleteColor();
//
//                    colorDao.insert(item);
//
//                    db.setTransactionSuccessful();
//
//                } catch (Exception e) {
//                    Utils.psErrorLog("Error in inserting color", e);
//                } finally {
//                    db.endTransaction();
//                }

                try {
                    db.runInTransaction(() -> {
                        db.colorDao().deleteColor();

                        colorDao.insert(item);
                    });
                } catch (Exception ex) {
                    Utils.psErrorLog("Error at ", ex);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Color> data) {
                return connectivity.isConnected();
            }

            @NonNull
            @Override
            protected LiveData<List<Color>> loadFromDb() {
                Utils.psLog("Load color list From DB.");

                return db.colorDao().getColorList();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Color>>> createCall() {
                Utils.psLog("Call color list webservice.");

                return psApiService.getColorList(Config.API_KEY, limit, offset);

            }

            @Override
            protected void onFetchFailed(String message) {
                Utils.psLog("Fetch Failed of category list ");
                rateLimiter.reset(functionKey);
            }

        }.asLiveData();
    }
    //endregion

    //Get Color
    public LiveData<Resource<Boolean>> getNextPageColorList(String limit, String offset) {

        final MediatorLiveData<Resource<Boolean>> statusLiveData = new MediatorLiveData<>();

        LiveData<ApiResponse<List<Color>>> apiResponse = psApiService.getColorList(Config.API_KEY, limit, offset);

        statusLiveData.addSource(apiResponse, response -> {

            statusLiveData.removeSource(apiResponse);

            //noinspection Constant Conditions
            if (response.isSuccessful()) {

                appExecutors.diskIO().execute(() -> {
//
//                    try {
//                        db.beginTransaction();
//
//                        colorDao.insert(response.body);
//
//
//                        db.setTransactionSuccessful();
//
//                    } catch (NullPointerException ne) {
//                        Utils.psErrorLog("Null Pointer Exception : ", ne);
//                    } catch (Exception e) {
//                        Utils.psErrorLog("Exception : ", e);
//                    } finally {
//                        db.endTransaction();
//                    }

                    try {
                        db.runInTransaction(() -> colorDao.insert(response.body));
                    } catch (Exception ex) {
                        Utils.psErrorLog("Error at ", ex);
                    }

                    statusLiveData.postValue(Resource.success(true));
                });

            } else {
                statusLiveData.postValue(Resource.error(response.errorMessage, null));
            }

        });

        return statusLiveData;

    }
    //endregion
}

package com.rumahdev.exo.chanyeol.viewmodel.point;

import com.rumahdev.exo.chanyeol.repository.point.PointRepository;
import com.rumahdev.exo.chanyeol.utils.AbsentLiveData;
import com.rumahdev.exo.chanyeol.viewmodel.common.PSViewModel;
import com.rumahdev.exo.chanyeol.viewobject.DailyPoint;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;
import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PointViewModel extends PSViewModel {

    private LiveData<Resource<Boolean>> insertData;
    private MutableLiveData<DailyPoint> insertObj = new MutableLiveData<>();

    private LiveData<Resource<Boolean>> claimStatusData;
    private MutableLiveData<DailyPoint> claimStatusObj = new MutableLiveData<>();

    private LiveData<Resource<Boolean>> sendClaimedPointToServerData;
    private MutableLiveData<PointViewModel.TmpDataHolder> sendClaimedPointToServerObj = new MutableLiveData<>();

    @Inject
    PointViewModel(PointRepository repository)
    {
        insertData = Transformations.switchMap(insertObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.insertDailyPoint(obj);
        });

        claimStatusData = Transformations.switchMap(claimStatusObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.checkClaimStatus(obj);
        });

        sendClaimedPointToServerData = Transformations.switchMap(sendClaimedPointToServerObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.uploadClaimedPointToServer(obj.loginUserId, obj.claimedPoint);
        });
    }

    public void setInsertObj(DailyPoint dailyPoint){
        this.insertObj.setValue(dailyPoint);
    }

    public LiveData<Resource<Boolean>> getInsertData()
    {
        return this.insertData;
    }

    public void setClaimStatusObj(DailyPoint dailyPoint){
        this.claimStatusObj.setValue(dailyPoint);
    }

    public LiveData<Resource<Boolean>> getClaimStatusData()
    {
        return this.claimStatusData;
    }

    public void setSendClaimedPointToServerObj(String loginuserId, String claimedPoint){
        TmpDataHolder tmpDataHolder = new TmpDataHolder();
        tmpDataHolder.loginUserId = loginuserId;
        tmpDataHolder.claimedPoint = claimedPoint;

        this.sendClaimedPointToServerObj.setValue(tmpDataHolder);
    }

    public LiveData<Resource<Boolean>> getSendClaimedPointToServerData()
    {
        return this.sendClaimedPointToServerData;
    }

    class TmpDataHolder {
        String loginUserId = "";
        String claimedPoint = "";
    }
}

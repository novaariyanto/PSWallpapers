package com.rumahdev.exo.chanyeol.viewmodel.favourite;

import com.rumahdev.exo.chanyeol.repository.wallpaper.WallpaperRepository;
import com.rumahdev.exo.chanyeol.utils.AbsentLiveData;
import com.rumahdev.exo.chanyeol.viewmodel.common.PSViewModel;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;
import com.rumahdev.exo.chanyeol.viewobject.holder.WallpaperParamsHolder;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;


public class FavouriteViewModel extends PSViewModel {

    private final LiveData<Resource<Boolean>> sendFavouritePostData;
    private MutableLiveData<FavouriteViewModel.TmpDataHolder> sendFavouriteDataPostObj = new MutableLiveData<>();
    public WallpaperParamsHolder favQueryListWallpaperParamsHolder = new WallpaperParamsHolder().getFavQueryHolder();

    @Inject
    public FavouriteViewModel(WallpaperRepository wallpaperRepository) {
        sendFavouritePostData = Transformations.switchMap(sendFavouriteDataPostObj, obj -> {

            if (obj == null) {
                return AbsentLiveData.create();
            }
            return wallpaperRepository.uploadFavouritePostToServer(obj.wallpaperId, obj.loginUserId);
        });
    }

    public void setFavouritePostDataObj(String wallpaperId, String loginUserId) {

        FavouriteViewModel.TmpDataHolder tmpDataHolder = new FavouriteViewModel.TmpDataHolder();
        tmpDataHolder.wallpaperId = wallpaperId;
        tmpDataHolder.loginUserId = loginUserId;

        sendFavouriteDataPostObj.setValue(tmpDataHolder);

    }

    public LiveData<Resource<Boolean>> getFavouritePostData() {
        return sendFavouritePostData;
    }

    class TmpDataHolder {
        public String wallpaperId = "";
        public String loginUserId = "";
    }
}
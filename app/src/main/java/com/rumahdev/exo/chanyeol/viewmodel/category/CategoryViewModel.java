package com.rumahdev.exo.chanyeol.viewmodel.category;


import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.repository.category.CategoryRepository;
import com.rumahdev.exo.chanyeol.utils.AbsentLiveData;
import com.rumahdev.exo.chanyeol.utils.Utils;
import com.rumahdev.exo.chanyeol.viewmodel.common.PSViewModel;
import com.rumahdev.exo.chanyeol.viewobject.Category;
import com.rumahdev.exo.chanyeol.viewobject.common.Resource;
import com.rumahdev.exo.chanyeol.viewobject.holder.WallpaperParamsHolder;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class CategoryViewModel extends PSViewModel {
    private LiveData<Resource<List<Category>>> categoryListLiveData;
    private MutableLiveData<TmpDataHolder> categoryListObj = new MutableLiveData<>();

    private final LiveData<Resource<Boolean>> nextPageCategoryListData;
    private MutableLiveData<CategoryViewModel.TmpDataHolder> nextPageLoadingStateObj = new MutableLiveData<>();

    public String catId;

    public WallpaperParamsHolder wallpaperParamsHolder = new WallpaperParamsHolder();

    @Inject
    CategoryViewModel(CategoryRepository repository) {
        Utils.psLog("CategoryViewModel...");

        categoryListLiveData = Transformations.switchMap(categoryListObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            return repository.getCategoryList(Config.API_KEY, obj.limit, obj.offset);
        });

        nextPageCategoryListData = Transformations.switchMap(nextPageLoadingStateObj, obj -> {
            if (obj == null) {
                return AbsentLiveData.create();
            }
            Utils.psLog("ItemInquiry List.");
            return repository.getNextPageCategoryList(Config.API_KEY, obj.limit, obj.offset);
        });
    }

    //region all wallpapers
    public void setCategoryList(String limit, String offset) {
        CategoryViewModel.TmpDataHolder tmpDataHolder = new CategoryViewModel.TmpDataHolder();
        tmpDataHolder.limit = limit;
        tmpDataHolder.offset = offset;
        categoryListObj.setValue(tmpDataHolder);

        setLoadingState(true);

    }

    public LiveData<Resource<List<Category>>> getAllCategoryList() {
        return categoryListLiveData;
    }


    public LiveData<Resource<Boolean>> getNextPageLoadingStateData() {
        return nextPageCategoryListData;
    }

    public void setNextPageLoadingStateObj(String limit, String offset) {
        if (!isLoading) {
            TmpDataHolder tmpDataHolder = new TmpDataHolder();
            tmpDataHolder.limit = limit;
            tmpDataHolder.offset = offset;
            nextPageLoadingStateObj.setValue(tmpDataHolder);
            // start loading
            setLoadingState(true);
        }
    }

    // endregion

    class TmpDataHolder {
        public String limit = "";
        public String offset = "";

    }
}

package com.rumahdev.exo.chanyeol.di;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rumahdev.exo.chanyeol.viewmodel.aboutus.AboutUsViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.apploading.AppLoadingViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.category.CategoryViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.clearalldata.ClearAllDataViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.color.ColorViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.common.NotificationViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.common.PSNewsViewModelFactory;
import com.rumahdev.exo.chanyeol.viewmodel.contactus.ContactUsViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.favourite.FavouriteViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.livewallpaper.latest.LatestLiveWallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.point.PointViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.user.UserViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.WallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.downloadcount.DownloadCountViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.feature.FeatureViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.gif.GifWallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.landscape.LandscapeWallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.latest.LatestWallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.portrait.PortraitWallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.square.SquareWallpaperViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.touchcount.TouchCountViewModel;
import com.rumahdev.exo.chanyeol.viewmodel.wallpaper.trending.TrendingViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Panacea-Soft on 11/16/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PSNewsViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel userViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel.class)
    abstract ViewModel bindAboutUsViewModel(AboutUsViewModel aboutUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PortraitWallpaperViewModel.class)
    abstract ViewModel bindPortraitWallpaperViewModel(PortraitWallpaperViewModel portraitWallpaperViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LandscapeWallpaperViewModel.class)
    abstract ViewModel bindLandScapeWallpaperViewModel(LandscapeWallpaperViewModel landScapeWallpaperViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SquareWallpaperViewModel.class)
    abstract ViewModel bindSquareWallpaperViewModel(SquareWallpaperViewModel squareWallpaperViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LatestWallpaperViewModel.class)
    abstract ViewModel bindLatestWallpaperViewModel(LatestWallpaperViewModel latestWallpaperViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel.class)
    abstract ViewModel bindNotificationViewModel(NotificationViewModel notificationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel.class)
    abstract ViewModel bindContactUsViewModel(ContactUsViewModel contactUsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FeatureViewModel.class)
    abstract ViewModel bindFeatureViewModel(FeatureViewModel featureViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel.class)
    abstract ViewModel bindTrendingViewModel(TrendingViewModel trendingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WallpaperViewModel.class)
    abstract ViewModel bindDashboardViewModel(WallpaperViewModel wallpaperViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(CategoryViewModel categoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TouchCountViewModel.class)
    abstract ViewModel bindTouchCountViewModel(TouchCountViewModel touchCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel.class)
    abstract ViewModel bindFavouriteViewModel(FavouriteViewModel favouriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ColorViewModel.class)
    abstract ViewModel bindColorViewModel(ColorViewModel colorViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PointViewModel.class)
    abstract ViewModel bindPointViewModel(PointViewModel pointViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DownloadCountViewModel.class)
    abstract ViewModel binddDownloadCountViewModel(DownloadCountViewModel downloadCountViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ClearAllDataViewModel.class)
    abstract ViewModel binddClearAllDataViewModel(ClearAllDataViewModel clearAllDataViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AppLoadingViewModel.class)
    abstract ViewModel binddPSAppInfoViewModel(AppLoadingViewModel psAppInfoViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(GifWallpaperViewModel.class)
    abstract ViewModel binddPSGifWallpaperViewModel(GifWallpaperViewModel psGifWallpaperViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LatestLiveWallpaperViewModel.class)
    abstract ViewModel binddLatestLiveWallpaperViewModel(LatestLiveWallpaperViewModel latestLiveWallpaperViewModel);
}

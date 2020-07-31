package com.rumahdev.exo.chanyeol.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rumahdev.exo.chanyeol.Config;
import com.rumahdev.exo.chanyeol.api.PSApiService;
import com.rumahdev.exo.chanyeol.db.AboutUsDao;
import com.rumahdev.exo.chanyeol.db.CategoryDao;
import com.rumahdev.exo.chanyeol.db.ColorDao;
import com.rumahdev.exo.chanyeol.db.DeletedObjectDao;
import com.rumahdev.exo.chanyeol.db.ImageDao;
import com.rumahdev.exo.chanyeol.db.PSAppInfoDao;
import com.rumahdev.exo.chanyeol.db.PSAppVersionDao;
import com.rumahdev.exo.chanyeol.db.PSCoreDb;
import com.rumahdev.exo.chanyeol.db.PointDao;
import com.rumahdev.exo.chanyeol.db.UploadWallpaperDao;
import com.rumahdev.exo.chanyeol.db.UserDao;
import com.rumahdev.exo.chanyeol.db.WallpaperDao;
import com.rumahdev.exo.chanyeol.db.WallpaperMapDao;
import com.rumahdev.exo.chanyeol.utils.Connectivity;
import com.rumahdev.exo.chanyeol.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Panacea-Soft on 11/15/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    PSApiService providePSNewsService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Config.APP_API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(PSApiService.class);

    }

    @Singleton
    @Provides
    PSCoreDb provideDb(Application app) {
        return Room.databaseBuilder(app, PSCoreDb.class, "pswallpapers.db")
                //.addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    Connectivity provideConnectivity(Application app) {
        return new Connectivity(app);
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application app) {
        return PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
    }

    @Singleton
    @Provides
    UserDao provideUserDao(PSCoreDb db) {
        return db.userDao();
    }

    @Singleton
    @Provides
    AboutUsDao provideAboutUsDao(PSCoreDb db) {
        return db.aboutUsDao();
    }

    @Singleton
    @Provides
    ImageDao provideImageDao(PSCoreDb db) {
        return db.imageDao();
    }

    @Singleton
    @Provides
    WallpaperDao provideWallpaperDao(PSCoreDb db) {
        return db.wallpaperDao();
    }

    @Singleton
    @Provides
    CategoryDao provideCategoryDao(PSCoreDb db) {
        return db.categoryDao();
    }

    @Singleton
    @Provides
    ColorDao provideColorDao(PSCoreDb db) {
        return db.colorDao();
    }

    @Singleton
    @Provides
    PointDao providePointDao(PSCoreDb db) {
        return db.pointDao();
    }

    @Singleton
    @Provides
    WallpaperMapDao provideWallpaperMapDao(PSCoreDb db) {
        return db.wallpaperMapDao();
    }

    @Singleton
    @Provides
    DeletedObjectDao provideDeletedObjectDao(PSCoreDb db) {
        return db.deletedObjectDao();
    }

    @Singleton
    @Provides
    PSAppVersionDao providePSAppVersionDao(PSCoreDb db) {
        return db.psAppVersionDao();
    }

    @Singleton
    @Provides
    PSAppInfoDao providePSAppInfoDao(PSCoreDb db) {
        return db.psAppInfoDao();
    }

    @Singleton
    @Provides
    UploadWallpaperDao provideUploadWallpaperDao(PSCoreDb db) {
        return db.uploadWallpaperDao();
    }

}

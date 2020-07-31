package com.rumahdev.exo.chanyeol.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rumahdev.exo.chanyeol.db.common.Converters;
import com.rumahdev.exo.chanyeol.viewobject.AboutUs;
import com.rumahdev.exo.chanyeol.viewobject.App;
import com.rumahdev.exo.chanyeol.viewobject.Category;
import com.rumahdev.exo.chanyeol.viewobject.Color;
import com.rumahdev.exo.chanyeol.viewobject.DailyPoint;
import com.rumahdev.exo.chanyeol.viewobject.DeletedObject;
import com.rumahdev.exo.chanyeol.viewobject.Image;
import com.rumahdev.exo.chanyeol.viewobject.PSAppInfo;
import com.rumahdev.exo.chanyeol.viewobject.PSAppVersion;
import com.rumahdev.exo.chanyeol.viewobject.SearchLog;
import com.rumahdev.exo.chanyeol.viewobject.SearchWallpaper;
import com.rumahdev.exo.chanyeol.viewobject.TrendingWallpaper;
import com.rumahdev.exo.chanyeol.viewobject.UploadedWallpaper;
import com.rumahdev.exo.chanyeol.viewobject.User;
import com.rumahdev.exo.chanyeol.viewobject.UserLogin;
import com.rumahdev.exo.chanyeol.viewobject.Video;
import com.rumahdev.exo.chanyeol.viewobject.VideoIcon;
import com.rumahdev.exo.chanyeol.viewobject.Wallpaper;
import com.rumahdev.exo.chanyeol.viewobject.WallpaperMap;


/**
 * Created by Panacea-Soft on 11/20/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Database(entities = {
        Image.class,
        Category.class,
        User.class,
        UserLogin.class,
        AboutUs.class,
        SearchLog.class,
        Wallpaper.class,
        App.class,
        TrendingWallpaper.class,
        SearchWallpaper.class,
        UploadedWallpaper.class,
        Color.class,
        DailyPoint.class,
        WallpaperMap.class,
        PSAppVersion.class,
        PSAppInfo.class,
        DeletedObject.class,
        Video.class,
        VideoIcon.class

}, version = 7, exportSchema = false)

//******************
// DB Version Log
//******************
// V2.8 => DB Ver 7
// V2.6 => DB Ver 7
// V2.5 => DB Ver 7
// V2.4 => DB Ver 7
// V2.3 => DB Ver 6
// V2.2 => DB Ver 5
// V1.6 => DB Ver 5
// V1.5 => DB Ver 4
// V1.4 => DB Ver 4
// V1.3 => DB Ver 3
// V1.2 => DB Ver 3
// V1.1 => DB Ver 2
// V1.0 => DB Ver 1

@TypeConverters({Converters.class})

public abstract class PSCoreDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public AboutUsDao aboutUsDao();

    abstract public ImageDao imageDao();

    abstract public WallpaperDao wallpaperDao();

    abstract public CategoryDao categoryDao();

    abstract public ColorDao colorDao();

    abstract public PointDao pointDao();

    abstract public WallpaperMapDao wallpaperMapDao();

    abstract public PSAppInfoDao psAppInfoDao();

    abstract public PSAppVersionDao psAppVersionDao();

    abstract public DeletedObjectDao deletedObjectDao();

    abstract public UploadWallpaperDao uploadWallpaperDao();


    /**
     * Migrate from:
     * version 1 - using Room
     * to
     * version 2 - using Room where the {@link } has an extra field: added_date_str
     */
//    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE news "
//                    + " ADD COLUMN added_date_str INTEGER NOT NULL DEFAULT 0");
//        }
//    };

    /* More migration write here */
}
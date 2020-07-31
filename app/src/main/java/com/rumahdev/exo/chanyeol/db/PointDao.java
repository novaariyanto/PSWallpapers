package com.rumahdev.exo.chanyeol.db;

import android.database.Cursor;

import com.rumahdev.exo.chanyeol.viewobject.DailyPoint;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DailyPoint dailyPoint);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DailyPoint> dailyPoints);

    @Query("SELECT claimed_status FROM DailyPoint WHERE date = :date")
    Cursor getClaimedStatusByDate(String date);
}

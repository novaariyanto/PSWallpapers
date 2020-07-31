package com.rumahdev.exo.chanyeol.db;

import com.rumahdev.exo.chanyeol.viewobject.AboutUs;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Panacea-Soft on 12/30/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

@Dao
public interface AboutUsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AboutUs aboutUs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AboutUs> aboutUsList);

    @Query("SELECT * FROM AboutUs LIMIT '1'")
    LiveData<AboutUs> get();

    @Query("SELECT * FROM AboutUs")
    LiveData<List<AboutUs>> getAll();

    @Query("DELETE FROM AboutUs WHERE about_id = :aboutId")
    void deleteById(String aboutId);

    @Query("DELETE FROM AboutUs")
    void deleteTable();

}
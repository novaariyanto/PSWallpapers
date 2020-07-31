package com.rumahdev.exo.chanyeol.db;

import com.rumahdev.exo.chanyeol.viewobject.Color;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ColorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Color> colorList);

    @Query("SELECT * FROM Color")
    LiveData<List<Color>> getColorList();

    @Query("DELETE FROM Color")
    void deleteColor();
}

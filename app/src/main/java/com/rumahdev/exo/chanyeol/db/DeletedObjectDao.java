package com.rumahdev.exo.chanyeol.db;

import com.rumahdev.exo.chanyeol.viewobject.DeletedObject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DeletedObjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DeletedObject deletedObject);

    @Query("DELETE FROM DeletedObject")
    void deleteAll();
}

package com.example.pdmparcial2.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.pdmparcial2.model.New;

import java.util.List;

@Dao
public interface NewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNew(New mNew);

    @Query("DELETE FROM news_table")
    void deleteNews();

    //Retorna la lista de noticias en order descendiente segun la fecha de creacion
    @Query("SELECT * FROM news_table ORDER BY create_date DESC")
    LiveData<List<New>> getNews();

    @Query("UPDATE news_table SET favorite=:favorite WHERE id=:id")
    void setFavorite(String id, boolean favorite);
}

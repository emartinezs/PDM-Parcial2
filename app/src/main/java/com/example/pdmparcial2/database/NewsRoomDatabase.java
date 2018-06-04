package com.example.pdmparcial2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.pdmparcial2.model.New;

@Database(entities = {New.class}, version=1)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewDao newsDao();
    private static NewsRoomDatabase INSTANCE;

    public static NewsRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (NewsRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsRoomDatabase.class, "news_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
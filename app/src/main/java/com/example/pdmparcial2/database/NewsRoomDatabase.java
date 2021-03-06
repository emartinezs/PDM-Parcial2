package com.example.pdmparcial2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.pdmparcial2.database.daos.CategoryDao;
import com.example.pdmparcial2.database.daos.NewDao;
import com.example.pdmparcial2.database.daos.PlayerDao;
import com.example.pdmparcial2.model.Category;
import com.example.pdmparcial2.model.New;
import com.example.pdmparcial2.model.Player;

@Database(entities = {New.class, Category.class, Player.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {

    public abstract NewDao newsDao();

    public abstract CategoryDao categoryDao();

    public abstract PlayerDao playerDao();

    private static NewsRoomDatabase INSTANCE;

    //Crea la instancia de la base de datos si no existe una
    public static NewsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsRoomDatabase.class, "news_database").build();
                }
            }
        }
        return INSTANCE;
    }
}

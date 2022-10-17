package com.example.letsgo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.letsgo.DaoClass.UserDao;
import com.example.letsgo.EntityClass.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase instance;


    static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = Room.databaseBuilder(context, AppDatabase.class, "DATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;


    }


}

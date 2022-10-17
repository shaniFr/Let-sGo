package com.example.letsgo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.letsgo.DaoClass.RideDao;
import com.example.letsgo.EntityClass.Ride;


@Database(entities = {Ride.class}, version = 1)
public abstract class RideDatabase extends RoomDatabase {

    public abstract RideDao rideDao();

    private static RideDatabase instance;


    static RideDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (RideDatabase.class) {
                instance = Room.databaseBuilder(context, RideDatabase.class, "RideDATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;


    }
}

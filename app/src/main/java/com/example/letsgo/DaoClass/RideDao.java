package com.example.letsgo.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgo.EntityClass.Ride;
import com.example.letsgo.EntityClass.User;

import java.util.List;


@Dao
public interface RideDao {

    @Insert
    void insertAll(final Ride... ride);

    @Query("SELECT * From ride WHERE `from` IN (:from) AND `to` IN (:to) AND date IN (:date) AND departureTime IN (:departureTime)")
    List<Ride> getRidedsOfDate (String from, String to, String date, String departureTime);

    @Query("SELECT * FROM ride")
    List<Ride> getALLRides();


}

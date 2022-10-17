package com.example.letsgo.EntityClass;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ride")
public class Ride {

    @PrimaryKey
    @ColumnInfo(name = "IDride")
    @NonNull
    public int IDride;

    @ColumnInfo(name = "username")
    @NonNull
    public String username;

    @ColumnInfo(name = "from")
    @NonNull
    public String from;

    @ColumnInfo(name = "to")
    @NonNull
    public String to;

    @ColumnInfo(name = "date")
    @NonNull
    public String date;

    @ColumnInfo(name = "departureTime")
    @NonNull
    public String departureTime;


    public void setIDride(int IDride) { this.IDride = IDride;}

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setFrom (String from) {this.from = from; }

    public void setTo (String to) {this.to = to; }

    public void setDate (String date) {this.date = date; }

    public void setDepartureTime (String departureTime) {this.departureTime = departureTime; }
}

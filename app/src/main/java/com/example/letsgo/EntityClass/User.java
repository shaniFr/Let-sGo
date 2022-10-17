package com.example.letsgo.EntityClass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    @NonNull
    public String userName;

    @ColumnInfo(name = "password")
    @NonNull
    public String password;

    @ColumnInfo(name = "firstName")
    @NonNull
    public String firstName;

    @ColumnInfo(name = "lastName")
    public String lastName;

    @ColumnInfo(name = "profession")
    public  String profession;

    @ColumnInfo(name = "hobbies")
    public  String hobbies;

    @ColumnInfo(name = "music")
    public String music;

    @ColumnInfo(name = "car")
    public  String car;

    @ColumnInfo(name = "socialLink")
    public String socialLink;

    @ColumnInfo(name = "relationship")
    public  String relationship;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "Image")
    public byte[] image;

    @ColumnInfo(name = "rating")
    public  int rating;

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {this.password = password; }

    public void setFirstName(String firstName) {this.firstName = firstName; }

    public void setLastName(String lastName) {this.lastName = lastName; }

    public void setProfession(String profession) {this.profession = profession; }

    public void setRating(int rating) {this.rating = rating; }

    public String getUserName(){
        return userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getProfession(){
        return profession;
    }

    public String getHobbies(){
        return hobbies;
    }

    public String getMusic(){
        return music;
    }

    public String getCar(){
        return car;
    }

    public String getSocialLink(){
        return socialLink;
    }

    public String getRelationship(){
        return relationship;
    }

}

package com.example.letsgo.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgo.EntityClass.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insertAll(final User... users);

    @Query("SELECT * FROM user WHERE userName IN (:userNames)")
    List<User> loadAllByUserNames(String[] userNames);

    @Query("SELECT firstName From user WHERE userName IN (:username)")
    String returnFirstName(String username);

    @Query("SELECT * From user WHERE userName IN (:username)")
    User userDetails(String username);

    @Query("SELECT * FROM user WHERE password LIKE :password  LIMIT 1")
    User findByPassword(String password);

    @Delete
    void delete(User user);

    //Update Data
    @Query("update user SET hobbies =:hobbies ,music =:music,car =:car, socialLink =:socialLink, relationship =:relationship where `userName`= :username")
    void updateData(String hobbies, String music,String car, String socialLink, String relationship, String username);

    @Query("update user SET Image =:image where `userName`= :username")
    void updateImage(byte[] image, String username);
}

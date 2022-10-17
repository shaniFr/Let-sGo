package com.example.letsgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements Frag_Letsgo.FragLetsGoListener, Frag_Signin.FragSignInListener, Frag_moreDetails.FragMoreDetailsListener, Frag_Login.FragLogInListener, Frag_Select_Photo.FragSelectPhotoListener, Frag_userLogin.FragUserLogInListener, Frag_New_Ride.FragNewRideListener, Frag_Search_Ride.FragSearchRideListener, Frag_drivers.fragDriversListener, Frag_Drivers_Details.FragDriversDetailsListener {

    private String MyTag;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request for camera runtime permission
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

    /*
      Frag_Signin fragSignIn = (Frag_Signin) getSupportFragmentManager().findFragmentByTag("FRAGSINGIN");
       FragmentContainerView fragContainer = findViewById(R.id.fragContainer);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragContainer, Frag_Signin.class,null, "FRAGSINGIN")
                //	.addToBackStack(null)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
     */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Frag_Letsgo.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void OnClickSignIn() {

        Frag_Signin fragSignIn;

            getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                    .setReorderingAllowed(true)
                    .replace(R.id.fragContainer, Frag_Signin.class, null, "FRAGSIGNIN")
                    //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                    .addToBackStack("BBB")
                    .commit();
            getSupportFragmentManager().executePendingTransactions();


        fragSignIn = (Frag_Signin) getSupportFragmentManager().findFragmentByTag("FRAGSIGNIN");
        fragSignIn.onNewClick();

    }

    @Override
    public void OnClickLogIn() {

        Frag_Login fragLogIn;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_Login.class, null, "FRAGLOGIN")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragLogIn = (Frag_Login) getSupportFragmentManager().findFragmentByTag("FRAGLOGIN");
        fragLogIn.onNewClick();
    }


    @Override
    public void OnClickNext(String username) {

        Frag_moreDetails fragMoreDetails;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_moreDetails.class, null, "FRAGMOREDETAILS")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragMoreDetails = (Frag_moreDetails) getSupportFragmentManager().findFragmentByTag("FRAGMOREDETAILS");
        fragMoreDetails.onNewClick(username);

    }

    @Override
    public void OnClickNext2(String username) {

        Frag_Select_Photo fragSelectPhoto;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_Select_Photo.class, null, "FRAGSELECTPHOTO")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragSelectPhoto = (Frag_Select_Photo) getSupportFragmentManager().findFragmentByTag("FRAGSELECTPHOTO");
        fragSelectPhoto.onNewClick(username);

    }

    @Override
    public void OnClickLogin2(String username) {
        Frag_userLogin fragUserLogin;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_userLogin.class, null, "FRAGUSERLOGIN")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragUserLogin = (Frag_userLogin) getSupportFragmentManager().findFragmentByTag("FRAGUSERLOGIN");
        fragUserLogin.onNewClick(username);
    }


    @Override
    public void OnClickSignIn2() {
        Frag_Letsgo fragLetsgo;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_Letsgo.class, null, "FRAGULETSGO")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragLetsgo = (Frag_Letsgo) getSupportFragmentManager().findFragmentByTag("FRAGULETSGO");
        fragLetsgo.onNewClick();
    }

    @Override
    public void OnClickOpenRide(String username) {

        Frag_New_Ride fragNewRide;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_New_Ride.class, null, "FRAGNEWRIDE")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragNewRide = (Frag_New_Ride) getSupportFragmentManager().findFragmentByTag("FRAGNEWRIDE");
        fragNewRide.onNewClick(username);

    }

    @Override
    public void OnClickSearchRide(String username) {

        Frag_Search_Ride fragSearchRide;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_Search_Ride.class, null, "FRAGSEARCHRIDE")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragSearchRide = (Frag_Search_Ride) getSupportFragmentManager().findFragmentByTag("FRAGSEARCHRIDE");
        fragSearchRide.onNewClick(username);

    }

    @Override
    public void OnClickOpenNewRide(String username) {
        Frag_userLogin fragUserLogin;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_userLogin.class, null, "FRAGUSERLOGIN")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragUserLogin = (Frag_userLogin) getSupportFragmentManager().findFragmentByTag("FRAGUSERLOGIN");
        fragUserLogin.onNewClick(username);
    }

    @Override
    public void OnClickSearch(String username, String from, String to, String date, String departureTime) {

        Frag_drivers fragDrivers;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_drivers.class, null, "FRAGDRIVERS")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragDrivers = (Frag_drivers) getSupportFragmentManager().findFragmentByTag("FRAGDRIVERS");
        fragDrivers.onNewClick(username, from, to, date, departureTime);

    }

    @Override
    public void OnClickInfo(String username, String from, String to, String date, String departureTime, String user) {

        Frag_Drivers_Details fragDriversDetails;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_Drivers_Details.class, null, "FRAGDRIVERSDETAILS")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragDriversDetails = (Frag_Drivers_Details) getSupportFragmentManager().findFragmentByTag("FRAGDRIVERSDETAILS");
        fragDriversDetails.onNewClick(username, from, to, date, departureTime, user);

    }

    @Override
    public void OnClickBack(String username, String from, String to, String date, String departureTime) {
        Frag_drivers fragDrivers;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_drivers.class, null, "FRAGDRIVERS")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragDrivers = (Frag_drivers) getSupportFragmentManager().findFragmentByTag("FRAGDRIVERS");
        fragDrivers.onNewClick(username, from, to, date, departureTime);
    }

    @Override
    public void OnClickSelectDriver(String username) {
        Frag_userLogin fragUserLogin;

        getSupportFragmentManager().beginTransaction()  //this block create fragB dynamically in the stack
                .setReorderingAllowed(true)
                .replace(R.id.fragContainer, Frag_userLogin.class, null, "FRAGUSERLOGIN")
                //.add(R.id.fragContainer, Frag_Signin.class, null,"FRAGSIGNIN")
                .addToBackStack("BBB")
                .commit();
        getSupportFragmentManager().executePendingTransactions();


        fragUserLogin = (Frag_userLogin) getSupportFragmentManager().findFragmentByTag("FRAGUSERLOGIN");
        fragUserLogin.onNewClick(username);

    }
}
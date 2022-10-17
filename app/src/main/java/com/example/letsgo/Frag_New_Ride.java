package com.example.letsgo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.letsgo.EntityClass.Ride;
import com.example.letsgo.EntityClass.User;

import java.util.ArrayList;
import java.util.List;

public class Frag_New_Ride extends Fragment implements View.OnClickListener{

    Frag_New_Ride.FragNewRideListener listener;
    EditText etFrom;
    EditText etTo;
    EditText etDate;
    EditText etDeparture;
    String username;
    private List<Ride> ridelist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //	setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag_New_Ride.FragNewRideListener)context;
        }catch(ClassCastException e){
            throw new ClassCastException("the class " +
                    getActivity().getClass().getName() +
                    " must implements the interface 'FragBListener'");
        }

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ride, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.rideBtn).setOnClickListener(this);
        etFrom = view.findViewById(R.id.etFrom);
        etTo = view.findViewById(R.id.etTo);
        etDate = view.findViewById(R.id.etDate);
        etDeparture = view.findViewById(R.id.etDepartureTime);
        super.onViewCreated(view, savedInstanceState);
    }

    public void onNewClick(String username) {

        this.username = username;
    }

    @Override
    public void onClick(View v) {

        String from = etFrom.getText().toString();
        String to = etTo.getText().toString();
        String date = etDate.getText().toString();
        String departureTime = etDeparture.getText().toString();

        Ride ride = new Ride();
        ridelist = new ArrayList<>();
        ridelist = RideDatabase.getDatabase(getActivity().getApplicationContext()).rideDao().getALLRides();
        int countRides = 1;
        for(Ride ride1 : ridelist)
        {
            countRides++;
        }
        ride.setIDride(countRides);
        ride.setUsername(username);
        ride.setFrom(from);
        ride.setTo(to);
        ride.setDate(date);
        ride.setDepartureTime(departureTime);

        RideDatabase.getDatabase(getActivity().getApplicationContext()).rideDao().insertAll(ride);

        showDialog();
        listener.OnClickOpenNewRide(username);
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Your ride has been successfully saved");
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }

        });

        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }


    public interface FragNewRideListener{
        //put here methods you want to utilize to communicate with the hosting activity
        public void OnClickOpenNewRide(String username);
    }
}

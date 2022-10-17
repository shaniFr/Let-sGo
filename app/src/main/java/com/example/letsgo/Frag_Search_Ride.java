package com.example.letsgo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_Search_Ride extends Fragment implements View.OnClickListener{

    Frag_Search_Ride.FragSearchRideListener listener;
    TextView searchRide;
    Button searchBtn;
    EditText etFrom;
    EditText etTo;
    EditText etDate;
    EditText etDeparture;
    String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //	setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag_Search_Ride.FragSearchRideListener)context;
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
        searchRide = view.findViewById(R.id.textRide);
        searchBtn = view.findViewById(R.id.rideBtn);
        view.findViewById(R.id.rideBtn).setOnClickListener(this);
        etFrom = view.findViewById(R.id.etFrom);
        etTo = view.findViewById(R.id.etTo);
        etDate = view.findViewById(R.id.etDate);
        etDeparture = view.findViewById(R.id.etDepartureTime);
        super.onViewCreated(view, savedInstanceState);
    }

    public void onNewClick(String username) {

        searchRide.setText("Search for a ride");
        searchBtn.setText("Search");
        this.username = username;
    }

    @Override
    public void onClick(View v) {
        String from = etFrom.getText().toString();
        String to = etTo.getText().toString();
        String date = etDate.getText().toString();
        String departureTime = etDeparture.getText().toString();

        listener.OnClickSearch(username, from, to, date, departureTime);
    }

    public interface FragSearchRideListener{
        //put here methods you want to utilize to communicate with the hosting activity
        public void OnClickSearch(String username, String from, String to, String date, String departureTime);
    }
}

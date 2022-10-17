package com.example.letsgo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgo.EntityClass.Ride;
import com.example.letsgo.EntityClass.User;

import java.util.ArrayList;
import java.util.List;

public class Frag_drivers extends Fragment implements driversAdapter.AdapterListener{


    fragDriversListener listener;
    private RecyclerView recyclerView;
    private List<Ride> rideList;
    private List<User> usersList;
    private String username, from, to, date, departureTime;


    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (fragDriversListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'countryListener'");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_ride, container, false);
        //Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.rvDrivers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("username", username);
        outState.putString("from", from);
        outState.putString("to", to);
        outState.putString("date", date);
        outState.putString("departureTime", departureTime);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.username = savedInstanceState.getString(username);
            this.from = savedInstanceState.getString(from);
            this.to = savedInstanceState.getString(to);
            this.date = savedInstanceState.getString(date);
            this.departureTime = savedInstanceState.getString(departureTime);

            onNewClick(username,from,to,date,departureTime);
        }
    }

    public void onNewClick(String username, String from, String to, String date, String departureTime) {

        this.username = username;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departureTime = departureTime;

        rideList = new ArrayList<>();
        rideList = RideDatabase.getDatabase(getActivity().getApplicationContext()).rideDao().getRidedsOfDate(from, to, date, departureTime);
        int size = rideList.size();
        String[] usernames = new String[size];
        int i = 0;
        for(Ride ride : rideList){

            usernames[i] = ride.username;
            i++;
        }

        usersList = new ArrayList<>();
        usersList = AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().loadAllByUserNames(usernames);
        recyclerView.setAdapter(new driversAdapter(getActivity().getApplicationContext(), usersList, Frag_drivers.this));

    }



    @Override
    public void changeFragment(String username) {

        listener.OnClickInfo(username, from, to, date, departureTime, this.username);
    }

    public interface fragDriversListener {

        public void OnClickInfo(String username, String from, String to, String date, String departureTime, String user);

    }
}

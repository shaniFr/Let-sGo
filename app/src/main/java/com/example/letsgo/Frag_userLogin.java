package com.example.letsgo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_userLogin extends Fragment implements View.OnClickListener{

    Frag_userLogin.FragUserLogInListener listener;
    String firstName;
    TextView firstNameTxt;
    String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //	setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag_userLogin.FragUserLogInListener)context;
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
        return inflater.inflate(R.layout.user_login, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        firstNameTxt = view.findViewById(R.id.txtFirstName);
        view.findViewById(R.id.openRideBtn).setOnClickListener(this);
        view.findViewById(R.id.searchRideBtn).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //the activity informs fragSignIn about new click in fragLetsGo
    public void onNewClick(String username){

        firstName = AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().returnFirstName(username);
        firstNameTxt.setText("Hi " + firstName +", ");
        this.username = username;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openRideBtn:
                listener.OnClickOpenRide(username);
                break;
            case R.id.searchRideBtn:
                listener.OnClickSearchRide(username);
                break;
        }
    }

    public interface FragUserLogInListener{
        //put here methods you want to utilize to communicate with the hosting activity
        public void OnClickOpenRide(String username);
        public void OnClickSearchRide(String username);
    }
}

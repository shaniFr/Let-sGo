package com.example.letsgo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_Letsgo extends Fragment implements View.OnClickListener {

    FragLetsGoListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (FragLetsGoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'FragAListener'");
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_letsgo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.signInBtn).setOnClickListener(this);
        view.findViewById(R.id.logInBtn).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signInBtn:
                listener.OnClickSignIn();
                break;
            case R.id.logInBtn:
                listener.OnClickLogIn();
                break;
        }
    }

    //the activity informs fragLogIn about new click in fragLetsGo
    public void onNewClick(){

    }
/*
    @Override
    public void changeFragment() {
        listener.change();
    }

 */

    interface FragLetsGoListener {
        public void OnClickSignIn();
        public void OnClickLogIn();
    }

}

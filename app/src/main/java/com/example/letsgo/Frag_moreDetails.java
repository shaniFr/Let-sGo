package com.example.letsgo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag_moreDetails extends Fragment implements View.OnClickListener{

    Frag_moreDetails.FragMoreDetailsListener listener;
    EditText etHobbies;
    EditText etMusic;
    EditText etCar;
    EditText etLink;
    EditText etRelationship;
    String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //	setRetainInstance(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag_moreDetails.FragMoreDetailsListener)context;
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
        return inflater.inflate(R.layout.more_details, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.nextBtn).setOnClickListener(this);
        etHobbies = view.findViewById(R.id.etHobbies);
        etMusic = view.findViewById(R.id.etMusic);
        etCar = view.findViewById(R.id.etCar);
        etLink = view.findViewById(R.id.etLink);
        etRelationship = view.findViewById(R.id.etRelationship);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //the activity informs fragSignIn about new click in fragLetsGo
    public void onNewClick(String username){
        this.username = username;
    }

    @Override
    public void onClick(View v) {

        String hobbies = etHobbies.getText().toString();
        String music = etMusic.getText().toString();
        String car = etCar.getText().toString();
        String link = etLink.getText().toString();
        String relationship = etRelationship.getText().toString();
        AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().updateData(hobbies, music, car, link,relationship, username);
        listener.OnClickNext2(username);
    }

    public interface FragMoreDetailsListener{
        //put here methods you want to utilize to communicate with the hosting activity
        public void OnClickNext2(String username);
    }
}

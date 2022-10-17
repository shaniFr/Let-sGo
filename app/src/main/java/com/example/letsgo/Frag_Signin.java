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

import com.example.letsgo.EntityClass.User;

import java.util.Random;

public class Frag_Signin extends Fragment implements View.OnClickListener{

    Frag_Signin.FragSignInListener listener;
    EditText etUsername;
    EditText etPassword;
    EditText etFirstName;
    EditText etLastName;
    EditText etProfession;
    //serLocalStore userLocalStore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //	setRetainInstance(true);
        super.onCreate(savedInstanceState);
        //userLocalStore = new UserLocalStore(getContext());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag_Signin.FragSignInListener)context;
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
        return inflater.inflate(R.layout.signin, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.nextBtn).setOnClickListener(this);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etProfession = view.findViewById(R.id.etProfession);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //the activity informs fragSignIn about new click in fragLetsGo
    public void onNewClick(){

    }

    @Override
    public void onClick(View v) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String profession = etProfession.getText().toString();
        //User registeredData = new User(username, password);
        //userLocalStore.storeUserData(registeredData);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setProfession(profession);
        Random rand = new Random();
        int rating = rand.nextInt(5);
        rating++;
        user.setRating(rating);

        AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().insertAll(user);

        listener.OnClickNext(username);
    }

    public interface FragSignInListener{
        //put here methods you want to utilize to communicate with the hosting activity
        public void OnClickNext(String username);
    }
}

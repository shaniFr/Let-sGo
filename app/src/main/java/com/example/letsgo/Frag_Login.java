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

import com.example.letsgo.EntityClass.User;

import java.util.ArrayList;
import java.util.List;

public class Frag_Login extends Fragment implements View.OnClickListener{

    Frag_Login.FragLogInListener listener;
    //UserLocalStore userLocalStore;
    EditText etUsername;
    EditText etPassword;
    String username;
    private List<User> list;

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (Frag_Login.FragLogInListener) context;
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
        //userLocalStore = new UserLocalStore(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.logInBtn2).setOnClickListener(this);
        etUsername = view.findViewById(R.id.etxtusername);
        etPassword = view.findViewById(R.id.etxtpassword);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //the activity informs fragLogIn about new click in fragLetsGo
    public void onNewClick(){

    }

    @Override
    public void onClick(View v) {
        //User1 user = new User1(null, null);

        //user = userLocalStore.getLoggedInUser();

        if(authentication()) {
            //userLocalStore.setUserLoggedIn(true);
            listener.OnClickLogin2(username);
        }
    }

    private boolean authentication(){

        username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        list = new ArrayList<>();
        list = AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().getAll();

        for(User user : list)
        {
            if (username.equals(user.userName) && password.equals(user.password))
            {
                return true;
            }
        }

        showDialog();
        return false;

    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("You have entered incorrect username or password.");
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }

        });

        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }



    interface FragLogInListener {
        public void OnClickLogin2(String username);
    }
}

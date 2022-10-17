package com.example.letsgo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.letsgo.EntityClass.User;

public class Frag_Drivers_Details extends Fragment implements View.OnClickListener{

    Frag_Drivers_Details.FragDriversDetailsListener listener;
    ImageView image;
    TextView details;
    private String username, from, to, date, departureTime, user1;

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            this.listener = (Frag_Drivers_Details.FragDriversDetailsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("the class " +
                    context.getClass().getName() +
                    " must implements the interface 'FragAListener'");
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.details_drivers, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        details = (TextView) view.findViewById(R.id.detailsDriver);
        image = (ImageView) view.findViewById(R.id.driverImage2);
        view.findViewById(R.id.backBtn).setOnClickListener(this);
        view.findViewById(R.id.selectBtn).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    public void onNewClick(String username, String from, String to, String date, String departureTime, String user1) {

        this.username = username;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departureTime = departureTime;
        this.user1 = user1;

        String userDetails = "";
        User user;

        user = AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().userDetails(username);

        Bitmap bmp = BitmapFactory.decodeByteArray(user.image, 0, user.image.length);
        int width = image.getDrawable().getIntrinsicWidth();
        int height = image.getDrawable().getIntrinsicHeight();
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, width, height, false));
        userDetails += (user.getFirstName() + " ");
        if ((user.getLastName()).isEmpty()){
            userDetails += "\n\n";
        }
        else
            userDetails += user.getLastName() + "\n\n";

        if ((user.getProfession()).isEmpty()){

        }

        else
            userDetails += "Profession: " + user.getProfession() + "\n\n";

        if ((user.getHobbies()).isEmpty()){

        }

        else
            userDetails += "Hobbies: " + user.getHobbies() + "\n\n";

        if ((user.getMusic()).isEmpty()){

        }

        else
            userDetails += "Music in the car: " + user.getMusic() + "\n\n";


        if ((user.getCar()).isEmpty()){

        }

        else
            userDetails += "Car type and color: " + user.getCar() + "\n\n";

        if ((user.getSocialLink()).isEmpty()){

        }

        else
            userDetails += "Social link: " + user.getSocialLink() + "\n\n";

        if ((user.getRelationship()).isEmpty()){

        }

        else
            userDetails += "Relationship: " + user.getRelationship() + "\n\n";

        details.setText(userDetails);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                listener.OnClickBack(user1, from, to, date, departureTime);
                break;
            case R.id.selectBtn:
                showDialog();
                listener.OnClickSelectDriver(this.user1);
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Your selected driver has been successfully saved!");
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }

        });

        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    interface FragDriversDetailsListener {
        public void OnClickBack(String username, String from, String to, String date, String departureTime);
        public void OnClickSelectDriver(String username);
    }
}

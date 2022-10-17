package com.example.letsgo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letsgo.EntityClass.Ride;
import com.example.letsgo.EntityClass.User;

import java.util.List;

public class driversAdapter extends RecyclerView.Adapter<driversAdapter.ViewHolder>{

    Context context;
    List<User> userList;
    private AdapterListener adapterListener;

    public driversAdapter(Context context, List<User> list, AdapterListener adapterListener) {
        this.context = context;
        this.userList = list;
        this.adapterListener = adapterListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drivers, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull driversAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.fillData(position);
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterListener.changeFragment(userList.get(position).getUserName());
            }
        });


    }

    @Override
    public int getItemCount() {

        return userList.size();
    }

    public interface AdapterListener {

        public void changeFragment(String username);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView driverName, raiting;
        Button info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageDriver);
            driverName = itemView.findViewById(R.id.txtDriverName);
            raiting = itemView.findViewById(R.id.txtRating);
            info = itemView.findViewById(R.id.infoBtn);
        }

        public void fillData(int position)
        {
            User user = userList.get(position);
            Bitmap bmp = BitmapFactory.decodeByteArray(user.image, 0, user.image.length);
            int width = image.getDrawable().getIntrinsicWidth();
            int height = image.getDrawable().getIntrinsicHeight();
            image.setImageBitmap(Bitmap.createScaledBitmap(bmp, width, height, false));
            driverName.setText(user.firstName + " " + user.lastName);
            String stars = "rating: ";
            int countStars = user.rating;
            for(int i = 0; i < countStars; i++ ){

                stars += ("\u2605");
            }

            raiting.setText(stars);
        }
    }
}

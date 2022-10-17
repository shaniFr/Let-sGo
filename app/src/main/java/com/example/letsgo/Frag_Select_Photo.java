package com.example.letsgo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Frag_Select_Photo extends Fragment implements View.OnClickListener{

    Frag_Select_Photo.FragSelectPhotoListener listener;
    Button btnSelectPhoto;
    ImageView viewImage;
    String username;

    // constant to compare
    // the activity result code
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private static final int SELECT_PICTURE = 200;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //	setRetainInstance(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        try{
            this.listener = (Frag_Select_Photo.FragSelectPhotoListener)context;
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
        return inflater.inflate(R.layout.select_photo, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnSelectPhoto = (Button)view.findViewById(R.id.selectPhotoBtn);
        view.findViewById(R.id.signInBtn2).setOnClickListener(this);
        viewImage = (ImageView)view.findViewById(R.id.viewImage);
        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    // create an instance of the
                    // intent of the type image
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(Intent.ACTION_GET_CONTENT);

                    // pass the constant to compare it
                    // with the returned requestCode
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                viewImage.setImageBitmap(bitmap);
                AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().updateImage(byteArray, username);

            }
        }

        else if (requestCode == SELECT_PICTURE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                // compare the resultCode with the
                // SELECT_PICTURE constant
                if (requestCode == SELECT_PICTURE) {
                    // Get the url of the image from data
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        // update the preview image in the layout
                        viewImage.setImageURI(selectedImageUri);
                        InputStream iStream = null;
                        try {
                            iStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
                            byte[] inputData = getBytes(iStream);
                            AppDatabase.getDatabase(getActivity().getApplicationContext()).userDao().updateImage(inputData, username);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

        @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onNewClick(String username) {
        this.username = username;
    }

    @Override
    public void onClick(View v) {

        listener.OnClickSignIn2();

    }

    public interface FragSelectPhotoListener{
        //put here methods you want to utilize to communicate with the hosting activity
        public void OnClickSignIn2();
    }
}

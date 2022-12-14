package com.ass2.i190626_190438;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    ImageView add;
    CircleImageView setProfile;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        add = findViewById(R.id.imageAdd);
        setProfile = findViewById(R.id.circularimage);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == GALLERY_REQ_CODE)
            {
                setProfile.setImageURI(data.getData());
                Bitmap dp=null;
                try {
                    dp=MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
package com.ass2.i190626_190438;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class IndividualChat extends AppCompatActivity {

    ImageView dp;
    TextView name;
    ImageView screenShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        dp = findViewById(R.id.imageViewDp);
        name = findViewById(R.id.nameIndividual);

        Bundle b = getIntent().getExtras();
        name.setText(b.getString("name"));
        dp.setImageResource(b.getInt("image", 0));

        screenShot = findViewById(R.id.ss);
        screenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                SaveImage();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            SaveImage();
        } else
        {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void SaveImage() {

        if (!CheckPermission()) {
             return;
        }
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera/Musify";
            File fileDir = new File(path);

            if (!fileDir.exists())
                fileDir.mkdirs();

            String mPath = "ScreenShot_"+new Date().getTime()+".png";
            Bitmap bitmap = screenShot();
            File file = new File(fileDir, mPath);
            FileOutputStream fOut= new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fOut);
            fOut.flush();
            fOut.close();

            Toast.makeText(IndividualChat.this, "Oops! You took a Screenshot", Toast.LENGTH_LONG).show();

            Intent intent= new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri,"image/png");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap screenShot() {

        View v = findViewById(R.id.rootView);
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(),v.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private boolean CheckPermission() {
        int permission = ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},1);
            return false;
        }
        return true;
    }
}

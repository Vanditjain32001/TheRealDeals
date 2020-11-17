package com.example.test1;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class buy_card extends AppCompatActivity{

    TextView Price, BHK, type;
    ImageView Image;

    Bitmap bitmap;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_card);

        //initialize views
         Price = findViewById(R.id.price);
         BHK = findViewById(R.id.bhk);
         type = findViewById(R.id.mType);
         Image = findViewById(R.id.imageView);

        //get data from intent
        byte[] bytes = getIntent().getByteArrayExtra("image");
        String price = getIntent().getStringExtra("price");
        String Bhk = getIntent().getStringExtra("BHK");
        String TYpe = getIntent().getStringExtra("Type");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        //set data to views
        Price.setText(price);
        BHK.setText(Bhk);
        type.setText(TYpe);
        Image.setImageBitmap(bmp);

        //get image from imageview as bitmap
        bitmap = ((BitmapDrawable)Image.getDrawable()).getBitmap();

    }

}

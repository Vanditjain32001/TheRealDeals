package com.example.test1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sell extends AppCompatActivity {
    EditText sparea;
    EditText spprice;
    EditText spbhk;
    EditText sptype;
    EditText sploc;
    EditText sppos;
    EditText sppark;
    EditText spfurn;
    EditText spstatus;
    EditText spsur;
    EditText spdesc;
    int counter = 1;
    int PICK_IMAGE_MULTIPLE = 1;

    private ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private int uploads = 0;
    private ProgressDialog progressDialog;
    private DatabaseReference node;
    int index = 0;
    TextView sptextView;
    Button spchoose, spsend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);




    }

    public void process(View view) {


        sparea = (EditText) findViewById(R.id.sparea);
        spprice = (EditText) findViewById(R.id.spprice);
        spbhk = (EditText) findViewById(R.id.spbhk);
        sptype = (EditText) findViewById(R.id.sptype);
        sploc = (EditText) findViewById(R.id.sploc);
        sppos = (EditText) findViewById(R.id.sppos);
        sppark = (EditText) findViewById(R.id.sppark);
        spfurn = (EditText) findViewById(R.id.spfurn);
        spstatus = (EditText) findViewById(R.id.spstatus);
        spsur = (EditText) findViewById(R.id.spsur);
        spdesc = (EditText) findViewById(R.id.spdesc);


        String area = sparea.getText().toString().trim();
        String price = spprice.getText().toString().trim();
        String bhk = spbhk.getText().toString().trim();
        String type = sptype.getText().toString().trim();
        String loc = sploc.getText().toString().trim();
        String pos = sppos.getText().toString().trim();
        String park = sppark.getText().toString().trim();
        String fur = spfurn.getText().toString().trim();
        String status = spstatus.getText().toString().trim();
        String sur = spsur.getText().toString().trim();
        String des = spdesc.getText().toString().trim();
        property_data obj = new property_data(area, price, bhk, type, loc, pos, park, fur, status, sur, des);
        FirebaseDatabase fdata = FirebaseDatabase.getInstance();
        node = fdata.getReference("mail_ID");
        node.child(String.valueOf((counter + 1))).setValue(obj);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading ..........");
        sptextView = findViewById(R.id.sptextview);
        spchoose = findViewById(R.id.spchoose);
        spsend = findViewById(R.id.spsend);


        sparea.setText("");
        spprice.setText("");
        spbhk.setText("");
        sptype.setText("");
        sploc.setText("");
        sppos.setText("");
        sppark.setText("");
        spfurn.setText("");
        spstatus.setText("");
        spsur.setText("");
        spdesc.setText("");

        Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @SuppressLint("SetTextI18n")
    public void choose (View view){


        //we will pick images
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);

    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    ImageList.add(mImageUri);

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            ImageList.add(uri);




                        }
                        Log.v("LOG_TAG", "Selected Images" + ImageList.size());
                    }Toast.makeText(this, "You Have Selected "+ ImageList.size() +" Pictures", Toast.LENGTH_LONG).show();

                }
            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @SuppressLint("SetTextI18n")
    public void send (View view){


        Toast.makeText(this, "Please wait", Toast.LENGTH_LONG).show();
        final StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
        for (uploads = 0; uploads < ImageList.size(); uploads++) {
            Uri Image = ImageList.get(uploads);
            final StorageReference imagename = ImageFolder.child("image/" + Image.getLastPathSegment());

            imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String url = String.valueOf(uri);
                            //SendLink(url);

                        }
                    });

                }
            });

        }
    }
   /*private void SendLink(String url) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("link", url);

        node.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @SuppressLint("SetTextI18n")
                @Override

                public void onComplete(@NonNull Task<Void> task) {

                    ImageList.clear();
                }

        });


    }*/


}
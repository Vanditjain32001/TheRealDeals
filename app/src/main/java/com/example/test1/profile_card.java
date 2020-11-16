package com.example.test1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class profile_card extends AppCompatActivity {

    private ImageView mOnlineImageView;
    private CardView mCardView;
    private ListView mListView;
    private TextView price;
    private TextView area;
    private TextView bhk;
    private TextView mDescriptionText;
    private ArrayAdapter<String> adapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference;
    DocumentReference documentReference;

    private ArrayList<String> benefits;
    private String imageUrl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_card);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mOnlineImageView = (ImageView)findViewById(R.id.online_image);
        price = (TextView) findViewById(R.id.price);
        area = (TextView) findViewById(R.id.area);
        bhk = (TextView) findViewById(R.id.bhk);
        mDescriptionText = (TextView)findViewById(R.id.des);

        String mprice,marea,mbhk;
        Intent intent = getIntent();
        mprice = intent.getStringExtra("price");
        documentReference = collectionReference.document(mprice);
        price.setText(mprice);
        marea = intent.getStringExtra("area");
        documentReference = collectionReference.document(marea);
        area.setText(marea);
        mbhk = intent.getStringExtra("BHK");
        documentReference = collectionReference.document(mbhk);
        bhk.setText(mbhk);
        mCardView.setEnabled(false);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null)
                {
                    Toast.makeText(profile_card.this,"no internet connection",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (documentSnapshot.exists()) {
                        price.setText(documentSnapshot.getString("price"));
                        area.setText(documentSnapshot.getString("area"));
                        bhk.setText(documentSnapshot.getString("bhk"));
                        mDescriptionText.setText(documentSnapshot.getString("des"));
                        benefits = (ArrayList<String>) documentSnapshot.get("benefits");
                        mCardView.setEnabled(true);
                        imageUrl = documentSnapshot.getString("imageUrl");
                        RequestOptions options = new RequestOptions()
                                .centerCrop();
                    }
                }
            }
        });
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListView.getVisibility()==View.GONE)
                {
                    mListView.setVisibility(View.VISIBLE);
                    populateListView(benefits);
                }
                else if(mListView.getVisibility()==View.VISIBLE)
                {
                    mListView.setAdapter(null);
                    mListView.setVisibility(View.GONE);
                }
            }
        });
    }
    void populateListView(ArrayList<String> benefits)
    {
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,benefits);
        mListView.setAdapter(adapter);
    }
}


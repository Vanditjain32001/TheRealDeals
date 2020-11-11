package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private FirebaseAuth data;
    private Button logout;
    private Button buy_button;
    private Button sell_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data=FirebaseAuth.getInstance();

        buy_button=(Button)findViewById(R.id.button_buy);
        sell_button=(Button)findViewById(R.id.btn_sell);

        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_buy = new Intent(MainActivity2.this, Buy.class);
                startActivity(activity_buy);
            }
        });

        sell_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_sell_profile=new Intent(MainActivity2.this,profile.class);
                startActivity(activity_sell_profile);
            }
        });

        logout=(Button)findViewById(R.id.btnLogOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.signOut();
                finish();
                startActivity(new Intent(new Intent(MainActivity2.this,MainActivity.class)));
            }
        });


    }
}

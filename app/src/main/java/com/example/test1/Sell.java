package com.example.test1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
    }

    public void process(View view){
        sparea=(EditText)findViewById(R.id.sparea);
        spprice=(EditText)findViewById(R.id.spprice);
        spbhk=(EditText)findViewById(R.id.spbhk);
        sptype=(EditText)findViewById(R.id.sptype);
        sploc=(EditText)findViewById(R.id.sploc);
        sppos=(EditText)findViewById(R.id.sppos);
        sppark=(EditText)findViewById(R.id.sppark);
        spfurn=(EditText)findViewById(R.id.spfurn);
        spstatus=(EditText)findViewById(R.id.spstatus);
        spsur=(EditText)findViewById(R.id.spsur);
        spdesc=(EditText)findViewById(R.id.spdesc);


        String area=sparea.getText().toString().trim();
        String price=spprice.getText().toString().trim();
        String bhk=spbhk.getText().toString().trim();
        String type=sptype.getText().toString().trim();
        String loc=sploc.getText().toString().trim();
        String pos=sppos.getText().toString().trim();
        String park=sppark.getText().toString().trim();
        String fur=spfurn.getText().toString().trim();
        String status=spstatus.getText().toString().trim();
        String sur=spsur.getText().toString().trim();
        String des=spdesc.getText().toString().trim();


        property_data obj=new property_data(area,price, bhk, type, loc, pos, park, fur, status, sur, des);
        FirebaseDatabase data=FirebaseDatabase.getInstance();
        DatabaseReference node=data.getReference("Email_ID");
        node.child(String.valueOf((counter+1))).setValue(obj);

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
}

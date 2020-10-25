package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Mail;
    private EditText password;
    private TextView sign_up;
    private Button Login;
    private FirebaseAuth data;
    private ProgressDialog message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mail=(EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etPassword);
        Login=(Button) findViewById(R.id.Button_Login);
        sign_up=(TextView)findViewById(R.id.tvsignup);
        data=FirebaseAuth.getInstance();
        message=new ProgressDialog(this);

        FirebaseUser user= data.getCurrentUser();

        if(user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(Mail.getText().toString(),password.getText().toString());
            }
        }
        );
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,sign_up.class);
                startActivity(intent);
            }
        }
        );

    }
    private void login(String useremail,String userpassword)
    {
        message.setMessage("Please Wait....");
        message.show();
        data.signInWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    message.dismiss();
                    startActivity(new Intent(MainActivity.this,MainActivity2.class));
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    message.dismiss();
                    Toast.makeText(MainActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

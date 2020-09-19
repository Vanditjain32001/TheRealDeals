package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    private EditText username,email,password;
    private Button signupbt;
    private FirebaseAuth authentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        VarId();

        signupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    //String name=username.getText().toString().trim();
                    String mail=email.getText().toString().trim();
                    String pass=password.getText().toString().trim();
                    authentication.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(sign_up.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(sign_up.this,MainActivity.class));
                        }
                    });
                    startActivity(new Intent(sign_up.this,MainActivity.class));
                }
            }
        });
    }
    private void VarId()
    {
        authentication=FirebaseAuth.getInstance();
        username= (EditText)findViewById(R.id.etName_s);
        email=(EditText)findViewById(R.id.etEmail_s);
        password=(EditText)findViewById(R.id.etPassword_s);
        signupbt=(Button)findViewById(R.id.Button_signup);
    }
     private Boolean validate()
     {
         Boolean flag=false;
         String name=username.getText().toString();
         String mail=email.getText().toString();
         String pass=password.getText().toString();
         if(name.isEmpty() || mail.isEmpty() || pass.isEmpty())
         {
             Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
         }
         else
         {
             flag=true;
         }
         return flag;
     }
}

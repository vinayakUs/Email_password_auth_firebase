package com.example.email_password_auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText username,passsword;
    Button finish;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        finish = findViewById(R.id.finishbtn);
        username = findViewById(R.id.usernameF);
        passsword = findViewById(R.id.passwordF);
        mAuth = FirebaseAuth.getInstance();


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String us=username.getText().toString().trim();
                String ps=passsword.getText().toString().trim();
                login(us,ps);
            }
        });


    }
    void login(String us,String ps){

        mAuth.signInWithEmailAndPassword(us,ps)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(login.this,home.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(login.this,"Try again Later",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}

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

public class create extends AppCompatActivity {

    Button create;
    EditText username,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        create=findViewById(R.id.createbtn);
        username = findViewById(R.id.username_reg_F);
        password = findViewById(R.id.password_reg_F);
        mAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String us=username.getText().toString().trim();
                String ps=password.getText().toString().trim();
                register(us,ps);
            }
        });

    }
    void register(String us,String ps){

        mAuth.createUserWithEmailAndPassword(us,ps)
                .addOnCompleteListener(create.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(create.this,home.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(create.this,"Try again Later",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}

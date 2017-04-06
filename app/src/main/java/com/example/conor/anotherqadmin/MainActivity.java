package com.example.conor.anotherqadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //view objects defined
    private EditText etAdminUserName;
    private EditText etAdminPassword;
    private Button bAdminLogin;

    //create progress dialog reference
    private ProgressDialog pDial;

    //create database reference
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get firebase auth object
        firebaseAuth = firebaseAuth.getInstance();

        /*
        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), AdminArea.class));
        }*/

        etAdminUserName = (EditText) findViewById(R.id.etAdminUserName);
        etAdminPassword = (EditText) findViewById(R.id.etAdminPassword);
        bAdminLogin =  (Button) findViewById(R.id.bAdminLogin);

        pDial = new ProgressDialog(this);

        bAdminLogin.setOnClickListener(this);
        etAdminUserName.setOnClickListener(this);
        etAdminPassword.setOnClickListener(this);
    }

    public void userLogin(){
            String username = etAdminUserName.getText().toString().trim();
            String password = etAdminPassword.getText().toString().trim();

        //checking if email and password are empty
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        // is the email and password are not empty display a progress dialog

        pDial.setMessage("Logging in please wait");
        pDial.show();

        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pDial.dismiss();
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(MainActivity.this, AdminArea.class));
                            }
                        }

                });
                }

    @Override
    public void onClick(View v) {
        if (v == bAdminLogin) {
          userLogin();
        }

    }
}









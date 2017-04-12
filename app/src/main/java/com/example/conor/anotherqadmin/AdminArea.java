package com.example.conor.anotherqadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminArea extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView  tvWelcome;
    private Button bAdminLogout;
    private ProgressDialog pDial;
    private Button bQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_area);

        firebaseAuth = firebaseAuth.getInstance();
        pDial = new ProgressDialog(this);




        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, AdminArea.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvWelcome.setText("Welcome to your Admin page" +user.getEmail());
        bAdminLogout = (Button) findViewById(R.id.bAdminLogout);
        bQueue = (Button) findViewById(R.id.bQueue);

        bAdminLogout.setOnClickListener(this);
        bQueue.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == bAdminLogout) {
            pDial.setMessage("Logging out... please wait");
            pDial.show();
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        if(v == bQueue){
            startActivity(new Intent(this, Queue.class));
        }
    }


        }


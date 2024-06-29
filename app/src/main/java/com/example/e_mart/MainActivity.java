package com.example.e_mart;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemClock.sleep(2000);
        firebaseAuth = FirebaseAuth.getInstance();



    }

   @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {

            Intent loginIntent = new Intent(MainActivity.this, Register.class);
            startActivity(loginIntent);
            finish();
        } else {


            Intent loginIntent = new Intent(MainActivity.this, home.class);
            startActivity(loginIntent);
            finish();
        }


    }
}

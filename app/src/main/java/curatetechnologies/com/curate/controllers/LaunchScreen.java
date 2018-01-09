package curatetechnologies.com.curate.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;

import curatetechnologies.com.curate.MainActivity;
import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.Crashlytics;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            // not signed in
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

    }
}

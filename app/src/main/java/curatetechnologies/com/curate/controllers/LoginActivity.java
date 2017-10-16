package curatetechnologies.com.curate.controllers;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import curatetechnologies.com.curate.MainActivity;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.network.FirebaseAPI;


/**
 * Created by mremondi on 10/9/17.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText email;
    private EditText password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        Button btnLogin = (Button) findViewById(R.id.login_button);
        Button btnRegister = (Button) findViewById(R.id.register_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });


    }

    private void signInUser(){
        String email = this.email.getText().toString();
        String password  = this.password.getText().toString();
        if (isEmailLegal(email) && isPasswordLegal(password)) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("USER LOG", "signInWithEmail:success");
                                        final FirebaseUser user = mAuth.getCurrentUser();
                                        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                                GetTokenResult result = task.getResult();
                                                FirebaseAPI.SHARED.updateToken(user, result.getToken());
                                            }
                                        });
                                        saveRestaurantID(user);
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("USER LOG", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }
                            });
        }
    }

    private void createUser() {
        String email = this.email.getText().toString();
        String password  = this.password.getText().toString();
        if (isEmailLegal(email) && isPasswordLegal(password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                final FirebaseUser user = mAuth.getCurrentUser();
                                user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                                        GetTokenResult result = task.getResult();
                                        FirebaseAPI.SHARED.addUserToDatabase(user, result.getToken());
                                    }
                                });

                                updateUI(user);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }

    }

    private boolean isEmailLegal(String email){
        if (email != null && email.contains("@") ){
            return true;
        }
        return false;
    }

    private boolean isPasswordLegal(String password){
        if (password != null && password.length() > 6){
            return true;
        }
        return false;
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        else{
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveRestaurantID(final FirebaseUser user){
        DatabaseReference ref = FirebaseAPI.SHARED.getUserRestaurantRef(user.getUid());
        Log.d("REF", ref.toString());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SharedPreferences.Editor editor = getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE).edit();
                editor.putString("restaurantID", (String) dataSnapshot.getValue());
                editor.apply();
                updateUI(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getMessage());
            }
        });
    }

}

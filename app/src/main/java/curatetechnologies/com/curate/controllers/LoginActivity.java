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
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import curatetechnologies.com.curate.MainActivity;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.network.FirebaseAPI;


/**
 * Created by mremondi on 10/9/17.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.login_email) EditText email;
    @BindView(R.id.login_password) EditText password;
    @OnClick(R.id.login_button) void loginClick(){
        signInUser();
    }
    @OnClick(R.id.register_button) void registerClick(){
        createUser();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
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
                                final FirebaseUser user = mAuth.getCurrentUser();
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
                                FirebaseAPI.SHARED.addUserToDatabase(user);
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
        Toast.makeText(this, "Your Password is too short. Make sure it is longer than 6 characters.", Toast.LENGTH_LONG).show();
        return false;
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            Intent i = new Intent(this, ChooseRestaurant.class);
            startActivity(i);
            finish();
            return;
        }
        else{
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.assignmentone.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentone.R;
import com.example.assignmentone.fragments.RegFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


    }

    public boolean validateEmail(String email) {
        boolean isValid = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+[.]+[a-zA-Z].+[a-zA-Z]$").matcher(email).matches();
        return isValid;
    }

    public void registerFunc(View view, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Navigation.findNavController(view).navigate(R.id.action_regFragment_to_logFragment);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"register not succeed",Toast.LENGTH_LONG).show();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    public void loginFunc(View view, String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this,"LOGGED IN!",Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_logFragment_to_homeAppFrag);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"User not found!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
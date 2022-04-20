package com.example.dolphy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    private EditText username, email, password;
    private Button signup;
    Switch privacy;
    DBHandler dbHandler;
    TextInputLayout textInputLayoutUsername;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.editTextUsernsme);
        email =(EditText) findViewById(R.id.editTextEmial);
        password = (EditText) findViewById(R.id.editTextPassword);
        signup = (Button) findViewById(R.id.SignupBtn);
        privacy = (Switch) findViewById(R.id.policy);
        layout = (ConstraintLayout)findViewById(R.id.signuplayout);
        textInputLayoutUsername = (TextInputLayout)findViewById(R.id.textInputLayoutUsername);
        textInputLayoutEmail = (TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);


        dbHandler = new DBHandler(SignupActivity.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_name = username.getText().toString();
                String e_mail = email.getText().toString();
                String pass_word = password.getText().toString();

                if (validate()){
                    if (privacy.isChecked()){
                        if(!dbHandler.isEmailExists(e_mail)) {
                            dbHandler.addUser( user_name, e_mail, pass_word);
                            Snackbar.make(layout, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, Snackbar.LENGTH_LONG);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Snackbar.make(layout, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Snackbar.make(layout, "please accept privacy and terms", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
    public boolean validate() {
        boolean valid = false;

        String UserName = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUsername.setError("Please enter valid username!\n");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUsername.setError(null);
            } else {
                valid = false;
                textInputLayoutUsername.setError("Username is to short!\n");
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!\n");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!\n");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!\n");
            }
        }
        return valid;
    }
}
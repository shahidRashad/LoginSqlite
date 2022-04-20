package com.example.dolphy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText uname, pwd;
    private Button login;
    DBHandler dbHandler;
    LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = (EditText) findViewById(R.id.LoginEmail);
        pwd = (EditText) findViewById(R.id.LoginPass);
        login = (Button) findViewById(R.id.loginBtn);
        dbHandler = new DBHandler(this);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layoutLog);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String password = pwd.getText().toString();
                loginModel = dbHandler.authenticate(new LoginModel(null,username,password));
                if (loginModel != null){
                    Snackbar.make(layout,"Successfully Logged in",Snackbar.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, Home.class);
                    startActivity(i);
                }
                else{
                    Snackbar.make(layout,"Login Failed",Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}
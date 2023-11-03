package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {
    private EditText user,pass;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.Login);

        DBHandler dbHandler=new DBHandler(this);
        login.setOnClickListener(view -> {
            String username=user.getText().toString();
            String password=pass.getText().toString();

            if(dbHandler.verify(username,password)){
                Intent intent=new Intent(getApplicationContext(),Success.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
            else
                Toast.makeText(LoginActivity2.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();

        });
    }
}
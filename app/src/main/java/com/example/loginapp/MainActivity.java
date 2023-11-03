package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText user,pass,rePass;
    private Button signUp,login;

    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*-----INITIALIZING THE TEXT FIELDS AND BUTTONS TO ACCESS-----*/
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        rePass=findViewById(R.id.rePassword);
        signUp=findViewById(R.id.signUp);
        login=findViewById(R.id.login);

        DBHandler dbHandler=new DBHandler(this);

        signUp.setOnClickListener(view -> {
            String username=user.getText().toString();
            String password=pass.getText().toString();
            String rePassword=rePass.getText().toString();
            if(username.equals("") || password.equals("")||rePassword.equals(""))
                Toast.makeText(MainActivity.this, "enter all fields", Toast.LENGTH_SHORT).show();
            else if(!password.equals(rePassword))
                Toast.makeText(MainActivity.this, "passwords doesn't match", Toast.LENGTH_SHORT).show();

            else{
                if(dbHandler.userExists(username))
                    Toast.makeText(MainActivity.this, "Username exist", Toast.LENGTH_SHORT).show();
                else {
                    if(dbHandler.insertValues(username, password)){
                        Intent intent=new Intent(getApplicationContext(),Success.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                }
            }
        });

        login.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(),LoginActivity2.class);
            startActivity(intent);
        });

    }
}
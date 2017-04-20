package com.example.aviral.openseseme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginButton ;
    private EditText userNameFeild, passwordFeild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        userNameFeild = (EditText) findViewById(R.id.userNameFeild);
        passwordFeild = (EditText) findViewById(R.id.passwordFeild);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameFeild.getText().toString();
                String password = passwordFeild.getText().toString();

                if(username.equals("danny") && password.equals("danny")){
//                    Toast.makeText(MainActivity.this, "Authorized User", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), LoggedIn.class);
                    startActivity(i);

                } else {
                    Toast.makeText(MainActivity.this, "Unauthorized User", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

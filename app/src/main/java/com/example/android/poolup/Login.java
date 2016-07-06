package com.example.android.poolup;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button signIn,signUp;
    EditText etEmailId, etPass;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailId = (EditText) findViewById(R.id.email_login);
        etPass = (EditText) findViewById(R.id.pass_login);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn:

                String email = etEmailId.getText().toString();
                String password = etPass.getText().toString();

                User user = new User(email, password);

                authenticate(user);

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);

                break;

            case R.id.signUp:
                startActivity(new Intent(this, Register.class ));
                break;
        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser == null)
                    showErrorMessage();
                else{
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Wrong Email or Password");
        dialogBuilder.setPositiveButton("Ok",null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, HomePage.class));
    }
}

package com.example.android.poolup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etEmailId,etPassword,etMob_no,etPolicy_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bRegister = (Button) findViewById(R.id.register);
        etEmailId = (EditText) findViewById(R.id.emailId);
        etPassword = (EditText) findViewById(R.id.password);
        etMob_no = (EditText) findViewById(R.id.mobile_no);
        etPolicy_no = (EditText) findViewById(R.id.policy_no);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:

                String email = etEmailId.getText().toString();
                String password = etPassword.getText().toString();
                String policy_no = etPolicy_no.getText().toString();
                long mob_no = Long.parseLong(etMob_no.getText().toString());

                User user = new User(email, password, mob_no, policy_no);
                registerUser(user);
                break;
        }
    }

    private void registerUser(User user) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
}

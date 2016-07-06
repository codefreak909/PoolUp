package com.example.android.poolup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Results extends AppCompatActivity implements View.OnClickListener{

    TextView user1,user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        user1 = (TextView) findViewById(R.id.user1);
        user2 = (TextView) findViewById(R.id.user2);

        user1.setOnClickListener(this);
        user2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.user1:
                startActivity(new Intent(this, viewProfile.class));
                break;

            case R.id.user2:
                startActivity(new Intent(this, viewProfile.class));
                break;
        }
    }
}

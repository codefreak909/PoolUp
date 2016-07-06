package com.example.android.poolup;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    Button profile,schedule,home,logout;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        profile = (Button) findViewById(R.id.profile_btn);
        schedule = (Button) findViewById(R.id.schedule_btn);
        home = (Button) findViewById(R.id.home_btn);
        logout = (Button) findViewById(R.id.logout_btn);

        profile.setOnClickListener(this);
        schedule.setOnClickListener(this);
        home.setOnClickListener(this);
        logout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(authenticate()==true) {
            displayUserDetails();
        } else {
            startActivity(new Intent(HomePage.this, Login.class));
        }
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails() {
        User user =  userLocalStore.getLoggedInUser();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_btn:

                startActivity(new Intent(this, Profile.class));

                break;

            case R.id.schedule_btn:

                startActivity(new Intent(this, Schedule.class));

                break;

            case R.id.home_btn:

                startActivity(new Intent(this, Home.class));

                break;

            case R.id.logout_btn:
                    userLocalStore.clearUserData();
                    userLocalStore.setUserLoggedIn(false);


                startActivity(new Intent(this, Login.class));

                break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_display_message,
                    container, false);
            return rootView;
        }
    }

}
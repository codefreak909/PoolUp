package com.example.android.poolup;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by A1Ze on 20-03-2016.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userdetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("email", user.email_id);
        spEditor.putString("password", user.password);
        spEditor.putLong("mob_no", user.mob_no);
        spEditor.putString("policy_no",user.policy_no);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String email = userLocalDatabase.getString("email", "");
        String password = userLocalDatabase.getString("password", "");
        long mob_no = userLocalDatabase.getInt("mob_no", -1);
        String policy_no = userLocalDatabase.getString("policy_no", "");

        User storedUser = new User(email,password,mob_no,policy_no);

        return storedUser;
    }

    public boolean getUserLoggedIn() {
        if(userLocalDatabase.getBoolean("loggedIn",false)==true)
            return true;
        else
            return false;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}

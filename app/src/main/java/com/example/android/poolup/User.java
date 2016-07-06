package com.example.android.poolup;

public class User {

    String email_id, password, policy_no;
    long mob_no;

    public User (String email, String password, long mob_no, String policy_no) {
        this.email_id = email;
        this.password = password;
        this.mob_no = mob_no;
        this.policy_no = policy_no;
    }

    public User(String email, String password) {
        this.email_id = email;
        this.password = password;
        this.mob_no = -1;
        this.policy_no = " ";
    }
}

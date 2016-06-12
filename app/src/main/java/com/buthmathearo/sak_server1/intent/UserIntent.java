package com.buthmathearo.sak_server1.intent;

import com.buthmathearo.sak_server1.model.User;
import com.buthmathearo.sak_server1.ui.UserDetail;

import android.content.Context;
import android.content.Intent;

/**
 * Created by buthmathearo on 6/12/16.
 */
public class UserIntent extends Intent{
    private static final String KEY_USER = "keyUser";

    public UserIntent(Context context, User user){
        super(context, UserDetail.class);
        putExtra(KEY_USER, user);
    }

    public UserIntent(Intent o) {
        super(o);
    }

    public User getUser(){
        return getParcelableExtra(KEY_USER);
    }
}

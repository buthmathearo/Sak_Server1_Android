package com.buthmathearo.sak_server1.ui;

import com.buthmathearo.sak_server1.R;
import com.buthmathearo.sak_server1.databinding.UserDetailBinding;
import com.buthmathearo.sak_server1.intent.UserIntent;
import com.buthmathearo.sak_server1.model.User;
import com.squareup.picasso.Picasso;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class UserDetail extends AppCompatActivity {

    private UserDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.user_detail);
        User user = new UserIntent(getIntent()).getUser();
        Picasso.with(this).load(user.getAvatar_url()).into(mBinding.imageView);


        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        mBinding.textLogin.setText(user.getLogin());
        mBinding.textType.setAnimation(animation);


        mBinding.textType.setText(user.getType());
    }
}

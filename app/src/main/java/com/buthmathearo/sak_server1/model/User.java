package com.buthmathearo.sak_server1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by buthmathearo on 6/12/16.
 */
public class User implements Parcelable {
    private String login;
    private String avatar_url;
    private String type;

    public User(String login, String avatar_url, String type) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.avatar_url);
        dest.writeString(this.type);
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.avatar_url = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

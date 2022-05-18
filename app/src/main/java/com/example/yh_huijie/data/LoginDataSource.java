package com.example.yh_huijie.data;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.yh_huijie.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            Log.e(TAG, "login before: username  "+username+"   password  "+password );




            if(username.equals("15168589418") && password.equals("123456"))
            {
                LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            ""+username);
            return new Result.Success<>(fakeUser);
            }
            else
            {
                return  null;
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
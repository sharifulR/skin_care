package com.wb.skincare.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager@Inject constructor(@ApplicationContext context: Context) {

    private var prefs= context.getSharedPreferences(Constants.PREFS_TOKEN_FILE,Context.MODE_PRIVATE)

    fun saveToken(token:String){
        val editor=prefs.edit()
        editor.putString(Constants.USER_TOKEN,token)
        editor.apply()
    }

    fun getToken():String?{
        return prefs.getString(Constants.USER_TOKEN,null)
    }

    var authToken: String?
        get() = prefs.getString("access_token", "")
        set(accessToken) = prefs.edit().putString("access_token", accessToken).apply()

    var isLogin: Boolean?
        get() = prefs.getBoolean("is_login", false)
        set(isLogin) = prefs.edit().putBoolean("is_login", isLogin!!).apply()


    var clientId: Int?
        get() = prefs.getInt("id", 0)
        set(id) = prefs.edit().putInt("id", id!!).apply()
    var mobile: String?
        get() = prefs.getString("mobile", "")
        set(mobile) = prefs.edit().putString("mobile", mobile).apply()
    var userEmail: String?
        get() = prefs.getString("email", "")
        set(email) = prefs.edit().putString("email", email).apply()

    var name: String?
        get() = prefs.getString("name", "")
        set(name) = prefs.edit().putString("name", name).apply()

    var userName: String?
        get() = prefs.getString("user_name", "")
        set(userName) = prefs.edit().putString("user_name", userName).apply()
    var userMobile: String?
        get() = prefs.getString("mobile", "")
        set(mobile) = prefs.edit().putString("mobile", mobile).apply()

    var user: Int?
        get() = prefs.getInt("user", 0)
        set(user) = prefs.edit().putInt("user", user!!).apply()
}
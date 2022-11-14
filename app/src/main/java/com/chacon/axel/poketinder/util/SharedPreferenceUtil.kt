package com.chacon.axel.poketinder.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.chacon.axel.poketinder.data.model.User
import com.google.gson.Gson

class SharedPreferenceUtil {
    companion object {
        private const val SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY"

        private lateinit var sharedPreference: SharedPreferences

        private const val USER = "USER_KEY"
    }

    fun setSharedPreference(context: Context) {
        sharedPreference = context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    fun saveUser(user: User) {
        val gson = Gson()

        val jsonUser = gson.toJson(user)

        Log.e("SharedPreferenceUtil", "jsonUser: $jsonUser")

        sharedPreference
            .edit()
            .putString(USER, jsonUser)
            .apply()
    }

    fun getUser(): User? {
        val gson = Gson()

        var user: User? = null

        val jsonUser = sharedPreference.getString(USER, "")

        user = gson.fromJson(jsonUser, User::class.java)

        return user
    }
}
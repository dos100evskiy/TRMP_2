// SharedPreferencesHelper.kt
package com.example.myapplication.utils

import android.app.Activity
import android.content.Context

object SharedPreferencesHelper {

    private const val PREFS_NAME = "user_prefs"
    private const val KEY_IS_AUTHENTICATED = "is_authenticated"
    private const val KEY_USER_LOGIN = "user_login"

    // Сохранение состояния аутентификации
    fun saveUserAuthStatus(context: Activity, isAuthenticated: Boolean, userLogin: String?) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_AUTHENTICATED, isAuthenticated)
        if (userLogin != null) {
            editor.putString(KEY_USER_LOGIN, userLogin)
        } else {
            editor.remove(KEY_USER_LOGIN)
        }
        editor.apply()
    }

    // Получение состояния аутентификации
    fun isUserAuthenticated(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_IS_AUTHENTICATED, false)
    }

    // Получение логина пользователя
    fun getUserLogin(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USER_LOGIN, null)  // Возвращаем логин пользователя или null, если он не найден
    }
}

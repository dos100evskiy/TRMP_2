package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import com.example.myapplication.Hotel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferencesHotels {

    private const val SHARED_PREFS_NAME = "HotelPrefs"
    private const val HOTELS_KEY = "hotels_list"
    private val gson = Gson()

    // Сохранение отеля в SharedPreferences
    fun addHotel(context: Activity, hotel: Hotel) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val hotelsJson = sharedPreferences.getString(HOTELS_KEY, "[]") ?: "[]"

        // Десериализуем текущие отели
        val type = object : TypeToken<ArrayList<Hotel>>() {}.type
        val hotels: ArrayList<Hotel> = gson.fromJson(hotelsJson, type)

        // Добавляем новый отель в список
        if (!hotels.contains(hotel)) {
            hotels.add(hotel)
        }

        // Сериализуем список отелей обратно в JSON
        val updatedHotelsJson = gson.toJson(hotels)

        // Сохраняем обновленный список в SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString(HOTELS_KEY, updatedHotelsJson)
        editor.apply()
    }

    // Удаление отеля из SharedPreferences
    fun delHotel(context: Activity, hotel: Hotel) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val hotelsJson = sharedPreferences.getString(HOTELS_KEY, "[]") ?: "[]"

        // Десериализуем текущие отели
        val type = object : TypeToken<ArrayList<Hotel>>() {}.type
        val hotels: ArrayList<Hotel> = gson.fromJson(hotelsJson, type)

        // Удаляем отель из списка
        if (hotels.contains(hotel)) {
            hotels.remove(hotel)
        }

        // Сериализуем список отелей обратно в JSON
        val updatedHotelsJson = gson.toJson(hotels)

        // Сохраняем обновленный список в SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString(HOTELS_KEY, updatedHotelsJson)
        editor.apply()
    }

    // Получение списка отелей из SharedPreferences
    fun getHotels(context: Context): ArrayList<Hotel> {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val hotelsJson = sharedPreferences.getString(HOTELS_KEY, "[]") ?: "[]"

        // Десериализуем JSON строку в список объектов Hotel
        val type = object : TypeToken<ArrayList<Hotel>>() {}.type
        return gson.fromJson(hotelsJson, type)
    }
}
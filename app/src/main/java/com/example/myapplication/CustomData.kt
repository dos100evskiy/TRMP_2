package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

// Реализация Parcelable для CustomData
data class CustomData(val serviceType: String, val servicePrice: Float) : Parcelable {

    // Описание содержимого, для Parcelable это обычно 0
    override fun describeContents(): Int {
        return 0
    }

    // Запись данных в Parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(serviceType)  // Записываем строковое значение
        dest.writeFloat(servicePrice)  // Записываем число с плавающей запятой
    }

    // Считывание данных из Parcel
    companion object CREATOR : Parcelable.Creator<CustomData> {
        override fun createFromParcel(parcel: Parcel): CustomData {
            // Чтение данных из Parcel
            val serviceType = parcel.readString() ?: ""  // Читаем строку
            val servicePrice = parcel.readFloat()  // Читаем число с плавающей запятой
            return CustomData(serviceType, servicePrice)
        }

        // Создание массива объектов CustomData
        override fun newArray(size: Int): Array<CustomData?> {
            return arrayOfNulls(size)
        }
    }
}
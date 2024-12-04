package com.example.myapplication

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class Hotel(
    val id: Int,
    val title: String,
    val location: String,
    val price: Float,
    val avalibality: List<LocalDateTime>,  // Сложный тип данных
    val services: MutableList<CustomData>,  // Сложный тип данных
    val rating: Float,
    val img: String
) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        mutableListOf<LocalDateTime>().apply {
            // Чтение List<LocalDateTime>
            val size = parcel.readInt()
            for (i in 0 until size) {
                val date = parcel.readSerializable() as LocalDateTime
                add(date)
            }
        },
        mutableListOf<CustomData>().apply {
            // Чтение MutableList<CustomData>
            val size = parcel.readInt()
            for (i in 0 until size) {
                val service = parcel.readParcelable<CustomData>(CustomData::class.java.classLoader)
                if (service != null) {
                    add(service)
                }
            }
        },
        parcel.readFloat(),
        parcel.readString() ?: ""
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(location)
        parcel.writeFloat(price)

        // Записываем List<LocalDateTime>
        parcel.writeInt(avalibality.size)
        for (date in avalibality) {
            parcel.writeSerializable(date)
        }

        // Записываем MutableList<CustomData>
        parcel.writeInt(services.size)
        for (service in services) {
            parcel.writeParcelable(service, flags)
        }

        parcel.writeFloat(rating)
        parcel.writeString(img)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Hotel> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFromParcel(parcel: Parcel): Hotel {
            return Hotel(parcel)
        }

        override fun newArray(size: Int): Array<Hotel?> {
            return arrayOfNulls(size)
        }
    }
}


/*public class Hotel(val id : Int,val title : String, val location : String, val price : Float,
                   val rating : Float, val img : String) {
}*/
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.utils.SharedPreferencesHotels
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter

class HotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hotel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val hotel : Hotel? = intent.getParcelableExtra("Hotel")

        if(hotel == null) {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
        findViewById<TextView>(R.id.titleHotel).text = hotel!!.title
        findViewById<TextView>(R.id.locationHotel).text = "Местоположение: ${hotel!!.location}"
        findViewById<TextView>(R.id.ratingHotel).text = "Рейтинг: ${hotel!!.rating.toString()}"
        findViewById<TextView>(R.id.priceHotel).text = "Цена: ${hotel!!.price.toString()}"
        Picasso.get().load(hotel.img).into(findViewById<ImageView>(R.id.imageHotel))

        val addBtn : Button = findViewById(R.id.addButton)

        var forSpinnerHotel = arrayListOf<String>()
        var counter : Int = 0
        var strBuff : StringBuilder = StringBuilder()

        for(avability in hotel.avalibality) {
            if(counter == 2) {
                counter = 0;
                forSpinnerHotel.add(strBuff.toString())
                strBuff.clear()
            } else if(counter == 1) {
                strBuff.append(" - ")
            }
            counter += 1
            strBuff.append(avability.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).substringBefore("."))
        }
        if(!strBuff.isEmpty()){
            forSpinnerHotel.add(strBuff.toString())
        }
        if(forSpinnerHotel.isEmpty()) {
            forSpinnerHotel.add("Нет доступных вариантов")
            addBtn.isEnabled = false;
        }


        val spinner = findViewById<Spinner>(R.id.spinnerHotel)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, forSpinnerHotel)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        strBuff.clear()
        for(serv in hotel.services) {
            strBuff.append(serv.serviceType)
        }
        findViewById<TextView>(R.id.servHotel).text = "Дополнительные услуги:\n ${if(strBuff.isEmpty())"отсутствуют" else strBuff.toString()}"


        val img :ImageView = findViewById(R.id.back)

        img.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        addBtn.setOnClickListener {
            SharedPreferencesHotels.addHotel(this, hotel)
        }
    }
}
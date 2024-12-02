package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomBar.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home ->replaceFragment(Home())
                R.id.cart -> replaceFragment(Cart())
                R.id.profile -> replaceFragment(Profile())
                R.id.settings -> replaceFragment(Settings())

                else ->{

                }
            }

            true
        }
        /*val hotelsList = findViewById<RecyclerView>(R.id.MainList)
        val hotels = arrayListOf<Hotel>()

*//*        hotels.add(Hotel(0, "Отель 1", "Тут 1", 111.1F,
            listOf<LocalDateTime>(), mutableListOf<CustomData>(), 1.1F, "src"))
        hotels.add(Hotel(1, "Отель 2", "Тут 2", 222.2F,
            listOf<LocalDateTime>(), mutableListOf<CustomData>(), 2.2F, "src"))
        hotels.add(Hotel(2, "Отель 3", "Тут 3", 3333.3F,
            listOf<LocalDateTime>(), mutableListOf<CustomData>(), 3.3F, "src"))*//*
                hotels.add(Hotel(0, "Отель 1", "Тут 1", 111.1F, 1.1F,
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2c/01/da/89/hotel-brooklyn-bridge.jpg?w=700&h=-1&s=1"))
        hotels.add(Hotel(1, "Отель 2", "Тут 2", 222.2F, 2.2F,
            "https://cdn.worldota.net/t/640x400/extranet/0b/2d/0b2d1c5b17b7b3ad2374be907f2430bdf2deb3be.JPEG"))
        hotels.add(Hotel(2, "Отель 3", "Тут 3", 3333.3F, 3.3F,
            "https://sapporo-hotel.ru/images/img-news-three-stars.jpg"))

        hotelsList.layoutManager = LinearLayoutManager(this)
        hotelsList.adapter = HotelsAdapter(hotels, this)*/


    }

    private fun replaceFragment(fragment: Fragment){

        val fragmenManager = supportFragmentManager
        val fragmentTransaction = fragmenManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }
}
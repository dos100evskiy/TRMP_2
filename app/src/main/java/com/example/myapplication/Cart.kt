package com.example.myapplication

import SharedPreferencesHotels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Cart.newInstance] factory method to
 * create an instance of this fragment.
 */
class Cart : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView : RecyclerView
    private  lateinit var btn : Button
    private  lateinit var empty : TextView

    private var hotels = arrayListOf<Hotel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_cart, container, false)
        recyclerView  = rootView.findViewById<RecyclerView>(R.id.recyclerCart)
        btn = rootView.findViewById<Button>(R.id.cartButton)
        empty = rootView.findViewById(R.id.EmptyText)

        hotels.add(Hotel(0, "Отель 1", "Тут 1", 111.1F, listOf<LocalDateTime>(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusWeeks(2)), mutableListOf<CustomData>(), 1.1F,
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/2c/01/da/89/hotel-brooklyn-bridge.jpg?w=700&h=-1&s=1"))
        hotels.add(Hotel(1, "Отель 2", "Тут 2", 222.2F, listOf<LocalDateTime>(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1)), mutableListOf<CustomData>(),2.2F,
            "https://cdn.worldota.net/t/640x400/extranet/0b/2d/0b2d1c5b17b7b3ad2374be907f2430bdf2deb3be.JPEG"))
        hotels.add(Hotel(2, "Отель 3", "Тут 3",  3333.3F, listOf<LocalDateTime>(), mutableListOf<CustomData>(CustomData("Бассейн", 34F)), 3.3F,
            "https://sapporo-hotel.ru/images/img-news-three-stars.jpg"))

        val toRemove = mutableListOf<Hotel>()
        for(hotel in hotels) {
            if(!SharedPreferencesHotels.isHotelSelected(requireContext(), hotel)) {
                toRemove.add(hotel)
            }
        }
        hotels.removeAll(toRemove)

        updateUI()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HotelsAdapterCart(hotels, requireContext(), ::updateUI)


        return rootView
    }

    private fun updateUI() {
        if (hotels.isEmpty()) {
            recyclerView.visibility = View.GONE
            btn.isEnabled = false
            empty.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            btn.isEnabled = true
            empty.visibility = View.GONE
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Cart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cart().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
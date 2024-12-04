import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.Hotel

object SharedPreferencesHotels {

    private lateinit var sharedPreferences: SharedPreferences

    // Ключ для хранения списка выбранных отелей
    private const val SELECTED_HOTELS_KEY = "selected_hotels"

    // Добавление отеля в список
    fun addHotel(context: Context, hotel: Hotel) {
        init(context)
        val hotelId = hotel.id.toString() // Идентификатор отеля для сохранения
        val selectedHotels = getSelectedHotels(context).toMutableSet()
        selectedHotels.add(hotelId)
        saveSelectedHotels(selectedHotels)
    }

    // Удаление отеля из списка
    fun removeHotel(context: Context, hotel: Hotel) {
        init(context)
        val hotelId = hotel.id.toString()
        val selectedHotels = getSelectedHotels(context).toMutableSet()
        selectedHotels.remove(hotelId)
        saveSelectedHotels(selectedHotels)
    }

    // Получение списка выбранных отелей
    fun getSelectedHotels(context: Context): Set<String> {
        init(context)
        return sharedPreferences.getStringSet(SELECTED_HOTELS_KEY, mutableSetOf()) ?: mutableSetOf()
    }

    // Проверка, выбран ли отель
    fun isHotelSelected(context: Context,hotel: Hotel): Boolean {
        init(context)
        return getSelectedHotels(context).contains(hotel.id.toString())
    }

    // Инициализация SharedPreferences
    private fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("SelectedHotelsPrefs", Context.MODE_PRIVATE)
    }

    // Сохранение списка выбранных отелей
    private fun saveSelectedHotels(selectedHotels: Set<String>) {
        val editor = sharedPreferences.edit()
        editor.putStringSet(SELECTED_HOTELS_KEY, selectedHotels)
        editor.apply()
    }
}
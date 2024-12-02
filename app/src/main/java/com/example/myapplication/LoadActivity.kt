package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.utils.SharedPreferencesHelper
import android.os.Handler

class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        val delayMillis: Long = 1500
        Handler().postDelayed({
            if(SharedPreferencesHelper.isUserAuthenticated(this)) {
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                val intent: Intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            }
            finish()  // Закрываем текущую активность, чтобы не было возврата к экрану Splash
        }, delayMillis) // Устанавливаем задержку

        enableEdgeToEdge()
        setContentView(R.layout.activity_load)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}
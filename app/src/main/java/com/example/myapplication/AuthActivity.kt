package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.utils.SharedPreferencesHelper

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(SharedPreferencesHelper.isUserAuthenticated(this)) {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginText = findViewById<EditText>(R.id.login)
        val passText = findViewById<EditText>(R.id.pass)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val login = loginText.text.toString().trim()
            val pass = passText.text.toString().trim()

            if(login == "" || pass == "")
                Toast.makeText(this, "Все поля дожны быить заполнены", Toast.LENGTH_LONG).show()
            else if(login == "login" && pass == "1") {
                SharedPreferencesHelper.saveUserAuthStatus(this, true, login)
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Ошибка авторизаации", Toast.LENGTH_LONG).show()
                loginText.text.clear()
                passText.text.clear()
            }

        }
    }
}
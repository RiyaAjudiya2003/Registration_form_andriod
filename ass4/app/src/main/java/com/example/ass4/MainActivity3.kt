package com.example.ass4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    val sharedpreferences = "loginshared"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var sharedpreferences = getSharedPreferences(sharedpreferences, Context.MODE_PRIVATE)
        val email = sharedpreferences.getString("EMAIL_KEY", null)

        val sucess = findViewById<TextView>(R.id.t9)
        sucess.text = "Sucessfully Logged in, $email"
        val btn4 = findViewById<Button>(R.id.btn4)
            btn4.setOnClickListener {
            val editor = sharedpreferences.edit()
            editor.clear()
                editor.apply()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
}}
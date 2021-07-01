package com.example.pendaftarankp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.lang.Exception

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        var btn_signup = findViewById<Button>(R.id.btn_signup)
        btn_signup.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}
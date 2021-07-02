package com.example.pendaftarankp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var btn_login = findViewById<Button>(R.id.btn_login)
        var btn_signup = findViewById<Button>(R.id.btn_signup)
        var input_email = findViewById<TextInputEditText>(R.id.emailInput)
        var input_password = findViewById<TextInputEditText>(R.id.passwordInput)
        var helper = SharedData.getInstance(this)
        btn_login.setOnClickListener {
            var email = input_email.text.toString().trim()
            var password = input_password.text.toString().trim()
            if (email == "" || password == "") {
                Toast.makeText(applicationContext, "Silahkan isi email/password dulu", Toast.LENGTH_SHORT).show()
            } else {
                var login = helper.dbo.login(email, password);
                if (login != null) {
                    helper.user = login
                    val intent = Intent(this, if (login.title == null) WelcomeActivity::class.java else ShowActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Email/Password salah, coba lagi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btn_signup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
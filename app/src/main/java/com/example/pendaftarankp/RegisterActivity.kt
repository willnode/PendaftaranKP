package com.example.pendaftarankp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var btn_signup = findViewById<Button>(R.id.btn_signup)
        var input_name = findViewById<TextInputEditText>(R.id.nameInput)
        var input_nim = findViewById<TextInputEditText>(R.id.nimInput)
        var input_email = findViewById<TextInputEditText>(R.id.emailInput)
        var input_password = findViewById<TextInputEditText>(R.id.passwordInput)
        var helper = SharedData.getInstance(this)
        btn_signup.setOnClickListener {
            try {
                if (input_name.text.toString() == "" || input_nim.text.toString() == "" || input_email.text.toString() == "" || input_password.text.toString() == "") {
                    Toast.makeText(applicationContext, "Silahkan lengkapi data dulu", Toast.LENGTH_SHORT).show()
                } else {
                    helper.dbo.register(Peserta(input_name.text.toString(), input_nim.text.toString(), input_email.text.toString(), input_password.text.toString()))
                    var login = helper.dbo.login(input_email.text.toString(), input_password.text.toString())
                    if (login != null) {
                        val intent = Intent(this, if (login.title == null) WelcomeActivity::class.java else ShowActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Terjadi kesalahan, Akun anda mungkin sudah dibuat", Toast.LENGTH_SHORT)
                    }
                }
            } catch (e:Exception) {
                Toast.makeText(applicationContext, "Terjadi kesalahan, Akun anda mungkin sudah dibuat", Toast.LENGTH_SHORT)
            }
        }
    }
}
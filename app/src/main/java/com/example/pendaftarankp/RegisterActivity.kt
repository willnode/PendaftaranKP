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
        var nameTest = """^[-a-zA-Z .']+$""".toRegex()
        var nimTest = """^\d{12}$""".toRegex()
        var passTest = """^.{8,}$""".toRegex()
        var emailTest = android.util.Patterns.EMAIL_ADDRESS

        btn_signup.setOnClickListener {
            try {
                var name = input_name.text.toString().trim()
                var nim = input_nim.text.toString().trim()
                var email = input_email.text.toString().trim()
                var password = input_password.text.toString().trim()
                if (name == "" || nim == "" || email == "" || password == "") {
                    Toast.makeText(applicationContext, "Silahkan lengkapi data dulu", Toast.LENGTH_SHORT).show()
                } else if (!nameTest.matches(name)) {
                    Toast.makeText(applicationContext, "Nama harus alfabet atau spasi, titik, strip dan petik satu", Toast.LENGTH_SHORT).show()
                } else if (!nimTest.matches(nim)) {
                    Toast.makeText(applicationContext, "Nim harus 12 digit", Toast.LENGTH_SHORT).show()
                } else if (!emailTest.matcher(email).matches()) {
                    Toast.makeText(applicationContext, "Email sepertinya tidak valid", Toast.LENGTH_SHORT).show()
                } else if (!passTest.matches(password)) {
                    Toast.makeText(applicationContext, "Password minimal 8 huruf", Toast.LENGTH_SHORT).show()
                } else {
                    helper.dbo.register(Peserta(input_name.text.toString(), input_nim.text.toString(), input_email.text.toString(), input_password.text.toString()))
                    var login = helper.dbo.login(input_email.text.toString(), input_password.text.toString())
                    if (login != null) {
                        helper.user = login
                        val intent = Intent(this, if (login.title == null) WelcomeActivity::class.java else ShowActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Terjadi kesalahan, Akun anda mungkin sudah dibuat", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e:Exception) {
                Toast.makeText(applicationContext, "Terjadi kesalahan, Akun anda mungkin sudah dibuat", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
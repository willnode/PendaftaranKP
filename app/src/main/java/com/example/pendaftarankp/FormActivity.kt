package com.example.pendaftarankp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        var btn_send = findViewById<Button>(R.id.btn_send)
        var input_name = findViewById<TextInputEditText>(R.id.nameInput)
        var input_nim = findViewById<TextInputEditText>(R.id.nimInput)
        var input_title = findViewById<TextInputEditText>(R.id.titleInput)
        var input_dospem = findViewById<TextInputEditText>(R.id.dospemInput)
        var input_dospen = findViewById<TextInputEditText>(R.id.dospenInput)
        var input_date = findViewById<TextInputEditText>(R.id.dateInput)
        var helper = SharedData.getInstance(this)
        var user = helper.user!!
        input_name.setText(user.name)
        input_nim.setText(user.nim)
        input_title.setText(user.title)
        input_dospem.setText(user.dospem)
        input_dospen.setText(user.dospen)
        input_date.setText(user.date)
        btn_send.setOnClickListener {
            try {
                if (input_name.text.toString() == "" || input_nim.text.toString() == "" || input_title.text.toString() == "" || input_dospem.text.toString() == "" || input_dospen.text.toString() == "" || input_date.text.toString() == "") {
                    Toast.makeText(applicationContext, "Silahkan lengkapi data dulu", Toast.LENGTH_SHORT).show()
                } else {
                    user.name = input_name.text.toString()
                    user.nim = input_nim.text.toString()
                    user.title = input_title.text.toString()
                    user.dospem = input_dospem.text.toString()
                    user.dospen = input_dospen.text.toString()
                    user.date = input_date.text.toString()
                    helper.dbo.save(user)

                    val intent = Intent(this, ShowActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
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
        var nameTest = """^[-a-zA-Z .']+$""".toRegex()
        var nimTest = """^\d{12}$""".toRegex()
        var dateTest = """^\d{2}-\d{2}-\d{4}$""".toRegex()



        btn_send.setOnClickListener {
            try {
                var name = input_name.text.toString().trim()
                var nim = input_nim.text.toString().trim()
                var title = input_title.text.toString().trim()
                var dospem = input_dospem.text.toString().trim()
                var dospen = input_dospen.text.toString().trim()
                var date = input_date.text.toString().trim()
                if (name == "" || nim == "" || title == "" || dospem == "" || dospen == "" || date == "") {
                    Toast.makeText(applicationContext, "Silahkan lengkapi data dulu", Toast.LENGTH_SHORT).show()
                } else if (!nameTest.matches(name)) {
                    Toast.makeText(applicationContext, "Nama harus alfabet atau spasi, titik, strip dan petik satu", Toast.LENGTH_SHORT).show()
                } else if (!nimTest.matches(nim)) {
                    Toast.makeText(applicationContext, "Nama harus alfabet atau spasi, titik, strip dan petik satu", Toast.LENGTH_SHORT).show()
                } else if (!nameTest.matches(dospem)) {
                    Toast.makeText(applicationContext, "Nama harus alfabet atau spasi, titik, strip dan petik satu", Toast.LENGTH_SHORT).show()
                } else if (!nameTest.matches(dospen)) {
                    Toast.makeText(applicationContext, "Nama harus alfabet atau spasi, titik, strip dan petik satu", Toast.LENGTH_SHORT).show()
                } else if (!dateTest.matches(date)) {
                    Toast.makeText(applicationContext, "Format tanggal harus dd-mm-yyyy", Toast.LENGTH_SHORT).show()
                } else {
                    user.name = name
                    user.nim = nim
                    user.title = title
                    user.dospem = dospem
                    user.dospen = dospen
                    user.date = date
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
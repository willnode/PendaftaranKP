package com.example.pendaftarankp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        var input_name = findViewById<TextView>(R.id.nameText)
        var input_nim = findViewById<TextView>(R.id.nimText)
        var input_title = findViewById<TextView>(R.id.titleText)
        var input_dospem = findViewById<TextView>(R.id.dospemText)
        var input_dospen = findViewById<TextView>(R.id.dospenText)
        var input_date = findViewById<TextView>(R.id.dateText)
        var helper = SharedData.getInstance(this)
        var user = helper.user!!
        input_name.text = user.name
        input_nim.text = user.nim
        input_title.text = user.title
        input_dospem.text = user.dospem
        input_dospen.text = user.dospen
        input_date.text = user.date

        var btn_edit = findViewById<Button>(R.id.btn_edit)
        btn_edit.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
        var btn_logout = findViewById<Button>(R.id.btn_logout)
        btn_logout.setOnClickListener {
            helper.user = null
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
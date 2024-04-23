package com.johnpinilla.androidmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result2)

        val tvResult = findViewById<TextView>(R.id.textResult)

        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()

        tvResult.text = "Hola ${name}"
    }
}
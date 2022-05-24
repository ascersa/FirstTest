package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class KotlinActivity : AppCompatActivity() {

    val pets = arrayOf("cat", "dog")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        for (element in pets){

        }
    }
}
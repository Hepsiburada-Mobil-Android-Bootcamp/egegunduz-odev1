package com.example.ege_odev1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    val homeTextView by lazy { findViewById<TextView>(R.id.textview_home)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeTextView.text = "Home Activity"
    }
}
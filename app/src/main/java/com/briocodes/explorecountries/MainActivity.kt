package com.briocodes.explorecountries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var langButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        langButton = findViewById(R.id.language_btn)
        langButton.setOnClickListener(View.OnClickListener {  v ->
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        })
    }
}
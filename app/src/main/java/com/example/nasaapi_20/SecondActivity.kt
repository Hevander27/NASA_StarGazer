package com.example.nasaapi_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val img_detail = findViewById<ImageView>(R.id.detailImageView)
        val cap_detail = findViewById<TextView>(R.id.captionView)
        val title_detail = findViewById<TextView>(R.id.detailTitleView)

        val intent = intent

        val image = intent?.getIntExtra("image",0)
        val title =  intent?.getStringExtra("title")
        val explanation = intent?.getStringExtra("explanation")

        if (image != null) {
            img_detail.setImageResource(image)
        }

        title_detail.text = title
        cap_detail.text = explanation

    }
}
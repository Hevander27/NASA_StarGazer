package com.example.nasaapi_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val img_detail = findViewById<ImageView>(R.id.detailImageView)
        val cap_detail = findViewById<TextView>(R.id.captionView)
        val title_detail = findViewById<TextView>(R.id.detailTitleView)

        val intent = intent


        val title =  intent?.getStringExtra("title")
        val explanation = intent?.getStringExtra("explanation")

        val imageUrl = intent?.getStringExtra("image")
        if (imageUrl != null) {
            Glide.with(this).load(imageUrl).into(img_detail)
        }

        title_detail.text = title
        cap_detail.text = explanation

    }
}
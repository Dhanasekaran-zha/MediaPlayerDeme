package com.example.mediaplayerdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gotoaudio.setOnClickListener(this)
        gotovideo.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val detailed = Intent(this, DetailedActivity::class.java)
        when (p0!!.id) {
            gotoaudio.id -> detailed.putExtra("player", 1)
            gotovideo.id -> detailed.putExtra("player", 2)
        }
        startActivity(detailed)
    }
}
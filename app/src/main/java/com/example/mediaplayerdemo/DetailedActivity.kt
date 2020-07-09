package com.example.mediaplayerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val fragmentId=intent.getIntExtra("player",0)
        when(fragmentId){
            1->setTransaction(AudioFrag())
            2->setTransaction(VideoFrag())
        }
    }

    fun setTransaction(fragment: Fragment){
        val manager=supportFragmentManager
        fragment.arguments=intent.extras
        val transaction=manager.beginTransaction()
        transaction.add(R.id.fLayout,fragment)
        transaction.commit()
    }
}
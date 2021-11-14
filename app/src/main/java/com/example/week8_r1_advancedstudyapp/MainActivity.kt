package com.example.week8_r1_advancedstudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.week8_r1_advancedstudyapp.fragments.AndriodFragment
import com.example.week8_r1_advancedstudyapp.fragments.KotlinFragment

class MainActivity : AppCompatActivity() {
    lateinit var aBtn:Button
    lateinit var kBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aBtn=findViewById(R.id.a_btn)
        kBtn=findViewById(R.id.k_btn)

        aBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frams, AndriodFragment()).commit()
        }

        kBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frams, KotlinFragment()).commit()
        }

    }
}
package com.mominul.radd_e_qadyyani

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mominul.radd_e_qadyyani.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}
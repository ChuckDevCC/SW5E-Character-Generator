package com.chuckdevcc.sw5echaractergenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val newFragment = CreateCharacterFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, newFragment)
                .commit()
        }


    }
}
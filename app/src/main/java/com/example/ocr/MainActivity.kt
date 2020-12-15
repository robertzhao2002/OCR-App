package com.example.ocr

import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_audio.*

class MainActivity : AppCompatActivity() {

    lateinit var bottom_home_nav : BottomNavigationView
    lateinit var homeFragment: HomeFragment
    lateinit var historyFragment: HistoryFragment

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "OCR"
    private var historylist = arrayListOf<OCRItem>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val sharedPref: SharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        AsyncTask.execute {
            if (sharedPref.getString("history", null) == null) {
                Log.d("Hello", "START")
                val gson = Gson()
                val json: String = gson.toJson(historylist)
                val editor = sharedPref.edit()
                editor.putString("history", json)
                editor.apply()
            }
        }

        val a = supportActionBar
        a!!.hide()

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom_home_nav = findViewById(R.id.home_nav)

        bottom_home_nav.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.home_item -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.history_item -> {
                    historyFragment = HistoryFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, historyFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
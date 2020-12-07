package com.example.ocr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class CurrentActivity : AppCompatActivity(){
    lateinit var bottom_nav : BottomNavigationView
    lateinit var audioFragment: AudioFragment
    lateinit var textFragment: TextFragment
    lateinit var fulltext : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.current_note)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        fulltext = layoutInflater.inflate(R.layout.fragment_text, RelativeLayout(this@CurrentActivity)).findViewById(R.id.textbody)
        Log.d("1",fulltext.text.toString())
        var currentItem : OCRItem? = OCRItem("TEMP", "TEMP", "TEMP")

        currentItem = intent.getParcelableExtra("item")

//        val a = supportActionBar
//        a!!.setDisplayHomeAsUpEnabled(true)
        fulltext.text = currentItem!!.full_text
        Log.d("1", currentItem!!.full_text)

        audioFragment = AudioFragment()
        fulltext.text = currentItem!!.full_text
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, audioFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottom_nav = findViewById(R.id.note_nav)

        bottom_nav.setOnNavigationItemSelectedListener OnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.audio_item -> {
                    audioFragment = AudioFragment()
                    fulltext.text = currentItem!!.full_text
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame, audioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.text_item -> {
                    textFragment = TextFragment()
                    fulltext.text = currentItem!!.full_text
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame, textFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
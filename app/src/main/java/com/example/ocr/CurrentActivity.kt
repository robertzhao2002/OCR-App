package com.example.ocr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        var extracttext : String? = null
        var photoD : ByteArray? = null

        Log.d("1",fulltext.text.toString())
        var currentItem : OCRItem? = null

        currentItem = intent.getParcelableExtra("item")

        intent.extras?.let{bundle ->
            extracttext = bundle.get("full_text") as String?
            photoD = bundle.get("pic") as ByteArray?
        }

        if (currentItem != null) fulltext.text = currentItem!!.full_text
        if (photoD != null && extracttext != null) {
            currentItem = OCRItem(extracttext!!, image_byte = photoD, audio = "TEMP")
            fulltext.text = currentItem!!.full_text
        }

        Log.d("1", currentItem!!.full_text)

        val a = supportActionBar
        a!!.title = currentItem!!.full_text
        a!!.setDisplayHomeAsUpEnabled(true)


//        audioFragment = AudioFragment.newInstance(images = currentItem!!.image_preview)
        audioFragment = AudioFragment.newInstance(images1 = currentItem!!.image_byte!!)
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
//                    audioFragment = AudioFragment.newInstance(images = currentItem!!.image_preview)
                    audioFragment = AudioFragment.newInstance(images1 = currentItem!!.image_byte!!)
                    fulltext.text = currentItem!!.full_text
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame, audioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.text_item -> {
                    textFragment = TextFragment.newInstance(currentItem!!.full_text)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
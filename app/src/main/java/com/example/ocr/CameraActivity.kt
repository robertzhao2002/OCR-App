package com.example.ocr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult


class CameraActivity : AppCompatActivity() {
    lateinit var camera : CameraView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snap)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val a = supportActionBar
        a!!.hide()
        camera = findViewById(R.id.camera)
        camera.setLifecycleOwner(this)

        camera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result : PictureResult) {
                // A Picture was taken!
            }

            override fun onVideoTaken(result : VideoResult) {
                // A Video was taken!
            }

            // And much more
        })
        camera.takePicture()

    }


}
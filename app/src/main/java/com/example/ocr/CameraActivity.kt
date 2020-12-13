package com.example.ocr


import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.api.client.extensions.android.json.AndroidJsonFactory
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.vision.v1.Vision
import com.google.api.services.vision.v1.VisionRequestInitializer
import com.google.api.services.vision.v1.model.*
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult
import java.io.File
import java.io.FileOutputStream
import java.util.*


class CameraActivity : AppCompatActivity() {
    lateinit var camera : CameraView
    lateinit var cameraButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snap)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val a = supportActionBar
        a!!.hide()
        camera = findViewById(R.id.camera)
        camera.setLifecycleOwner(this)
        cameraButton = findViewById(R.id.snapButton)

        camera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                super.onPictureTaken(result)
                val photo = SavePhotoTask(result.data, UUID.randomUUID().toString() + ".jpg")
                val resultIntent = Intent()
                resultIntent.putExtra("is_video", false)
                resultIntent.putExtra("source_data", photo?.absoluteFile.toString())
                setResult(RESULT_OK, resultIntent)

                val visionBuilder = Vision.Builder(NetHttpTransport(), AndroidJsonFactory(), null)

                visionBuilder.setVisionRequestInitializer(
                    VisionRequestInitializer("SECRET LMAOOOOOOOOO")
                )
                val vision = visionBuilder.build()

                var full_text : String = "DEFAULT"

                AsyncTask.execute {
                    val photoData: ByteArray = result.data
                    val inputImage = Image()
                    inputImage.encodeContent(photoData)
                    val desiredFeature = Feature()
                    desiredFeature.type = "TEXT_DETECTION"
                    val request = AnnotateImageRequest()
                    request.image = inputImage
                    request.features = listOf(desiredFeature)
                    val batchRequest = BatchAnnotateImagesRequest()

                    batchRequest.requests = listOf(request)
                    val batchResponse = vision.images().annotate(batchRequest).execute()
                    val text: TextAnnotation = batchResponse.responses[0].fullTextAnnotation
                    full_text = text.text
                    Log.d("detected text", full_text)
                    val intent = Intent(this@CameraActivity, CurrentActivity::class.java).apply {
                        putExtra("full_text", full_text)
                    }
                    startActivity(intent)
                    finish()
                }
                Toast.makeText(applicationContext, "RENDERING ...", Toast.LENGTH_LONG).show()
                finish()
            }

            override fun onVideoTaken(result: VideoResult) {
                // A Video was taken!
            }

            // And much more
        })

        cameraButton.setOnClickListener {
            camera.takePicture()
        }


    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun SavePhotoTask(jpeg: ByteArray?, fileName: String) : File? {
        val imagesFolder: File = File(cacheDir, "ProjectIllusion")
        imagesFolder.mkdirs()
        val photo = File(imagesFolder, fileName)
        try {
            val fos = FileOutputStream(photo.path)
            fos.write(jpeg)
            fos.close()
            return photo
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}

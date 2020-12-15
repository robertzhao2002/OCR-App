package com.example.ocr

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.historyrow.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImagePreviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImagePreviewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: ByteArray? = null

    lateinit var previewImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getByteArray(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_image_preview, container, false)
        previewImage = root.findViewById(R.id.preview)
        if(param2 != null) {
            Log.d("PICTURE", "PREVIEW")
            val bitmap = BitmapFactory.decodeByteArray(param2!!, 0, param2!!.size)
            previewImage.setImageBitmap(bitmap)
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImagePreviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int? = null, param2: ByteArray? = null) =
            ImagePreviewFragment().apply {
                arguments = Bundle().apply {
                    putByteArray(ARG_PARAM2, param2)
                }
            }
        }
}
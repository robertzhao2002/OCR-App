package com.example.ocr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "images"
private const val ARG_PARAM2 = "images1"

/**
 * A simple [Fragment] subclass.
 * Use the [AudioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AudioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var images: IntArray? = null
    private var images1 : ByteArray? = null

    private var imageList = arrayListOf<Int>(R.drawable.ic_launcher_foreground)
    private var image1List= arrayListOf<ByteArray>()
    private lateinit var viewPager: ViewPager2
    private lateinit var playpause: ImageView
    private var playing: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            images = it.getIntArray(ARG_PARAM1)
            images1 = it.getByteArray(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_audio, container, false)
        playpause = root.findViewById(R.id.playpause)
        playpause.setOnClickListener {
            if(playing)
                playpause.setImageResource(android.R.drawable.ic_media_pause)
            else
                playpause.setImageResource(android.R.drawable.ic_media_play)
            playing = !playing
        }
        if(images1 != null) {
            Log.d("SDFSDF", "SDFSDF")
            image1List.add(images1!!)
            Log.d("SDFSDF", image1List[0].toString())
        }
        viewPager = root.findViewById(R.id.vp)
        val pagerAdapter = SlidePagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param images Parameter 1.
         * @param images1 Parameter 2.
         * @return A new instance of fragment AudioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(images: IntArray? = null, images1: ByteArray? = null) =
                AudioFragment().apply {
                    arguments = Bundle().apply {
                        putIntArray(ARG_PARAM1, images)
                        putByteArray(ARG_PARAM2, images1)
                    }
                }
    }

    private inner class SlidePagerAdapter(fragmentActivity: FragmentActivity):
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            Log.d("WHY", image1List.size.toString())
            return image1List.size
        }

        override fun createFragment(position: Int) : Fragment {
            return ImagePreviewFragment.newInstance(param2 = image1List[position])
        }
    }
}
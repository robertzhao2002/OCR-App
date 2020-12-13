package com.example.ocr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "images"

/**
 * A simple [Fragment] subclass.
 * Use the [AudioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AudioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var images: IntArray? = null

    private var imageList = arrayListOf(R.drawable.ic_launcher_foreground)
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            images = it.getIntArray(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_audio, container, false)
        imageList = arrayListOf()
        for (i in images!!.indices) {
            imageList.add(images!![i])
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
         * @param param2 Parameter 2.
         * @return A new instance of fragment AudioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(images: IntArray) =
                AudioFragment().apply {
                    arguments = Bundle().apply {
                        putIntArray(ARG_PARAM1, images)
                    }
                }
    }

    private inner class SlidePagerAdapter(fragmentActivity: FragmentActivity):
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return imageList.size
        }

        override fun createFragment(position: Int): Fragment {
            return ImagePreviewFragment.newInstance(imageList[position])
        }
    }
}
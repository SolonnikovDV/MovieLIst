package com.example.movielistonfragments.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.movielistonfragments.R
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreenFragment : Fragment() {

    companion object {
        const val TAG = "SplashScreenFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.findViewById<View>(R.id.banner_image).startAnimation(animation)
        loading_bar.apply {
            progressMax = 100f
            setProgressWithAnimation(100f, 4000)
        }
    }
}
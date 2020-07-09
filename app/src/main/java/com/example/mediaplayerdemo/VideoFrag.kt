package com.example.mediaplayerdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.video_fragment.*
import java.io.File

class VideoFrag: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.video_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoView.setVideoPath("android.resource://"+context!!.packageName+"/"+R.raw.videoplayback)
        val mediaController=MediaController(activity)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        activity!!.videoView.setMediaController(mediaController)
        videoView.start()
    }
}
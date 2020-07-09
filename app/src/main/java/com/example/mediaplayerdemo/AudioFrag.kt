package com.example.mediaplayerdemo

import android.app.Service
import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.audio_fragment.*
import java.util.*
import android.widget.SeekBar.OnSeekBarChangeListener as OnSeekBarChangeListener1


class AudioFrag: Fragment() {
    var mediaPlayer: MediaPlayer? = null
    var audioManager: AudioManager? = null
    var playOrPause=0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.audio_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer.create(activity,R.raw.anthimalaineram)
        play.setOnClickListener {
            playPause()
        }
        audioTimeSeekBar()
        volumeSeekBar()

    }
/**
 * to change the volume of the playing audio
 * */
    private fun volumeSeekBar() {
        audioManager = activity!!.getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume=audioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val currentVolume=audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumerocker.max=maxVolume
        volumerocker.progress=currentVolume
        volumerocker.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i("VolumeChanged",p1.toString())
                audioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC,p1,0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
/**
 * to go to differnt time duration in the track
 * */
    private fun audioTimeSeekBar() {
            audioTimer.max=mediaPlayer!!.duration
            audioTimer.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    Log.i("SeekBar Moved",p1.toString())
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    mediaPlayer!!.seekTo(p0!!.progress)
                }
            })

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                audioTimer.progress=mediaPlayer!!.currentPosition
            }
        }, 0, 100)
    }
/**
 * to play and pause the music
 * */
    private fun playPause() {
        if(playOrPause==0){
            mediaPlayer!!.start()
            playOrPause=1
            play.setImageResource(R.drawable.ic_baseline_pause_24)
        }else{
            mediaPlayer!!.pause()
            playOrPause=0
            play.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        }
    }
}
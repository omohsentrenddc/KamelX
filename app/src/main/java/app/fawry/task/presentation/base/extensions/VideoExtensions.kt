package com.trenddc.hashksa.task.presentation.base.extensions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultAllocator
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.gson.Gson
import com.trenddc.hashksa.R
import com.trenddc.hashksa.task.presentation.base.custom_views.TimePicker
import com.trenddc.hashksa.task.presentation.base.utils.isUrlValid
import java.text.SimpleDateFormat
import java.util.*

fun PlayerView.loadVideo(videoUrl: String, progress: ProgressBar) {
  if(videoUrl.isUrlValid()) {
    val loadControl: LoadControl = DefaultLoadControl.Builder()
      .setAllocator(DefaultAllocator(true, 16))
      .setBufferDurationsMs(
        2000,
        5000,
        1500,
        2000
      )
      .setTargetBufferBytes(-1)
      .setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl()


    val mediaDataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
      context,
      Util.getUserAgent(context, "mediaPlayerSample")
    )
    val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
      .createMediaSource(MediaItem.fromUri(videoUrl))
    val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

    val player = SimpleExoPlayer.Builder(context)
      .setMediaSourceFactory(mediaSourceFactory)
      .setLoadControl(loadControl)
      .build()
    player.addMediaSource(mediaSource)
    player.prepare()
    hideController()
    useController = true
    player.volume = 0f
    player.addListener(object : Player.Listener {
      override fun onPlaybackStateChanged(state: Int) {
        if (state == ExoPlayer.STATE_BUFFERING) {
          progress.show()
        } else {
          progress.hide()
        }
      }
    })
  }
}
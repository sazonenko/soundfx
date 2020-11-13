package ru.recoder.soundfx.service

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log


class MediaService {
    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(context: Context?, url: String?) {
        if (mediaPlayer != null) {
            killMediaPlayer()
        }
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, Uri.parse(url))
        }
        mediaPlayer?.setOnCompletionListener { killMediaPlayer() }
        mediaPlayer?.start()
    }

    private fun killMediaPlayer() {
        try {
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            Log.e(javaClass.name, "Error killing mediaPlayer", e)
        }
    }
}
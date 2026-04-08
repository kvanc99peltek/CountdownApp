package edu.temple.startingserviceslab

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountdownService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())
    private var countdownJob: Job? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val value = intent?.getIntExtra("countdown_value", 0) ?: 0

        // Cancel any existing countdown
        countdownJob?.cancel()

        countdownJob = serviceScope.launch {
            for (i in value downTo 1) {
                Log.i("CountdownService", "Countdown: $i")
                delay(1000)
            }
            Log.i("CountdownService", "Countdown complete!")
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownJob?.cancel()
    }
}

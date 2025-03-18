package com.wpay.core.data.remote

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import com.wpay.core.data.database.UserDatabase
import com.wpay.core.data.database.entity.SessionDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionTrackingService : Service() {

    private var sessionStartTime: Long = 0L
    private var userId: String? = null
    private var screenName: String? = null

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    @Inject lateinit var db: UserDatabase

    override fun onCreate() {
        super.onCreate()
        sessionStartTime = SystemClock.elapsedRealtime()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        userId = intent?.getStringExtra(EXTRA_USER_ID)
        screenName = intent?.getStringExtra(EXTRA_SCREEN_NAME)

        return START_STICKY
    }

    override fun onDestroy() {
        val sessionEndTime = SystemClock.elapsedRealtime()
        val duration = (sessionEndTime - sessionStartTime) / 1000

        serviceScope.launch {
            saveSessionDuration(duration)
            stopSelf()
        }
    }

    private suspend fun saveSessionDuration(duration: Long) {
        val sessionDao = db.sessionDurationDao()
        val session = userId?.let {
            SessionDuration(userId = it, screenName = screenName ?: "unknown screen name", duration = duration)
        } ?: return
        sessionDao.insertSession(session)
    }

    override fun onBind(intent: Intent?): IBinder? { return null }

    companion object {
        private const val EXTRA_USER_ID = "EXTRA_USER_ID"
        private const val EXTRA_SCREEN_NAME = "EXTRA_SCREEN_NAME"

        fun createSessionTrackingIntent(context: Context, userId: String, screenName: String): Intent {
            return Intent(context, SessionTrackingService::class.java).apply {
                putExtra(EXTRA_USER_ID, userId)
                putExtra(EXTRA_SCREEN_NAME, screenName)
            }
        }
    }
}

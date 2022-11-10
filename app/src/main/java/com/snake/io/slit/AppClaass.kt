package com.snake.io.slit

import android.app.Application
import android.util.Log
import com.my.tracker.MyTracker
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import com.snake.io.slit.Const.INSTID
import com.snake.io.slit.Const.MYID
import com.snake.io.slit.Const.ONESIGNAL_APP_ID
import com.snake.io.slit.Const.SDK_KEY
import java.util.*

class AppClaass: Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        Hawk.init(this).build()
        val trackerParams = MyTracker.getTrackerParams()
        val trackerConfig = MyTracker.getTrackerConfig()
        val instID = MyTracker.getInstanceId(this)
        trackerConfig.isTrackingLaunchEnabled=true
        val idOfficial = UUID.randomUUID().toString()
        trackerParams.setCustomUserId(idOfficial)
        Hawk.put(MYID, idOfficial)
        Hawk.put(INSTID, instID)
        MyTracker.initTracker(SDK_KEY, this)


    }
}
package org.koin.sampleapp

import android.app.Application
import android.content.pm.ApplicationInfo
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.WeathericonsModule
import org.koin.Koin
import org.koin.android.ext.android.bindString
import org.koin.android.ext.android.startAndroidContext
import org.koin.android.logger.AndroidLogger
import org.koin.sampleapp.di.RemoteDataSourceModule
import org.koin.sampleapp.di.weatherAppModules

/**
 * Main Application
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startAndroidContext(this, weatherAppModules())

        // Display some logs
        val isDebug = (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
        if (isDebug) {
            Koin.logger = AndroidLogger()
        }

        // bind R.string.server_url to Koin RemoteDataSourceModule.SERVER_URL property
        bindString(R.string.server_url, RemoteDataSourceModule.SERVER_URL)

        Iconify.with(WeathericonsModule())
    }
}
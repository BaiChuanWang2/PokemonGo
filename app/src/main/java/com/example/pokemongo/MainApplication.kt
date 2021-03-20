package com.example.pokemongo

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.pokemongo.di.appModule
import com.example.pokemongo.di.retrofitModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(appModule, retrofitModule))
        }
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        AppHelper.initAppContext(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {

    }
}
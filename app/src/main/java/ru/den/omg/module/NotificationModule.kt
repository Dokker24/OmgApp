package ru.den.omg.module

import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationChannel(): NotificationChannel =
        NotificationChannel(
            "Time_Notivication",
            "Time",
            NotificationManager.IMPORTANCE_DEFAULT
        )
}
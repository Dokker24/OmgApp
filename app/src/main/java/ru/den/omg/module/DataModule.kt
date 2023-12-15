package ru.den.omg.module


import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.den.omg.data.Mine_Data24
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Mine_Data24 = Room.databaseBuilder(
            app,
            Mine_Data24::class.java,
            "Weeks.db"
        ).build()


}
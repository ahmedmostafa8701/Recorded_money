package com.am_apps.recorded_money.di

import android.app.Application
import androidx.room.Room
import com.am_apps.recorded_money.db.RecordDatabase
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.home_page.data.RecordLocalRepoRoomImpl
import com.am_apps.recorded_money.features.home_page.domain.RecordLocalRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): RecordDatabase = Room.databaseBuilder(
            application,
            RecordDatabase::class.java,
            "recorded_money_db"
        ).build()

    @Provides
    @Singleton
    fun provideRecordDao(database: RecordDatabase): RecordDoa = database.recordDoa()

    @Provides
    @Singleton
    fun provideRecordLocalRepo(recordDao: RecordDoa): RecordLocalRepo = RecordLocalRepoRoomImpl(recordDao)

}
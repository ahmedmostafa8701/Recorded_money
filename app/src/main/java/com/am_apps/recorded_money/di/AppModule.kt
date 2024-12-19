package com.am_apps.recorded_money.di

import android.app.Application
import androidx.room.Room
import com.am_apps.recorded_money.db.RecordDatabase
import com.am_apps.recorded_money.db.doa.RecordDoa
import com.am_apps.recorded_money.features.attachments.data.AttachmentRepoImpl
import com.am_apps.recorded_money.features.attachments.domain.repo.AttachmentRepo
import com.am_apps.recorded_money.features.home_page.data.RecordLocalRepoRoomImpl
import com.am_apps.recorded_money.features.home_page.domain.RecordLocalRepo
import com.am_apps.recorded_money.features.reminder.data.repo.AlarmRepoRoomImpl
import com.am_apps.recorded_money.features.reminder.domain.repo.AlarmRepo
import com.am_apps.recorded_money.features.tasks.data.TaskLocalRepoImpl
import com.am_apps.recorded_money.features.tasks.domain.TasksLocalRepo
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

    @Provides
    @Singleton
    fun provideAttachmentRepo(application: Application): AttachmentRepo = AttachmentRepoImpl(application)

    @Provides
    @Singleton
    fun provideTasksRepo(recordDao: RecordDoa, application: Application): TasksLocalRepo = TaskLocalRepoImpl(recordDao, application)

    @Provides
    @Singleton
    fun provideAlarmRepo(recordDao: RecordDoa): AlarmRepo = AlarmRepoRoomImpl(recordDao)
}

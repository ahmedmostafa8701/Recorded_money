package com.am_apps.recorded_money.features.reminder

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.core.domain.model.TaskModel

class TaskNotification(private val context: Context, private val taskModel: TaskModel) {
    private val notification =
        NotificationCompat.Builder(context, context.getString(R.string.task_reminder_channel_id))
            .setSmallIcon(R.drawable.cash_icon)
            .setContentTitle(context.getString(R.string.task_notification_title))
            .setContentText("Description: ${taskModel.description}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

    fun notifyTask() {
        NotificationManagerCompat.from(context).apply {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notification_permission_status.value = false
                return
            }
            notify("task ${taskModel.id}".hashCode(), notification)
        }
    }

    companion object {
        const val TASK_NOTIFICATION_PERMISSION_REQUEST_CODE = 1
        val notification_permission_status = mutableStateOf(false)
    }
}
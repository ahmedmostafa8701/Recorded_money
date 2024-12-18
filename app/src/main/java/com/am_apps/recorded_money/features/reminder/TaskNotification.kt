package com.am_apps.recorded_money.features.reminder

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.am_apps.recorded_money.R
import com.am_apps.recorded_money.core.domain.model.TaskModel

class TaskNotification(private val context: Context, private val taskModel: TaskModel) {
    private val builder =
        NotificationCompat.Builder(context, context.getString(R.string.task_reminder_channel_id))
            .setContentTitle(context.getString(R.string.task_notification_title))
            .setContentText(taskModel.description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

    fun notify(){
        NotificationManagerCompat.from(context).apply{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
            }
            notify("task ${taskModel.id}".hashCode(), builder.build())
        }
    }

    companion object {
        const val TASK_NOTIFICATION_PERMISSION_REQUEST_CODE = 1
    }
}
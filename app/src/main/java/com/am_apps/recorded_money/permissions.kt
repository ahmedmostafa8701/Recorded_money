package com.am_apps.recorded_money

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

const val PERMISSION_REQUEST_CODE: Int = 1001

fun grantPermissions(context: Context, permissions: ArrayList<String> = arrayListOf()) {

    permissions.forEach { permission ->
        if (ActivityCompat.checkSelfPermission(
                context,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(permission),
                PERMISSION_REQUEST_CODE
            )
        }
    }
}
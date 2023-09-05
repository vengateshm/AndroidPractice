package com.android.vengateshm.androidpractice.floating_window

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

object Common {
    var currentDesc = ""
    var savedDesc = ""
}

fun Context.isServiceRunning(serviceName: String): Boolean {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (info in activityManager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceName == info.service.className) return true
    }
    return false
}

fun Activity.requestOverlayDisplayPermission(
    onPositiveButtonClicked: () -> Unit,
) {
    val builder = AlertDialog.Builder(this)
    builder.setCancelable(true)
    builder.setTitle("Screen Overlay Permission Needed")
    builder.setMessage("Enable 'Display over other apps' from System Settings.")

    builder.setPositiveButton("Open Settings") { _, _ ->
        onPositiveButtonClicked()
    }
    val dialog = builder.create()
    dialog.show()
}

fun Context.checkOverlayDisplayPermission(): Boolean {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        // If 'Display over other apps' is not enabled it
        // will return false or else true
        Settings.canDrawOverlays(this)
    } else {
        // Below Marshmallow doesn't need 'Display over other apps' permission enabling
        true
    }
}

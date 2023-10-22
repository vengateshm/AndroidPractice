package com.android.vengateshm.androidpractice.intent_queries

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntentQueriesSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = ScrollView(this)
        val rootLp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rootLayout.layoutParams = rootLp

        val ll = LinearLayout(this)
        val llLp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ll.layoutParams = llLp
        ll.orientation = LinearLayout.VERTICAL

        val tv = TextView(this)

        ll.addView(tv)
        rootLayout.addView(ll)
        setContentView(rootLayout)

        val allPackages = StringBuilder()
        getInstalledApplications(packageManager)
            .forEach {
                allPackages.append(it.packageName).append("\n\n")
            }
        tv.text = allPackages.toString()

        val intent = Intent(Intent.ACTION_VIEW)
//        intent.type = "text/plain"
        val activities = queryActivitiesForIntent(packageManager, intent)
        val queryIntents = StringBuilder()
        for (resolveInfo in activities) {
            val appName = resolveInfo.activityInfo.applicationInfo.loadLabel(packageManager).toString()
            val activityName = resolveInfo.activityInfo.name
            queryIntents.append("App: $appName\nActivity: $activityName").append("\n\n")
        }
        tv.text = queryIntents.toString()
    }
}

fun getInstalledApplications(packageManager: PackageManager): List<ApplicationInfo> {
    return packageManager.getInstalledApplications(ApplicationInfo.FLAG_INSTALLED)
}

fun queryActivitiesForIntent(packageManager: PackageManager, intent: Intent): List<ResolveInfo> {
    return packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
}
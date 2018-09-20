package engineer.com.profile

import android.app.Activity
import android.app.Application
import android.util.Log
import com.squareup.leakcanary.AndroidExcludedRefs

import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.squareup.leakcanary.internal.ActivityLifecycleCallbacksAdapter
import engineer.com.profile.leakcanary.LeakedActivity
import java.util.concurrent.TimeUnit

/**
 * @author: rookie
 * @date: 2018-09-20
 * @desc
 */
class ProfileApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setUpLeakCanary()
    }

    private fun setUpLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            initLeakCanary()
        }

    }

    private fun initLeakCanary(): RefWatcher {
        // exclude some reference

        val excludedRefs = AndroidExcludedRefs.createAndroidDefaults()
                .instanceField("engineer.com.profile.leakcanary.LeakedActivity", "myObserver")
                .build()

        val refWatcher = LeakCanary.refWatcher(this)
                .watchDelay(10, TimeUnit.SECONDS)
                .watchActivities(false)
                .excludedRefs(excludedRefs)
                .buildAndInstall()

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacksAdapter() {
            override fun onActivityDestroyed(activity: Activity?) {
                super.onActivityDestroyed(activity)
                if (activity is LeakedActivity) {
                    return
                }

                refWatcher.watch(activity)
            }
        })

        Log.e("profiler","excludes==\n"+excludedRefs.toString())

        return refWatcher
    }
}

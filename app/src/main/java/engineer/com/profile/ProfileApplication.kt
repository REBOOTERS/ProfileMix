package engineer.com.profile

import android.app.Application

import com.squareup.leakcanary.LeakCanary

/**
 * @author: zhuyongging
 * @date: 2018-09-20
 * @desc
 */
class ProfileApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setUpLeakCanary()
    }

    private fun setUpLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        LeakCanary.install(this)
    }
}

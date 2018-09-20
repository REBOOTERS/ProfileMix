package engineer.com.profile.leakcanary

import android.os.Bundle
import android.util.Log
import engineer.com.profile.R
import engineer.com.profile.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_leaked.*
import java.util.concurrent.TimeUnit

class LeakedActivity : BaseActivity() {

    private val myObserver = MyObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaked)
        leak()
    }

    private fun leak() {


        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver)
    }

    private inner class MyObserver: Observer<Long> {
        override fun onNext(t: Long) {
            Log.e(TAG, "onNext: t==$t")
            textView2.text = "count==$t"
        }

        override fun onSubscribe(d: Disposable) {
            Log.e(TAG, "onSubscribe: d==$d")
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onComplete() {
            Log.e(TAG, "onComplete: ")
        }

    }
}

package engineer.com.profile.leakcanary

import android.os.Bundle
import android.util.Log
import engineer.com.profile.R
import engineer.com.profile.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_no_leaked.*
import java.util.concurrent.TimeUnit

class NoLeakedActivity : BaseActivity() {
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_leaked)

        mCompositeDisposable.add(Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, "it==$it")
                    textView3.text= "count==$it"
                }, {
                    it.printStackTrace()
                }))
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.clear()
        }
    }
}

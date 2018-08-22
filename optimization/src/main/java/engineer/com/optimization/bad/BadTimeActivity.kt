package engineer.com.optimization.bad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import engineer.com.optimization.R
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val TAG = "BAD"

class BadTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bad_time)


        Observable.create(ObservableOnSubscribe<String> { emitter ->
            run {
                emitter.onNext("1111")
                emitter.onComplete()
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, it)
                }, {
                    Log.e(TAG, it.toString())
                }, {
                    Log.e(TAG, "complete")
                }, {
                    Log.e(TAG, "it")
                })
    }
}

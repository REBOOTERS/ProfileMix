package engineer.com.optimization.good;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import engineer.com.optimization.R;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class GoodActivity extends AppCompatActivity {
    private static final String TAG = "GoodActivity";
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        goodCase();
    }

    private void goodCase() {
        mCompositeDisposable.add(Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.e(TAG, "accept: aLong==" + aLong)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}

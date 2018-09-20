package engineer.com.optimization.bad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import engineer.com.optimization.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class BadActivity extends AppCompatActivity {
    private static final String TAG = "BadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad);
        badCase();
    }

    private void badCase() {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.e(TAG, "accept: along==" + aLong));
    }
}

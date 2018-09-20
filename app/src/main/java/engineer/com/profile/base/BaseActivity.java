package engineer.com.profile.base;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author: rookie
 * @date: 2018-09-20
 * @desc
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity{
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}

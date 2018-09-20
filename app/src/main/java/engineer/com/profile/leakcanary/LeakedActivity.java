package engineer.com.profile.leakcanary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import engineer.com.profile.R;

public class LeakedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaked);
    }
}

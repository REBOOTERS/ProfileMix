package engineer.com.profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import engineer.com.profile.adapter.Adapter
import engineer.com.profile.leakcanary.LeakedActivity
import engineer.com.profile.leakcanary.NoLeakedActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener {

    lateinit var pages: MutableList<ActivityModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPages()
        val adapter = Adapter(pages)
        adapter.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onClick(model: ActivityModel) {
        val mIntent = Intent(this, model.className)
        startActivity(mIntent)
    }


    private fun initPages() {
        pages = ArrayList()
        pages.add(ActivityModel("leaked_activity", LeakedActivity::class.java))
        pages.add(ActivityModel("no_leaked_activity", NoLeakedActivity::class.java))
    }
}

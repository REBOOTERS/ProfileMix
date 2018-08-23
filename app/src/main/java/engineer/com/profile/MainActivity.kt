package engineer.com.profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import engineer.com.optimization.bad.BadTimeActivity
import engineer.com.optimization.good.GoodTimeActivity
import engineer.com.profile.adapter.Adapter
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


     private  fun initPages() {
        pages=ArrayList()
        pages.add(ActivityModel("profile_bad", BadTimeActivity::class.java))
        pages.add(ActivityModel("profile_good", GoodTimeActivity::class.java))
    }
}

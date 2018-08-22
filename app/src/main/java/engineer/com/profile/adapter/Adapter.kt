package engineer.com.profile.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import engineer.com.profile.ActivityModel
import engineer.com.profile.R
import java8.util.Optional

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/8/16
 * desc   :
 * version: 1.0
 */

class Adapter : RecyclerView.Adapter<Adapter.MyHolder> {

    private var pages: MutableList<ActivityModel>
    private lateinit var mContext: Context
    private lateinit var mListener: OnItemClickListener

    constructor(pages: MutableList<ActivityModel>) {
        this.pages = pages
    }

    fun setListener(mOnItemClickListener: OnItemClickListener) {
        mListener = mOnItemClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        mContext = p0.context
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_layout, p0, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
       p0.textView.text=pages[p1].name
        p0.cardView.setOnClickListener {
            Optional.of(mListener).ifPresent {
                mListener.onClick(pages[p1])
            }
        }
    }

    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardView: CardView = view.findViewById(R.id.item)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    interface OnItemClickListener {
        fun onClick(model:ActivityModel)
    }
}
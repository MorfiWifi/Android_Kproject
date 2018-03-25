package com.apps.morfiwifi.morfi_project_samane.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.apps.morfiwifi.morfi_project_samane.R

/**
 * Created by WifiMorfi on 3/25/2018.
 */
class ViewHolder_kot(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var image: ImageView
    var t1: TextView
    var t2: TextView
    var t3: TextView
    var im3dot: ImageView

    init {
        image = itemView.findViewById<View>(R.id.im_message_image) as ImageView
        im3dot = itemView.findViewById<View>(R.id.im_message_more) as ImageView
        t1 = itemView.findViewById<View>(R.id.tv_message_t1) as TextView
        t2 = itemView.findViewById<View>(R.id.tv_message_t2) as TextView
        t3 = itemView.findViewById<View>(R.id.tv_message_t3) as TextView
    }
}
package com.apps.morfiwifi.morfi_project_samane.view

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.apps.morfiwifi.morfi_project_samane.R
import com.apps.morfiwifi.morfi_project_samane.models.Message

/**
 * Created by WifiMorfi on 3/25/2018.
 */
class message_RecyclerAdapter_kot (private val messages: List<Message>) : RecyclerView.Adapter<ViewHolder_message>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_message {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return ViewHolder_message(view)
    }


    override fun onBindViewHolder(holder: ViewHolder_message, position: Int) {
        val sample_message = messages[position]
        // TODO: 3/25/2018  Choose Aproprate Image for Message (Be aware !)
        // TODO: 3/25/2018  ocnverting Date To persian One!!
        // {holder.image = case {type of User} }
        holder.t3.text = sample_message.Send_Date
        holder.t1.text = sample_message.Sender_ID // Use Message Header Insetead!
        holder.t3.text = sample_message.Tags // Minimall Tags! (Not All OF Them !)


    }


    override fun getItemCount(): Int {
        return messages.size
    }

    companion object {
        private var recyclerView: RecyclerView? = null


        fun Init(messages: List<Message>, activity: AppCompatActivity) {
            recyclerView = activity.findViewById(R.id.rec_messages_recycle)
            //recyclerView.refreshDrawableState();
            val linearLayoutManager = LinearLayoutManager(activity)
            recyclerView!!.layoutManager = linearLayoutManager
            recyclerView!!.setHasFixedSize(false)
            recyclerView!!.adapter = message_RecyclerAdapter(messages)
        }
    }
}
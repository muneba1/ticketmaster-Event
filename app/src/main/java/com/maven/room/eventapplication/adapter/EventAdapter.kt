package com.maven.room.eventapplication.adapter

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maven.room.eventapplication.R
import com.maven.room.eventapplication.databinding.RowEventBinding
import com.maven.room.eventapplication.model.Events
import com.maven.room.eventapplication.utils.DateFormatUtil
import com.maven.room.eventapplication.utils.DiffUtilCallBack
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_event.view.*
import java.util.*


class EventAdapter(private val listener: OnItemClickListener) :
    PagedListAdapter<Events, EventAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: RowEventBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_event,
            parent,
            false
        )
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindEvent(it, listener)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val eventName: TextView = itemView.Event_TextView_eventName
        private val eventDate: TextView = itemView.Event_TextView_eventDate
        private val eventImage: ImageView = itemView.Event_ImageView_image
        private val favorite: ImageView = itemView.Event_ImageView_favorite

        fun bindEvent(event: Events, onItemClickListener: OnItemClickListener) {
            with(event) {
                eventName.text = name
                    val eventStartDate: String = if (dates.start.dateTime != null) {
                        dates.start.dateTime
                    } else {
                        dates.start.localDate
                    }
                eventDate.text = DateFormat.format(
                    "E MMMM dd,yyyy hh:mm a",
                    DateFormatUtil().dateFormat(eventStartDate)
                )
                Picasso.get()
                    .load(event.images[1].url)
                    .into(eventImage)
            }
            favorite.setOnClickListener { onItemClickListener.onFavoriteClick(event, favorite) }
            eventImage.setOnClickListener { onItemClickListener.onItemClick(event) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Events?)
        fun onFavoriteClick(item: Events?, view: View)
    }
}
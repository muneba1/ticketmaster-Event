package com.maven.room.eventapplication.utils

import androidx.recyclerview.widget.DiffUtil
import com.maven.room.eventapplication.model.Events

class DiffUtilCallBack : DiffUtil.ItemCallback<Events>() {
    override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
        return oldItem.name == newItem.name
                && oldItem.type == newItem.type
    }
}
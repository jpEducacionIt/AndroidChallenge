package com.eventbrite.androidchallenge.ui.events

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eventbrite.androidchallenge.databinding.ItemListBinding
import com.eventbrite.androidchallenge.ui.model.Event
import javax.inject.Inject

class EventsAdapter @Inject constructor() : ListAdapter<Event, EventsAdapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Event) -> Unit

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (mEvent: Event) {

            with(binding)  {
                event = mEvent
                executePendingBindings()
                root.setOnClickListener {
                    if(::onItemClickListener.isInitialized) {
                        onItemClickListener(mEvent)
                    } else {
                        Log.i("TEST", "Adapter listener in fragment?")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EventsAdapter.ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}

@BindingAdapter("loadImage", "placeholder")
fun loadImage(imageViewUrl: ImageView, url: String, placeholder: Drawable) {
    Glide.with(imageViewUrl)
        .load(url)
        .placeholder(placeholder)
        .into(imageViewUrl)
}

@BindingAdapter("date")
fun date(itemlist_date: TextView, date: String) {
    itemlist_date.text = date.subSequence(0, 10).toString().lowercase()
}

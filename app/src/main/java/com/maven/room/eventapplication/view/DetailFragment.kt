package com.maven.room.eventapplication.view

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.maven.room.eventapplication.R
import com.maven.room.eventapplication.databinding.FragmentDetailBinding
import com.maven.room.eventapplication.utils.DateFormatUtil
import com.squareup.picasso.Picasso

const val EVENT_NAME = "event_name"
const val EVENT_DATE = "event_date"
const val EVENT_TYPE = "event_type"
const val EVENT_IMAGE_URL = "event_image_url"
const val EVENT_GENRE = "event_genre"


class DetailFragment : Fragment() {
    private var eventName: String? = null
    private var eventDate: String? = null
    private var eventType: String? = null
    private var eventImage: String? = null
    private var eventGenre: String? = null
    lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventName = it.getString(EVENT_NAME)
            eventDate = it.getString(EVENT_DATE)
            eventType = it.getString(EVENT_TYPE)
            eventImage = it.getString(EVENT_IMAGE_URL)
            eventGenre = it.getString(EVENT_GENRE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapDetailData()
    }


    private fun mapDetailData() {
        binding.EventDetailTextViewEventTitle.text = eventName
        binding.EventDetailTextViewEventDate.text =
            DateFormat.format(
                "E MMMM dd,yyyy hh:mm a",
                eventDate?.let { DateFormatUtil().dateFormat(it) }
            )

        binding.EventDetailTextViewEventType.text =
            resources.getString(R.string.event_type) + " " + eventType

        binding.EventDetailTextViewEventGenre.text =
            resources.getString(R.string.event_genre) + " " + eventGenre
        Picasso.get()
            .load(eventImage)
            .into(binding.EventDetailImageViewEventImage)
    }
}
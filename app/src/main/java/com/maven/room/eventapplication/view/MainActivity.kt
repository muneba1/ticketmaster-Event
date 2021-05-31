package com.maven.room.eventapplication.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.TextureView
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.maven.room.eventapplication.R
import com.maven.room.eventapplication.adapter.EventAdapter
import com.maven.room.eventapplication.database.Event
import com.maven.room.eventapplication.databinding.ActivityMainBinding
import com.maven.room.eventapplication.model.Events
import com.maven.room.eventapplication.root.EventApplication
import com.maven.room.eventapplication.viewModel.EventViewModel
import com.maven.room.eventapplication.viewModel.EventViewModelFactory


class MainActivity : AppCompatActivity(), EventAdapter.OnItemClickListener {
    private val eventAdapter = EventAdapter(this)
    private var binding: ActivityMainBinding? = null
    var progressBar: ProgressBar? = null

    private val eventViewModel: EventViewModel by viewModels {
        EventViewModelFactory((application as EventApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.action_bar)
        val view = supportActionBar?.customView

        observeLiveData()
        initializeList()
    }

    private fun observeLiveData() {

        eventViewModel.getEvents().observe(this, {
            hideProgressBar()
            eventAdapter.submitList(it)
        })
    }

    private fun initializeList() {
        binding?.EventRecyclerViewEventList?.adapter = eventAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onFavoriteClick(item: Events?, view: View) {
        item?.let {
            val eventDb = item.let {
                Event(
                    it.id,
                    it.name,
                    it.type,
                    it.images[1].url,
                    it.dates.start.dateTime
                )
            }
            eventViewModel.insert(event = eventDb)
            view.setBackgroundResource(R.drawable.ic_favorite)
        }
        // TODO: 5/3o/2021 unselect favorite work

    }

    override fun onItemClick(item: Events?) {

        val bundle = Bundle()

        val eventStartDate: String? = if (item?.dates?.start?.dateTime != null) {
            item.dates.start.dateTime
        } else {
            item?.dates?.start?.localDate
        }

        bundle.putString(EVENT_NAME, item?.name)
        bundle.putString(EVENT_DATE, eventStartDate)
        bundle.putString(EVENT_TYPE, item?.type)
        bundle.putString(EVENT_IMAGE_URL, item?.images?.get(1)?.url)
        bundle.putString(
            EVENT_GENRE,
            item?.classifications?.get(0)?.genre?.name + " (" + item?.classifications?.get(0)?.subGenre?.name + " )"
        )
        openFragment(bundle = bundle, fragment = DetailFragment())
    }

    private fun openFragment(bundle: Bundle, fragment: Fragment) {
        binding?.EventRecyclerViewEventList?.visibility = View.GONE
        binding?.EventRecyclerViewEventList?.animate()?.alpha(0.0f)

        binding?.EventFrameLayoutContainer?.visibility = View.VISIBLE

        val someFragment: Fragment = fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit);

        transaction.replace(R.id.Event_FrameLayout_container, someFragment)
        someFragment.arguments = bundle
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding?.EventRecyclerViewEventList?.visibility = View.VISIBLE
        binding?.EventRecyclerViewEventList?.animate()?.alpha(5.0f)
    }

    private fun hideProgressBar() {
        binding?.EventProgressBarLoader?.visibility = View.GONE
    }
}

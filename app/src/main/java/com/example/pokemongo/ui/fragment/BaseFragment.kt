package com.example.pokemongo.ui.fragment

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pokemongo.R

open class BaseFragment : Fragment() {
    fun initToolbar(toolbar: Toolbar) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_baseline_navigate_before_24)
                title = ""
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> (activity as AppCompatActivity).onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
package ru.recoder.soundfx.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.recoder.soundfx.ui.main.WebListFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun createFragment(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> SimpleListFragment()
            1 -> WebListFragment()
            else -> {
                PlaceholderFragment.newInstance(position + 1)
            }
        }
    }

    override fun getItemCount(): Int {
        // Show 2 total pages.
        return 3
    }
}
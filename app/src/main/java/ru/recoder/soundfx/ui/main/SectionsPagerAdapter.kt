package ru.recoder.soundfx.ui.main

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.recoder.soundfx.service.SiteAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList: ArrayList<Fragment> = ArrayList(3)
    private val site = SiteAdapter()


    fun getItem(position: Int): Fragment? {
        return fragmentList.get(position)
    }

    fun getPageFragment() : WebPageFragment {
        if (fragmentList.size < 3) {
            fragmentList.add(WebPageFragment(site))
            notifyDataSetChanged()
        }
        return fragmentList[2] as WebPageFragment
    }

    override fun createFragment(position: Int): Fragment {
        Log.d(javaClass.name, "createFragment for page $position")
        if (position >= fragmentList.size) {
            fragmentList.add(position,
                    when (position) {
                        0 -> SimpleListFragment()
                        1 -> WebRootFragment(site)
                        else -> WebPageFragment(site)
                    }
            )
        }
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return Math.max(2, fragmentList.size)
    }
}
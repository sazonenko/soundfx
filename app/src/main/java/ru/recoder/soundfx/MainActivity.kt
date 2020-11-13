package ru.recoder.soundfx

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.recoder.soundfx.service.MediaService
import ru.recoder.soundfx.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private val mediaService = MediaService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = SectionsPagerAdapter(this)

        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) {
            tab, position -> tab.text = when(position) {
                0 -> "Phone"
                1 -> "Web"
                else -> "Tab ${position+1}"
            }
        }.attach()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    fun getPageAdapter() : SectionsPagerAdapter {
        return viewPager.adapter as SectionsPagerAdapter
    }

    fun showPage(pos : Int) {
        Log.d(javaClass.name, "showPage ${viewPager.currentItem} -> $pos")
        if (pos in 0..2) {
            viewPager.currentItem = pos
        } else {
            Log.w(javaClass.name, "Incorrect page number: $pos")
        }
    }

    fun playSound(url : String) {

        mediaService.playAudio(applicationContext, url)
    }
}
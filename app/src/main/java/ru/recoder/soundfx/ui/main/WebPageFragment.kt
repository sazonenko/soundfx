package ru.recoder.soundfx.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment
import ru.recoder.soundfx.MainActivity
import ru.recoder.soundfx.model.WebItem
import ru.recoder.soundfx.service.SiteAdapter

class WebPageFragment(val site: SiteAdapter) : ListFragment() {

    private var adapter : ArrayAdapter<WebItem>? = null
    private val currentData = ArrayList<WebItem>()
    private var url : String? = null
    private var updated = false;

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1, currentData
        )
        listAdapter = adapter
        setListShown(false)
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        Toast.makeText(activity, "Вы выбрали : $position) ${currentData[position]}", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).playSound(currentData[position].url)
    }

    override fun onResume() {
        Log.d(javaClass.name, "onResume $url $updated")
        if (!updated ) {
            loadData(url)
            updated = true
        }
        super.onResume()
    }

    fun setUrl(newUrl : String) {
        Log.d(javaClass.name, "setUrl $url -> $newUrl")
        if (newUrl != url) {
            url = newUrl
            updated = false
        }
    }

    fun loadData(nullableUrl : String?) {
        val url = nullableUrl ?: return
        setListShown(false)
        Thread {
            try {
                val result = site.loadCategory(url)
                activity?.runOnUiThread {
                    updateWithNewData(result)
                    setListShown(true)
                }
            } catch (e: Exception) {
                Log.e(javaClass.name, "Error loading data", e)
                activity?.runOnUiThread {
                    Toast.makeText(activity, "Error loading data: $e", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }

    private fun updateWithNewData(result: List<WebItem>) {
        currentData.clear()
        currentData.addAll(result)
        adapter?.notifyDataSetChanged()
    }
}
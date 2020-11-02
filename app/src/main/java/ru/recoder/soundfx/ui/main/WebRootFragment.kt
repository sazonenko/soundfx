package ru.recoder.soundfx.ui.main

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment
import ru.recoder.soundfx.MainActivity
import ru.recoder.soundfx.model.WebItem
import ru.recoder.soundfx.service.SiteAdapter

class WebRootFragment(val site: SiteAdapter) : ListFragment() {

    private var adapter : ArrayAdapter<WebItem>? = null
    private val currentData = ArrayList<WebItem>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1, currentData
        )
        listAdapter = adapter
        loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.name, "view created ${Environment.getExternalStorageDirectory()}")
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        Toast.makeText(activity, "Вы выбрали : $position) ${currentData[position]}", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).apply {
            getPageAdapter().getPageFragment().setUrl(currentData[position].url)
            showPage(2)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(javaClass.name, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(javaClass.name, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(javaClass.name, "onPause")
    }

    private fun loadData() {
        setListShown(false)
        Thread {
            try {
                val result = site.loadRoot()
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
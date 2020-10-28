package ru.recoder.soundfx.ui.main

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment
import org.jsoup.select.Elements
import ru.recoder.soundfx.model.WebItem

class WebListFragment : ListFragment() {

    private var adapter : ArrayAdapter<WebItem>? = null
    private var currentData : ArrayList<WebItem> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1, currentData
        )
        loadData { loadCategories("https://noisefx.ru/", ".entry-content li a") }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.name, "view created ${Environment.getExternalStorageDirectory()}")
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        Toast.makeText(activity, "Вы выбрали : $position) ${currentData[position]}", Toast.LENGTH_SHORT).show()
        loadData{ loadCategories(currentData[position].url, ".v a.hre b") }
    }

    fun loadData(getList: () -> List<WebItem>) {
        Thread {
            try {
                val result = getList()
                activity?.runOnUiThread { updateWithNewData(result) }
            } catch (e: Exception) {
                activity?.runOnUiThread {
                    Log.e(javaClass.name, "Error loading data", e)
                    Toast.makeText(activity, "Error loading data: $e", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }

    fun loadCategories(url : String, selector : String) : List<WebItem> {
        Log.d(javaClass.name, "before load $url")
        val result : ArrayList<WebItem> = ArrayList()
        val doc: Document = Jsoup.connect(url).get()
        val elements: Elements = doc.select(selector)
        result.addAll( elements.map {
            e -> WebItem(e.text(), e.absUrl("href"))
        })
        return result
    }

    fun updateWithNewData(result: List<WebItem>) {
        currentData.clear()
        currentData.addAll(result)
        adapter?.notifyDataSetChanged()
        listAdapter = adapter
    }
}
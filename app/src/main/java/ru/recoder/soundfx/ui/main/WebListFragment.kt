package ru.recoder.soundfx.ui.main

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import org.jsoup.select.Elements

class WebListFragment : ListFragment() {

    private var adapter : ArrayAdapter<String>? = null
    private var currentData : ArrayList<String> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1, currentData
        )
        listAdapter = adapter
        Thread {
            val categories = loadCategories()
            activity?.runOnUiThread { updateWithNewData(categories) }

        }.start()
    }

    fun loadCategories() : List<String> {
        val result : ArrayList<String> = ArrayList()
        try {
            val doc: Document = Jsoup.connect("https://noisefx.ru/").get()
            val elements: Elements = doc.select(".entry-content li a")
            result.addAll( elements.map { e -> e.text() } )
        } catch (e: Exception) {
            println("error loading data: $e")
        }
        return result
    }

    fun updateWithNewData(result: List<String>) {
        currentData.clear()
        currentData.addAll(result)
        adapter?.notifyDataSetChanged()
    }
}
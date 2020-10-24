package ru.recoder.soundfx.ui.main

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import org.jsoup.select.Elements

public class WebListFragment : ListFragment() {

    private var adapter : ArrayAdapter<String>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(
                activity!!,
                android.R.layout.simple_list_item_1, getCategories()
        )
        listAdapter = adapter
    }

    fun getCategories() : List<String> {
        val doc: Document = Jsoup.connect("https://noisefx.ru/").get()
        println(doc.title())
        val elements: Elements = doc.select(".entry-content li a")

/*
        val list : ArrayList<String> =  ArrayList(elements.size)
        for (headline in elements) {
            list.add(headline.text())
            println( String.format( "%s\n\t%s",
                    headline.text(), headline.absUrl("href")
            ))
        }
*/
        return elements.map { e -> e.text()}
    }

}
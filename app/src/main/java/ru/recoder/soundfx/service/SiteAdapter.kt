package ru.recoder.soundfx.service

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import ru.recoder.soundfx.model.WebItem

class SiteAdapter {


    fun loadRoot() : List<WebItem> {
        return loadCategories("https://noisefx.ru/", ".entry-content li a")
    }

    fun loadCategory(url : String) : List<WebItem> {
        return loadCategories(url, ".v a.hre b")
    }

    fun loadCategories(url : String, selector : String) : List<WebItem> {
        Log.d(javaClass.name, "before load $url")
        return Jsoup.connect(url).get()
                .select(selector)
                .map { e -> WebItem(e.text(), e.absUrl("href")) }
    }

}
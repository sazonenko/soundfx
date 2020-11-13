package ru.recoder.soundfx.service

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import ru.recoder.soundfx.model.WebItem
import ru.recoder.soundfx.model.WebSoundItem

class SiteAdapter {


    fun loadRoot() : List<WebItem> {
        return loadCategories("https://noisefx.ru/", ".entry-content li a")
    }

    fun loadCategory(url : String) : List<WebSoundItem> {
        val sounds = Jsoup.connect(url).get().select("div.v")
        return sounds.map { e -> WebSoundItem(
                e.select("a.hre b")[0].text(),
                e.select("div.entry-content p a")[0].absUrl("href")
        ) }
    }

    fun loadCategories(url : String, selector : String) : List<WebItem> {
        Log.d(javaClass.name, "before load $url")
        return Jsoup.connect(url).get()
                .select(selector)
                .map { e -> WebItem(e.text(), e.absUrl("href")) }
    }

}
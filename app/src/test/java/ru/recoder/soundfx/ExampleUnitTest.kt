package ru.recoder.soundfx

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val doc: Document = Jsoup.connect("https://noisefx.ru/").get()
        println(doc.title())
        val newsHeadlines: Elements = doc.select(".entry-content li a")
        for (headline in newsHeadlines) {
            println( String.format( "%s\n\t%s",
                headline.text(), headline.absUrl("href")
            ))
        }

        assertEquals(4, 2 + 2)
    }
}
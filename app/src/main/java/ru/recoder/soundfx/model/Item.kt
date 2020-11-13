package ru.recoder.soundfx.model

open class Item(protected val name : String) {
    override fun toString(): String {
        return name;
    }
}

open class WebItem(name : String, val url : String) : Item(name)

class WebSoundItem(name : String, url : String) : WebItem(name, url)

class FileItem(name : String, val path : String) : Item(name)
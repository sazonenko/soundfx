package ru.recoder.soundfx.model

open class Item(protected val name : String) {
    override fun toString(): String {
        return name;
    }
}

class WebItem(name : String, val url : String) : Item(name)

class WebSoundItem(name : String, val url : String) : Item(name)

class FileItem(name : String, val path : String) : Item(name)
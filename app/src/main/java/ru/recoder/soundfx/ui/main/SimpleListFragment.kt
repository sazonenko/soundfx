package ru.recoder.soundfx.ui.main

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.ListFragment


class SimpleListFragment : ListFragment() {

    private val catNames = ArrayList<String>().apply {
        addAll( listOf(
            "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
            "Кузя", "Китти", "Масяня", "Симба", "Рыся"
        ))
    }

    private var adapter : ArrayAdapter<String>? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_list_item_1, catNames
        )
        listAdapter = adapter
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        val textView : TextView = view as TextView
        Toast.makeText(activity, "Вы выбрали : $position) ${textView.text}", Toast.LENGTH_SHORT).show()
        catNames.removeAt(position)
        adapter?.notifyDataSetChanged()
    }
}
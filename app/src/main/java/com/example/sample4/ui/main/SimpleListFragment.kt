package com.example.sample4.ui.main

import android.R
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.ListFragment


public class SimpleListFragment : ListFragment() {

    // определяем массив типа String
    val catNames = arrayOf(
        "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька", "Томасина", "Кристина", "Пушок", "Дымка",
        "Кузя", "Китти", "Масяня", "Симба", "Рыся"
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter: ListAdapter = ArrayAdapter(
            activity!!,
            R.layout.simple_list_item_1, catNames
        )
        listAdapter = adapter
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        val textView : TextView = view as TextView
        Toast.makeText(activity, "Вы выбрали : $position) ${textView.text}", Toast.LENGTH_SHORT).show()
    }
}
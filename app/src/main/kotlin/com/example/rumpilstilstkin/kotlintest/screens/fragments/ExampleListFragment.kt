package com.example.rumpilstilstkin.kotlintest.screens.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rumpilstilstkin.kotlintest.R
import com.example.rumpilstilstkin.kotlintest.adapters.ExampleAdapter
import com.example.rumpilstilstkin.kotlintest.models.ExampleModel
import kotlinx.android.synthetic.main.fragment_list.listView


///////////////////////////////////////////////////////////////////////////
// Example List Fragment
///////////////////////////////////////////////////////////////////////////

class ExampleListFragment: Fragment(){

    ///////////////////////////////////////////////////////////////////////////
    // Lifecycle
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = ExampleAdapter(context)
        listView.adapter = adapter
        adapter.setItems(getData())
    }

    private fun getData() : ArrayList<ExampleModel> {
        val res = ArrayList<ExampleModel>()

        for(i in 0..10L){
            res.add(ExampleModel("name$i", i))
        }
        return res
    }

}

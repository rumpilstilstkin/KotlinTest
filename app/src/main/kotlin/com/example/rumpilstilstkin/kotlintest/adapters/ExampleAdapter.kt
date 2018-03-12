package com.example.rumpilstilstkin.kotlintest.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rumpilstilstkin.kotlintest.R
import com.example.rumpilstilstkin.kotlintest.models.ExampleModel


///////////////////////////////////////////////////////////////////////////
// Example Adapter
///////////////////////////////////////////////////////////////////////////

class ExampleAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    var data: ArrayList<ExampleModel> = ArrayList()

    fun setItems(items: ArrayList<ExampleModel>) {
        data = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ExampleViewHolder.create(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = data[position]
        (holder as ExampleViewHolder).bind(model)
    }
}

///////////////////////////////////////////////////////////////////////////
// Example View Holder
///////////////////////////////////////////////////////////////////////////

class ExampleViewHolder private constructor(root: View) : RecyclerView.ViewHolder(root) {
    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup) =
                ExampleViewHolder(inflater.inflate(R.layout.item_example, parent, false))
    }

    private var nameView: TextView? = null
    private var ageView: TextView? = null

    init{
        nameView = root.findViewById(R.id.ie_tv_name)
        ageView = root.findViewById(R.id.ie_tv_age)
    }

    fun bind(model: ExampleModel){
        nameView?.text = model.name
        ageView?.text = model.age.toString()
    }
}
package com.test.kotlin_test.content_provider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.kotlin_test.R

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/16
 * @Description :
 */
class ContactAdapter(val context:Context) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder> (){

    private val lists = ArrayList<Contacts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false))
    }

    override fun getItemCount(): Int {
       return  lists.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.name.text = lists[position].name
        holder.number.text=lists[position].number
    }

    fun setData(list:List<Contacts>){
        lists.clear()
        lists.addAll(list)
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(view:View):RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.name)
        val number:TextView=view.findViewById(R.id.number)
    }
}
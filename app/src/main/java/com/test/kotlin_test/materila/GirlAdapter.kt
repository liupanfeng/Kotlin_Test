package com.test.kotlin_test.materila

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.test.kotlin_test.R

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/13
 * @Description :
 */
class GirlAdapter(private val context:Context): RecyclerView.Adapter<GirlAdapter.GirlHolder>() {

    private   val mGirlList:MutableList<BeautyGirl> =ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.girl_item,parent,false)
        val holder=GirlHolder(view)
        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            val girl=mGirlList[position]
            val intent= Intent(context,GirlDetailActivity::class.java).apply {
                putExtra(GirlDetailActivity.GIRL_NAME,girl.name)
                putExtra(GirlDetailActivity.GIRL_IMAGE,girl.imageId)
            }
            context.startActivity(intent)
        }
        return holder
    }

    fun setData(girlList:MutableList<BeautyGirl>?){
        mGirlList.clear()
        girlList?.let { mGirlList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mGirlList.size
    }

    override fun onBindViewHolder(holder: GirlHolder, position: Int) {
        val girl= mGirlList[position]
        holder.girlName.text = girl.name
        Glide.with(context).load(girl.imageId).into(holder.girlImage)
//        holder.girlImage.load(girl.imageId)
    }

    class GirlHolder(view : View) : RecyclerView.ViewHolder(view){
        var girlImage:ImageView = view.findViewById(R.id.girlImage)
        var girlName:TextView = view.findViewById(R.id.girlName)
    }

}
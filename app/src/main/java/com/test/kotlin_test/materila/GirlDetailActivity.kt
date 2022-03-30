package com.test.kotlin_test.materila

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.test.kotlin_test.R
import kotlinx.android.synthetic.main.activity_gril_detail.*
import kotlinx.android.synthetic.main.activity_material.toolBar
import android.graphics.Color.parseColor
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.graphics.Color
import android.view.View
import com.test.kotlin_test.BaseActivity


class GirlDetailActivity : BaseActivity() {

    companion object{
        const val GIRL_NAME="girl_name"
        const val GIRL_IMAGE="girl_image"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gril_detail)

        val girlName=intent.getStringExtra(GIRL_NAME)?:""
        val girlImageId=intent.getIntExtra(GIRL_IMAGE,0)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolBar.title=girlName
        Glide.with(this).load(girlImageId).into(girlImage)
        content.text = initContent(girlName)

    }

    private fun initContent(girlName:String): String =girlName.repeat(500)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

package com.test.kotlin_test.materila

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.R
import com.test.kotlin_test.showToast
import kotlinx.android.synthetic.main.activity_material.*
import kotlin.concurrent.thread

class MaterialActivity : BaseActivity() {

    private lateinit var girls:MutableList<BeautyGirl>
    private lateinit var adapter:GirlAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolBar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.mipmap.ic_menu)
        }

        navView.setCheckedItem(R.id.navCall)  //默认选中某个item
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navCall -> showToast("Call")
                R.id.navLocation -> showToast("Location")
            }
            drawerLayout.closeDrawers()
            true
        }

        fab.setOnClickListener {
            Snackbar.make(it,"Data deleted",Snackbar.LENGTH_LONG).setAction("Done"){
                showToast("confirm delete data")
            }.show()
        }

        initRecyclerView()
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            refreshGirls()
        }
    }


    private fun initRecyclerView() {
        val layoutManger=GridLayoutManager(this,2)
        recyclerView.layoutManager=layoutManger
         adapter=GirlAdapter(this)
        recyclerView.adapter=adapter
        initGirls()
        adapter.setData(girls)
    }

    fun refreshGirls(){
        thread {
            Thread.sleep(2000)
            runOnUiThread{
                initGirls()
                adapter.setData(girls)
                swipeRefreshLayout.isRefreshing=false
            }
        }
    }

    private fun initGirls() {
        girls = mutableListOf(
            BeautyGirl("girl1", R.mipmap.g1),
            BeautyGirl("girl2", R.mipmap.g2),
            BeautyGirl("girl3", R.mipmap.g3),
            BeautyGirl("girl4", R.mipmap.g4),
            BeautyGirl("girl5", R.mipmap.g5),
            BeautyGirl("girl6", R.mipmap.g6),
            BeautyGirl("girl1", R.mipmap.g1),
            BeautyGirl("girl2", R.mipmap.g2),
            BeautyGirl("girl3", R.mipmap.g3),
            BeautyGirl("girl4", R.mipmap.g4),
            BeautyGirl("girl5", R.mipmap.g5),
            BeautyGirl("girl6", R.mipmap.g6),
            BeautyGirl("girl1", R.mipmap.g1),
            BeautyGirl("girl2", R.mipmap.g2),
            BeautyGirl("girl3", R.mipmap.g3),
            BeautyGirl("girl4", R.mipmap.g4),
            BeautyGirl("girl5", R.mipmap.g5),
            BeautyGirl("girl6", R.mipmap.g6),
            BeautyGirl("girl1", R.mipmap.g1),
            BeautyGirl("girl2", R.mipmap.g2),
            BeautyGirl("girl3", R.mipmap.g3),
            BeautyGirl("girl4", R.mipmap.g4),
            BeautyGirl("girl5", R.mipmap.g5),
            BeautyGirl("girl6", R.mipmap.g6)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            R.id.backup -> showToast("backup")
            R.id.delete -> showToast("delete")
            R.id.settings -> showToast("settings")
        }

        return true
    }
}

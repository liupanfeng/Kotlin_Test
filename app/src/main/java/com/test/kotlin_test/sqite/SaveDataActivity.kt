package com.test.kotlin_test.sqite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.test.kotlin_test.R
import kotlinx.android.synthetic.main.activity_save_data.*
import kotlinx.android.synthetic.main.item_contact.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * 系统原生数据库的使用总结
 */
class SaveDataActivity : AppCompatActivity() {
    val TAG:String="SaveDataActivity"

    private lateinit var mySqliteDataHelper:MySqliteDataHelper
    private lateinit var  writableDatabase:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)
        mySqliteDataHelper = MySqliteDataHelper(this, "Bookstore.db", 2)
    }


    fun saveDataToFile (view:View){
        val toString = etInput.text.toString()
        save(toString)
    }


    /**
     * 创建数据库
     */
    fun onCreateDataBase(view:View){
        writableDatabase = mySqliteDataHelper.writableDatabase
    }

    fun saveDataToSharedPreferences(view:View){
        saveToSharePreference(etInput.text.toString())
    }

    fun readDataFromSharedPrefences(view:View){
        readFromSharePreference()
    }


    fun readDataFromFile (view:View){
        val read = read()
        tvShowContent.text = read
    }

    /**
     * 数据库更新数据
     */
    fun updateData(view: View){
        writableDatabase = mySqliteDataHelper.writableDatabase
        val value=ContentValues()
        value.put("price",12.68)
        writableDatabase.update("Book",value,"name=?", arrayOf("斗破苍穹"))
    }

    /**
     * 数据库插入数据
     */
    fun insertData(view: View){
        val value=ContentValues().apply {
            put("name","斗破苍穹")
            put("author","tom")
            put("pages",454)
            put("price",16.68)
        }
        writableDatabase.insert("Book",null,value)
    }

    /**
     * 数据库查询数据
     */
    fun queryData(view: View){
        writableDatabase=mySqliteDataHelper.writableDatabase
        //参数介绍 表名 查询的列名字  指定where的约束条件 为where指定的展位符设置的值 指定需要group by   对group by的结果进行一步约束  指定查询结果的排序
        val cursor = writableDatabase.query(
            "Book", null, null,
            null, null, null, null
        )
        while (cursor.moveToNext()){
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val page = cursor.getInt(cursor.getColumnIndex("pages"))
        }
    }

    /**
     * 删除数据库的数据
     */
    fun deleteData(view: View){
        writableDatabase=mySqliteDataHelper.writableDatabase
        writableDatabase.delete("Book","price>?", arrayOf("12.68"))
    }

    /**
     * 文件保存
     */
    private fun save(inputText:String){
        val output=openFileOutput("data", Context.MODE_PRIVATE)
        val write=BufferedWriter(OutputStreamWriter(output))
        write.use {
            it.write(inputText)
        }
    }


    /**
     * 文件读取
     */
    fun read():String{
        val content=StringBuilder()
        val input=openFileInput("data")
        val reader=BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine {
                content.append(it)
            }
        }
        return content.toString()
    }


    fun saveToSharePreference(content:String){
        getSharedPreferences("data",Context.MODE_PRIVATE).edit().apply {
            putString("name","jack")
            putInt("age",16)
            putString("desc",content)
            apply()
        }

    }

    fun readFromSharePreference() {
        getSharedPreferences("data", Context.MODE_PRIVATE).apply {
            val name = getString("name", "")
            val age = getInt("age", 0)
            val desc = getString("desc", "")
            Log.d(TAG, "name=$name,age=$age,desc=$desc")
        }
    }


    /**
     * 使用事务
     */
    fun useTransaction(){
        writableDatabase=mySqliteDataHelper.writableDatabase
        writableDatabase.beginTransaction()
        writableDatabase.delete("Book",null,null)

        val values = ContentValues().apply {
            put("name","斗罗大陆")
            put("author","tom")
            put("pages",454)
            put("price",15.68)
        }

        writableDatabase.insert("Book",null,values)
        writableDatabase.setTransactionSuccessful()
        writableDatabase.endTransaction()
    }


}

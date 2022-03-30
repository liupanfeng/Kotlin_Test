package com.test.kotlin_test.sqite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.test.kotlin_test.showToast

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/17
 * @Description :
 * 第三个参数：允许我们在查询数据库的时候返回一个自定义的Cursor 传入null就行
 */
class MySqliteDataHelper(val context: Context,name:String,version:Int):SQLiteOpenHelper(context,name,null,version) {

    private val createBook="create table Book( " +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"


    private val createCategory="create table Category( id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        showToast("create books success")
    }

    /**
     * 数据库的更新
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //根据版本号判断
        if (oldVersion<=1){
            db?.execSQL(createCategory)
        }

        if (oldVersion<=2){
            db?.execSQL("alter table Book add column category_id")
        }

        //...

    }
}
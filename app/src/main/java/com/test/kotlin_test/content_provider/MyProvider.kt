package com.test.kotlin_test.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/17
 * @Description :
 */
class MyProvider : ContentProvider() {


    //插入数据
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //从ContentProvider中查询数据
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 初始化ContentProvider的时候调用，通常在这里完成都数据库的创建和升级操作
     */
    override fun onCreate(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //更新数据
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //删除数据
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //根据传入的Uri返回相应的的MIME类型
    override fun getType(uri: Uri): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
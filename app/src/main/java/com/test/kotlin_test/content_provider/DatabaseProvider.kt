package com.test.kotlin_test.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.test.kotlin_test.sqite.MySqliteDataHelper

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/17
 * @Description :
 */
class DatabaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val authority = "com.test.kotlin_test.provider"
    private var dbHelper : MySqliteDataHelper ?= null

    private val uriMatcher by lazy {
         val mathcher=UriMatcher(UriMatcher.NO_MATCH)
        mathcher.addURI(authority,"book",bookDir)
        mathcher.addURI(authority,"book/#",bookItem)
        mathcher
    }



    override fun insert(uri: Uri, values: ContentValues?)=dbHelper?.let {

        val db =it.writableDatabase
        val uriReturn=when(uriMatcher.match(uri)){
            bookDir ->{
                val newBookId=db.insert("Book",null,values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            else ->null
        }
        uriReturn
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ) = dbHelper?.let {
        val db=it.readableDatabase
        val cursor = when(uriMatcher.match(uri)){
            bookDir ->db.query("Book",projection,selection,selectionArgs,null,null,sortOrder)
            bookItem -> {
                val bookId=uri.pathSegments[1]
                db.query("Book",projection,"id=?", arrayOf(bookId),null,null,sortOrder)
            }
            else->null
        }
        cursor
    }

    override fun onCreate() = context?.let {
        dbHelper=MySqliteDataHelper(it,"BookStore.db",2)
        true
    } ?:false

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?)= dbHelper?.let {
        val db=it.writableDatabase
        val updateRows=when(uriMatcher.match(uri)){
            bookDir -> db.update("Book",values,selection,selectionArgs)
            bookItem ->{
                val bookId=uri.pathSegments[1]
                db.update("Book",values,"id=?", arrayOf(bookId))
            }
            else -> 0
        }
        updateRows
    } ?: 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?)=dbHelper?.let {
        val db=it.writableDatabase
        val deleteRows=when(uriMatcher.match(uri)){
            bookDir -> db.delete("Book",selection,selectionArgs)
            bookItem -> {
                val bookId=uri.pathSegments[1]
                db.delete("Book","id=?", arrayOf(bookId))
            }
            else->0
        }
        deleteRows
    }?: 0

    override fun getType(uri: Uri)=when(uriMatcher.match(uri)){
        bookDir -> "vnd.android.cursor.dir/vnd.com.test.kotlin_test.provider.book"
        bookItem -> "vnd.android.cursor.item/vnd.com.test.kotlin_test.provider.book"
        else -> null
    }

}
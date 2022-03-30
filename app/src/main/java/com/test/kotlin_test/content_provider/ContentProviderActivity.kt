package com.test.kotlin_test.content_provider

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.R
import com.test.kotlin_test.showToast
import kotlinx.android.synthetic.main.activity_content_provider.*

class ContentProviderActivity : BaseActivity() {

    val TAG:String="ContentProviderActivity"

    val uri:String=""
    val mDatas=ArrayList<Contacts>()
   private lateinit var contactAdapter:ContactAdapter

    var bookId:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        initRecyclerView()

//        //查询数据
//        val cursor=contentResolver.query(Uri.parse("dd"),{"test"},"",[""],"")
//
//        //循环遍历数据
//        while (cursor?.moveToNext()!!){
//            val column1 = cursor.getString(cursor.getColumnIndex("column1"))
//            val column2 = cursor.getString(cursor.getColumnIndex("column2"))
//        }
//        cursor.close()
//
//        //插入数据
//        val values= contentValuesOf("columu1" to "content","column2" to 1)
//        contentResolver.insert(Uri.parse(uri),values)
//
//        //更新数据  使用selection selectionArgs 参数对想要更新的数据进行约束，防止所有的数据都会收到影响
//        val  valuesUpdate= contentValuesOf("column1" to "")
//        contentResolver.update(Uri.parse(uri),values,"column1 = ? and column2 = ?", arrayOf("text","1"))
//
//        //删除数据
//        contentResolver.delete(Uri.parse(uri),"column2 = ?", arrayOf("1"))

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
        }else{
            readContacts()
        }

    }


    fun insertData(view: View){
        val uri=Uri.parse("content://com.test.kotlin_test.provider/book")
        val contentValuesOf =
            contentValuesOf("name" to "Kings of the world", "author" to "kings", "pages" to 1001, "price" to 22.68)
        val newUri = contentResolver.insert(uri, contentValuesOf)
       bookId = newUri?.pathSegments?.get(1)
    }

    fun queryData(view: View){
        val uri=Uri.parse("content://com.test.kotlin_test.provider/book")
        contentResolver.query(uri,null,null,null,null)?.apply {
            while (moveToNext()){
                val name=getString(getColumnIndex("name"))
                val author=getString(getColumnIndex("author"))
                val pages=getInt(getColumnIndex("pages"))
                val price=getDouble(getColumnIndex("price"))
                Log.d(TAG,"anme=$name,author=$author,price=$price,pages=$pages")
            }
        }
    }

    fun updateData(view: View){
        val uri=Uri.parse("content://com.test.kotlin_test.provider/book")
        val contentValue=contentValuesOf("price" to 14)
        contentResolver.update(uri,contentValue,"name=?", arrayOf("Kings of the world"))
    }

    fun deleteData(view: View){
        val uri=Uri.parse("content://com.test.kotlin_test.provider/book")
        contentResolver.delete(uri,"name=?", arrayOf("Kings of the world"))
    }






    /**
     * 读取联系人
     */
    private fun readContacts() {
        mDatas.clear()
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
            null,null,null)?.apply {
             while (moveToNext()){
                 val displayName=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                 val number=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                 val contacts=Contacts(name = displayName,number =number )
                 mDatas.add(contacts)
             }
        }
        contactAdapter.setData(mDatas)

    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager=linearLayoutManager
        contactAdapter = ContactAdapter(this)
        recyclerView.adapter=contactAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts()
                }else{
                    showToast("you denied the permission")
                }
            }
        }
    }

}

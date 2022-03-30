package com.test.kotlin_test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.R
import com.test.kotlin_test.showToast
import kotlinx.android.synthetic.main.fragment_left.*

class FragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val widthPixels = resources.displayMetrics.widthPixels;
        showToast(widthPixels.toString())
//        leftButton.setOnClickListener {
//            replaceFragment(AnotherFragment())
//        }
//        replaceFragment(RightFragment())
//
//        val leftFragment = supportFragmentManager.findFragmentById(R.id.leftFragment) as LeftFragment
//        leftFragment.test()


    }

    private fun replaceFragment(rightFragment: Fragment) {
        val bundle=Bundle()
        bundle.putString(LeftFragment.KEY_PARAM,"test")
        rightFragment.arguments=bundle
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.rightFragment, rightFragment)
            addToBackStack(null)
            commit()
        }
    }

    fun play(){

    }
}

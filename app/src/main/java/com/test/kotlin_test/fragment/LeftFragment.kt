package com.test.kotlin_test.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.test.kotlin_test.R


class LeftFragment : Fragment() {

    companion object{
        val KEY_PARAM:String="key_param"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val param = arguments?.getString(KEY_PARAM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left, container, false)
    }

    fun test(){
        val fragmentActivity = activity as FragmentActivity
        fragmentActivity.play()
    }


    override fun getUserVisibleHint(): Boolean {
        return super.getUserVisibleHint()
    }

}

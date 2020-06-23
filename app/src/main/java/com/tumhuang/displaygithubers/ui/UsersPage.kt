package com.tumhuang.displaygithubers.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tumhuang.displaygithubers.R
import com.tumhuang.displaygithubers.model.RemoteApi
import com.tumhuang.displaygithubers.viewmodel.UsersViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UsersPage : Fragment() {

    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_page,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {

        viewModel.getUsers().observe(viewLifecycleOwner, Observer {

        })

    }
}
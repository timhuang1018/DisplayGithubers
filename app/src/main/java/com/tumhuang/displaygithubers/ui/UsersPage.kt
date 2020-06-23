package com.tumhuang.displaygithubers.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tumhuang.displaygithubers.R
import com.tumhuang.displaygithubers.adapter.AdapterClick
import com.tumhuang.displaygithubers.adapter.AdapterListener
import com.tumhuang.displaygithubers.adapter.UsersAdapter
import com.tumhuang.displaygithubers.data.User
import com.tumhuang.displaygithubers.model.RemoteApi
import com.tumhuang.displaygithubers.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.first_page.*
import kotlinx.coroutines.launch

class UsersPage : Fragment(),AdapterListener {

    private lateinit var viewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter

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
        usersAdapter = UsersAdapter(this@UsersPage)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        vertical_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        viewModel.getUsers(init = true).apply {
            user.observe(viewLifecycleOwner, Observer {list->
                Log.d("getUsers","list:${list.size}")
                usersAdapter.submitList(list)
            })
            isLoading.observe(viewLifecycleOwner, Observer { isLoading->
                if (isLoading){
                    list_progressbar.visibility = View.VISIBLE
                }else{
                    list_progressbar.visibility = View.GONE
                }
            })
            error.observe(viewLifecycleOwner, Observer {
                it.contentGetHandled()?.let {errorMessage->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }

        vertical_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //give positive number to check scrolling down, if not ,fetch data
                if (!recyclerView.canScrollVertically(1)){
                    viewModel.getUsers()
                }
            }
        })

    }

    //simply respond click and navigate at here
    override fun listenClick(item: AdapterClick) {
        Log.d("listenClick","item:$item")
        if (item is User){
        }
    }
}
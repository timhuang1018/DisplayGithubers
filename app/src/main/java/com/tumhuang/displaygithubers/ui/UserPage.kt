package com.tumhuang.displaygithubers.ui

import android.content.Intent
import android.net.ParseException
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tumhuang.displaygithubers.R
import com.tumhuang.displaygithubers.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.first_page.list_progressbar
import kotlinx.android.synthetic.main.second_page.*

class UserPage :Fragment() {

    private lateinit var viewModel: UserViewModel
    val args :UserPageArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_page,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        setupListener()
    }

    private fun initUI() {

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        viewModel.getUser(args.login).apply {
            data.observe(viewLifecycleOwner, Observer { user->
                with(iv_headshot){
                    Glide.with(this)
                        .load(user.headPic)
                        .thumbnail(0.05f)
                        .error(R.drawable.ic_baseline_person_24)
                        .into(this)
                }

                tv_name.text = user.name
                tv_bio.text = user.bio
                tv_login.text = user.login
                tv_location.text = user.location

                if (user.isAdmin){
                    admin_badge.visibility = View.VISIBLE
                }

                tv_blog.text = Html.fromHtml("<a href=\"${user.blog}\">${user.blog}</a>")
                if (user.blog.isNotEmpty()){
                    tv_blog.setOnClickListener {
                        openLink(user.blog)
                    }
                }

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

    }


    private fun setupListener() {
        iv_close.setOnClickListener {
            findNavController().navigateUp()
        }


    }

    private fun openLink(link:String){
        try {
            val uri = Uri.parse(link)
            val intent = Intent(Intent.ACTION_VIEW,uri)

            val activities = activity?.packageManager?.queryIntentActivities(intent,0) ?: listOf()
            if (activities.isNotEmpty()){
                activity?.run {
                    startActivity(intent)
                }
            }else{
                Toast.makeText(requireContext(), "no browser to open", Toast.LENGTH_SHORT).show()
            }
        }catch (e:ParseException){
            Toast.makeText(requireContext(), "can't open link", Toast.LENGTH_SHORT).show()
        }

    }

}
package com.example.martiandemo.ui.posts.create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_create_post.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentCreatePost : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val createPostViewModelFactory: CreatePostViewModelFactory by instance()

    private lateinit var viewModel: FragmentCreatePostViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, createPostViewModelFactory).get(FragmentCreatePostViewModel::class.java)

        initUi()
    }

    fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.create_post)
        fabCreatePost.setOnClickListener {
            groupCreatingPost.visibility = View.VISIBLE
            viewModel.createPost(etCreatePostTitle.text.toString(), etCreatePostBody.text.toString())
            groupCreatingPost.visibility = View.GONE
            //Not ideal, but due to time constraints...
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}

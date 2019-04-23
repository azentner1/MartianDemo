package com.example.martiandemo.ui.posts.comments.create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_create_comment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentCreateComment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val createCommentViewModelFactory: CreateCommentViewModelFactory by instance()

    private lateinit var createCommentViewModel: FragmentCreateCommentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_comment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createCommentViewModel = ViewModelProviders.of(this, createCommentViewModelFactory).get(FragmentCreateCommentViewModel::class.java)

        arguments?.let {
            createCommentViewModel.setPostId(FragmentCreateCommentArgs.fromBundle(it).postId)
        }

        initUi()
    }

    private fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.create_comment)
        fabCreateComment.setOnClickListener {
            groupCreatingComment.visibility = View.VISIBLE
            createCommentViewModel.createComment(etCreateCommentName.text.toString(), etCreateCommentEmail.text.toString(), etCreateCommentBody.text.toString())
            groupCreatingComment.visibility = View.GONE
            //Not ideal, but due to time constraints...
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}

package com.example.martiandemo.ui.posts.comments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import com.example.martiandemo.ui.recycler_view_ui.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentComments : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val commentsViewHolderFactory: CommentsViewHolderFactory by instance()

    private lateinit var commentsViewModel: FragmentCommentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        commentsViewModel = ViewModelProviders.of(this, commentsViewHolderFactory).get(FragmentCommentsViewModel::class.java)

        arguments?.let {
            commentsViewModel.setPostId(FragmentCommentsArgs.fromBundle(it).postId)
        }

        initUi()
        initData()
        initRecyclerView()
    }

    private fun initUi() {
        fabAddComment.setOnClickListener {
            openCreatePostFragment()
        }
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.comments)
    }

    private fun initData() = launch {
        val comments = commentsViewModel.comments.await()
        comments.observe(this@FragmentComments, Observer {
            commentsViewModel.getAdapter().initComments(it)
        })
    }

    private fun initRecyclerView() {
        rvComments.layoutManager = LinearLayoutManager(requireContext())
        rvComments.setHasFixedSize(true)
        rvComments.addItemDecoration(
            DividerItemDecoration(
                ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null),
                resources.getDimensionPixelOffset(R.dimen.item_padding_left),
                resources.getDimensionPixelOffset(R.dimen.item_padding_right))
        )
        rvComments.adapter = commentsViewModel.getAdapter()
    }

    fun openCreatePostFragment() {
        val action = FragmentCommentsDirections.actionFragmentCommentsToFragmentCreateComment()
        action.postId = commentsViewModel.getPostId()
        NavHostFragment.findNavController(this@FragmentComments).navigate(action)
    }

}

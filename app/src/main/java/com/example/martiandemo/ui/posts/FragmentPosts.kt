package com.example.martiandemo.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import com.example.martiandemo.ui.recycler_view_ui.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_posts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class FragmentPosts : ScopedFragment(), KodeinAware, OnPostClickListener {

    override val kodein by closestKodein()

    private val postsViewModelFactory: PostsViewModelFactory by instance()

    private lateinit var postsViewModel: FragmentPostsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postsViewModel = ViewModelProviders.of(this@FragmentPosts, postsViewModelFactory).get(FragmentPostsViewModel::class.java)
        initUi()
        initData()
        initRecyclerView()
    }

    private fun initData() = launch(Dispatchers.Main) {
        val posts = postsViewModel.posts.await()
        posts.observe(this@FragmentPosts, Observer {
            if (it == null) {
                return@Observer
            }
            groupLoadingPosts.visibility = View.GONE
            postsViewModel.getPostsAdapter().initPosts(it)
            postsViewModel.getPostsAdapter().setOnCommentClickListener(this@FragmentPosts)
        })
    }

    private fun initRecyclerView() {
        rvPosts.layoutManager = LinearLayoutManager(requireContext())
        rvPosts.setHasFixedSize(true)
        rvPosts.addItemDecoration(
            DividerItemDecoration(ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null),
                resources.getDimensionPixelOffset(R.dimen.item_padding_left),
                resources.getDimensionPixelOffset(R.dimen.item_padding_right))
        )
        rvPosts.adapter = postsViewModel.getPostsAdapter()
    }

    override fun onCommentClick(postId: Int) {
        val action = FragmentPostsDirections.actionFragmentPostsToFragmentComments()
        action.postId = postId
        NavHostFragment.findNavController(this@FragmentPosts).navigate(action)
    }

    fun initUi() {
        fabAddPost.setOnClickListener {
            openCreatePostFragment()
        }
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.posts)
    }

    fun openCreatePostFragment() {
        val action = FragmentPostsDirections.actionFragmentPostsToFragmentCreatePost()
        NavHostFragment.findNavController(this@FragmentPosts).navigate(action)
    }
}
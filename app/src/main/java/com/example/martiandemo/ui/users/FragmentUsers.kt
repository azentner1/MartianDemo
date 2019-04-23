package com.example.martiandemo.ui.users

import android.opengl.Visibility
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
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class FragmentUsers : ScopedFragment(), KodeinAware, OnUserClickListener {

    override val kodein by closestKodein()

    private val usersViewModelFactory: UsersViewModelFactory by instance()
    private lateinit var userViewModel: FragmentUsersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel = ViewModelProviders.of(this, usersViewModelFactory).get(FragmentUsersViewModel::class.java)
        initUi()
        initData()
        initRecyclerView()

    }

    private fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.users)

    }

    private fun initData() = launch(Dispatchers.Main) {
        val users = userViewModel.users.await()
        users.observe(this@FragmentUsers, Observer {
            if (it == null) {
                return@Observer
            }
            groupLoadingUsers.visibility = View.GONE
            userViewModel.getUsersAdapter().initUsers(it)
            userViewModel.getUsersAdapter().setOnUserClickListener(this@FragmentUsers)
        })
    }

    private fun initRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(requireContext())
        rvUsers.setHasFixedSize(true)
        rvUsers.addItemDecoration(
            DividerItemDecoration(
                ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null),
                resources.getDimensionPixelOffset(R.dimen.item_padding_left),
                resources.getDimensionPixelOffset(R.dimen.item_padding_right))
        )
        rvUsers.adapter = userViewModel.getUsersAdapter()
    }

    override fun onUserClick(userId: Int) {
        val action = FragmentUsersDirections.actionFragmentUsersToFragmentTodo()
        action.userId = userId
        NavHostFragment.findNavController(this@FragmentUsers).navigate(action)
    }

}
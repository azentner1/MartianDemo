package com.example.martiandemo.ui.users.todos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import com.example.martiandemo.ui.recycler_view_ui.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentTodo : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var todoViewModel: FragmentTodoViewModel

    private val todoViewModelFactory: TodoViewModelFactory by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        todoViewModel = ViewModelProviders.of(this, todoViewModelFactory).get(FragmentTodoViewModel::class.java)

        arguments?.let {
            todoViewModel.setUserId(FragmentTodoArgs.fromBundle(it).userId)
        }
        initUi()
        initData()
        initRecyclerView()
    }

    fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.todo)
    }

    private fun initData() = launch(Dispatchers.Main) {
        val todos = todoViewModel.todos.await()
        todos.observe(this@FragmentTodo, Observer {
            if (it == null) {
                return@Observer
            }
            groupLoadingTodos.visibility = View.GONE
            todoViewModel.getAdapter().initTodos(it)
        })
    }

    private fun initRecyclerView() {
        rvTodo.layoutManager = LinearLayoutManager(requireContext())
        rvTodo.setHasFixedSize(true)
        rvTodo.addItemDecoration(
            DividerItemDecoration(
                ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null),
                resources.getDimensionPixelOffset(R.dimen.item_padding_left),
                resources.getDimensionPixelOffset(R.dimen.item_padding_right))
        )
        rvTodo.adapter = todoViewModel.getAdapter()
    }

}

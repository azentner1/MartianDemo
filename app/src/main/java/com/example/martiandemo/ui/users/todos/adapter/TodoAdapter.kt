package com.example.martiandemo.ui.users.todos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.martiandemo.R
import com.example.martiandemo.data.db.models.Todo
import kotlinx.android.synthetic.main.item_todo.view.*


class TodoAdapter(var todos: List<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    fun initTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(todo: Todo) {
            itemView.txtTodoTitle.text = todo.title
            if (todo.completed) {
                itemView.ivTodoCheckmark.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_done_green))
            } else {
                itemView.ivTodoCheckmark.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_not_done))
            }
        }


    }
}
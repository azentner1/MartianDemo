package com.example.martiandemo.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martiandemo.R
import com.example.martiandemo.data.db.models.User
import com.example.martiandemo.ui.users.OnUserClickListener
import kotlinx.android.synthetic.main.item_users.view.*


class UsersAdapter(var users: List<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private lateinit var onUserClickListener: OnUserClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener {
            onUserClickListener.onUserClick(users[position].id)
        }
    }


    fun setOnUserClickListener(onUserClickListener: OnUserClickListener) {
        this.onUserClickListener = onUserClickListener
    }


    fun initUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(user: User) {
            itemView.txtUserName.text = user.name
            itemView.txtUserUserName.text = user.username
            itemView.txtUserEmail.text = user.email
            itemView.txtUserWebsite.text = user.website
        }

    }

}
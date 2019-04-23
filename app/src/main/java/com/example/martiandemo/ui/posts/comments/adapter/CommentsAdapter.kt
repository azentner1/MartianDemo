package com.example.martiandemo.ui.posts.comments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martiandemo.R
import com.example.martiandemo.data.db.models.Comment
import kotlinx.android.synthetic.main.item_comment.view.*


class CommentsAdapter(var comments: List<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun initComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(comment: Comment) {
            itemView.txtCommentName.text = comment.name
            itemView.txtCommentBody.text = comment.body
            itemView.txtCommentEmail.text = comment.email
        }
    }
}
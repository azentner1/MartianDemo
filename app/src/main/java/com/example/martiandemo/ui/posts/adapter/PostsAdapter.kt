package com.example.martiandemo.ui.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martiandemo.R
import com.example.martiandemo.data.db.models.Post
import com.example.martiandemo.ui.posts.OnPostClickListener
import kotlinx.android.synthetic.main.item_posts.view.*


class PostsAdapter(var posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private lateinit var onPostClickListener: OnPostClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
        holder.itemView.setOnClickListener {
            onPostClickListener.onCommentClick(post.id)
        }
    }

    fun initPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    fun setOnCommentClickListener(onPostClickListener: OnPostClickListener) {
        this.onPostClickListener = onPostClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) {
            itemView.txtPostsTitle.text = post.title
            itemView.txtPostsBody.text = post.body
        }
    }
}
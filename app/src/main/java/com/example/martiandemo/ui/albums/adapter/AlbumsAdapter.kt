package com.example.martiandemo.ui.albums.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martiandemo.R
import com.example.martiandemo.data.db.models.Album
import com.example.martiandemo.ui.albums.OnAlbumClickListener
import kotlinx.android.synthetic.main.item_albums.view.*


class AlbumsAdapter(var albums: List<Album>) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private lateinit var onAlbumClickListener: OnAlbumClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_albums, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
        holder.itemView.setOnClickListener {
            onAlbumClickListener.onAlbumClick(albums[position].id)
        }
    }

    fun initAlbums(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    fun setOnAlbumClickListener(onAlbumClickListenerListener: OnAlbumClickListener) {
        onAlbumClickListener = onAlbumClickListenerListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(album: Album) {
            itemView.txtAlbumName.text = album.title
        }

    }
}
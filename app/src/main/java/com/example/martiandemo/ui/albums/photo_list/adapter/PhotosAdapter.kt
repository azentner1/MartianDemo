package com.example.martiandemo.ui.albums.photo_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.martiandemo.R
import com.example.martiandemo.data.db.models.Photo
import com.example.martiandemo.ui.albums.photo_list.OnPhotoClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo_list.view.*


class PhotosAdapter(var photos: List<Photo>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private lateinit var onPhotoClickListener: OnPhotoClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
        holder.itemView.setOnClickListener {
            onPhotoClickListener.onPhotoClick(photos[position].url)
        }
    }

    fun initPhotos(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    fun setOnPhotoClickListener(onPhotoClickListener: OnPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(photo: Photo) {
            itemView.txtPhotoTitle.text = photo.title
            Picasso.get()
                .load(photo.thumbnailUrl.replace("http", "https"))
                .into(itemView.ivAlbumImage)

        }


    }
}
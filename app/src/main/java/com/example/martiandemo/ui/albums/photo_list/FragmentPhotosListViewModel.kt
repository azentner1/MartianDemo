package com.example.martiandemo.ui.albums.photo_list

import androidx.lifecycle.ViewModel
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import com.example.martiandemo.ui.albums.photo_list.adapter.PhotosAdapter

class FragmentPhotosListViewModel(val appRepository: AppRepository) : ViewModel() {

    private var photoAdapter: PhotosAdapter = PhotosAdapter(listOf())

    private var albumId: Int = 0

    val photos by lazyDeferred {
        appRepository.getPhotos(albumId)
    }

    fun getAdapter(): PhotosAdapter {
        return photoAdapter
    }

    fun setAlbumId(albumId: Int) {
        this.albumId = albumId
    }
}

package com.example.martiandemo.ui.albums

import androidx.lifecycle.ViewModel
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.ui.helpers.lazyDeferred
import com.example.martiandemo.ui.albums.adapter.AlbumsAdapter


class FragmentAlbumsViewModel(val appRepository: AppRepository) : ViewModel() {

    private var albumsAdapter: AlbumsAdapter = AlbumsAdapter(listOf())

    val albums by lazyDeferred {
        appRepository.getAlbums()
    }

    fun getAdapter() : AlbumsAdapter {
        return albumsAdapter
    }
}
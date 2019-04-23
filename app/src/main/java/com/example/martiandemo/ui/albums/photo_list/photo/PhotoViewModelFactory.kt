package com.example.martiandemo.ui.albums.photo_list.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martiandemo.data.repository.AppRepository


class PhotoViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentPhotoViewModel(appRepository) as T
    }
}
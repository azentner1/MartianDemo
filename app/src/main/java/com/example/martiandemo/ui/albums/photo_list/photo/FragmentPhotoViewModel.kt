package com.example.martiandemo.ui.albums.photo_list.photo

import androidx.lifecycle.ViewModel;
import com.example.martiandemo.data.repository.AppRepository

class FragmentPhotoViewModel(val appRepository: AppRepository) : ViewModel() {

    private lateinit var photoUrl: String

    fun setPhotoUrl(photoUrl: String) {
        this.photoUrl = photoUrl
    }

    fun getPhotoUrl() : String {
        return photoUrl
    }

}

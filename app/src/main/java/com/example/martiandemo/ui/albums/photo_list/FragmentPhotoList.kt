package com.example.martiandemo.ui.albums.photo_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager

import com.example.martiandemo.R
import com.example.martiandemo.ui.albums.FragmentAlbumsDirections
import com.example.martiandemo.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_photo_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentPhotoList : ScopedFragment(), KodeinAware, OnPhotoClickListener {

    override val kodein by closestKodein()

    private val photoViewModelFactory: PhotoListViewModelFactory by instance()

    private lateinit var photosListViewModel: FragmentPhotosListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photosListViewModel = ViewModelProviders.of(this, photoViewModelFactory).get(FragmentPhotosListViewModel::class.java)

        arguments?.let {
            photosListViewModel.setAlbumId(FragmentPhotoListArgs.fromBundle(it).albumId)
        }

        initUi()
        initData()
        initRecyclerView()
    }

    private fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.album_photos)
    }

    private fun initData() = launch(Dispatchers.Main) {
        val photos = photosListViewModel.photos.await()
        photos.observe(this@FragmentPhotoList, Observer {
            if (it == null) {
                return@Observer
            }
            groupLoadingPhotos.visibility = View.GONE
            photosListViewModel.getAdapter().initPhotos(it)
            photosListViewModel.getAdapter().setOnPhotoClickListener(this@FragmentPhotoList)
        })
    }

    private fun initRecyclerView() {
        rvPhotos.layoutManager = GridLayoutManager(requireContext(), 3)
        rvPhotos.setHasFixedSize(true)
        rvPhotos.adapter = photosListViewModel.getAdapter()
    }

    override fun onPhotoClick(photoUrl: String) {
        val action = FragmentPhotoListDirections.actionFragmentPhotosToFragmentPhoto()
        action.photoUrl = photoUrl
        NavHostFragment.findNavController(this@FragmentPhotoList).navigate(action)
    }

}

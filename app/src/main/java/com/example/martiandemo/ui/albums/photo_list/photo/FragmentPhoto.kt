package com.example.martiandemo.ui.albums.photo_list.photo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.martiandemo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.item_photo_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FragmentPhoto : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var photoViewModel: FragmentPhotoViewModel

    private val photoViewModelFactory: PhotoViewModelFactory by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photoViewModel = ViewModelProviders.of(this, photoViewModelFactory).get(FragmentPhotoViewModel::class.java)

        arguments?.let {
            photoViewModel.setPhotoUrl(FragmentPhotoArgs.fromBundle(it).photoUrl)
        }
        initUi()
        initData()
    }

    private fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.photo)
    }

    private fun initData() {
        Picasso.get().load(photoViewModel.getPhotoUrl().replace("http", "https")).into(ivPhoto)
    }

}

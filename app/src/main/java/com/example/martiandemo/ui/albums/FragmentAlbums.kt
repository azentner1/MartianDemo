package com.example.martiandemo.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.martiandemo.R
import com.example.martiandemo.ui.base.ScopedFragment
import com.example.martiandemo.ui.recycler_view_ui.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_albums.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class FragmentAlbums : ScopedFragment(), KodeinAware, OnAlbumClickListener {

    override val kodein by closestKodein()

    private val albumsViewModelFactory: AlbumsViewModelFactory by instance()
    private lateinit var albumsViewModel: FragmentAlbumsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        albumsViewModel = ViewModelProviders.of(this, albumsViewModelFactory).get(FragmentAlbumsViewModel::class.java)
        initUi()
        initAlbumData()
        initRecyclerView()
    }

    private fun initUi() {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.albums)
    }

    private fun initAlbumData() = launch(Dispatchers.Main) {
        val albums = albumsViewModel.albums.await()
        albums.observe(this@FragmentAlbums, Observer {
            if (it == null) {
                return@Observer
            }
            groupLoadingAlbums.visibility = View.GONE
            albumsViewModel.getAdapter().initAlbums(it)
            albumsViewModel.getAdapter().setOnAlbumClickListener(this@FragmentAlbums)
        })
    }

    private fun initRecyclerView() {
        rvAlbums.layoutManager = LinearLayoutManager(requireContext())
        rvAlbums.setHasFixedSize(true)
        rvAlbums.addItemDecoration(
            DividerItemDecoration(
                ResourcesCompat.getDrawable(resources, R.drawable.recycler_view_divider, null),
                resources.getDimensionPixelOffset(R.dimen.item_padding_left),
                resources.getDimensionPixelOffset(R.dimen.item_padding_right))
        )
        rvAlbums.adapter = albumsViewModel.getAdapter()
    }

    override fun onAlbumClick(albumId: Int) {
        val action = FragmentAlbumsDirections.actionFragmentAlbumsToFragmentPhotos()
        action.albumId = albumId
        NavHostFragment.findNavController(this@FragmentAlbums).navigate(action)
    }

}
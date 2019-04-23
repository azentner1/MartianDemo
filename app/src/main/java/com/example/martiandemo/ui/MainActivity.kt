package com.example.martiandemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.martiandemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tbMain)
        navController = Navigation.findNavController(this, R.id.root_nav_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        initBottomNav()
    }

    private fun initBottomNav() {
        bnvMain?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}

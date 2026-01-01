package com.example.paperxr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.paperxr.databinding.ActivityMainBinding
import com.example.paperxr.permission.Permissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var permissions: Permissions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        permissions= Permissions(this)
        permissions.checkAndRequestMultiPerm()


        val navHost=supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        val controller= navHost.navController

        binding.bottomNavABar.setOnItemSelectedListener {

            when(it.itemId){
                R.id.libraryIcon->{
                    controller.navigate(R.id.pdfListsFragment)
                    true

                }
                R.id.favouriteIcon->{
                    controller.navigate(R.id.favoritesFragment)
                    true
                }
                R.id.xrIcon->{

                    controller.navigate(R.id.arFragment)
                    true
                }

                else->false


            }
        }








    }
}
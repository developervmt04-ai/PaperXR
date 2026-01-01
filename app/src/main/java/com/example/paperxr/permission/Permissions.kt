package com.example.paperxr.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Permissions(private val activity: AppCompatActivity) {

    private val requiredPermissions = buildList {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            add(Manifest.permission.POST_NOTIFICATIONS)
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }



    private val multiPermissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            result ->
            result.forEach { (perm,granted)->
                if (!granted){


                }
            }

        }
    private val singlePermissionLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {


        }
    fun checkAndRequestSinglePerm(perm:String){

        if (ContextCompat.checkSelfPermission((activity),perm)!= PackageManager.PERMISSION_GRANTED){

            singlePermissionLauncher.launch(perm)
        }


    }



    fun checkAndRequestMultiPerm() {

        val notGranted = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED


        }.toMutableList()
        if (notGranted.isNotEmpty()){
            multiPermissionLauncher.launch(notGranted.toTypedArray())





        }

    }


}
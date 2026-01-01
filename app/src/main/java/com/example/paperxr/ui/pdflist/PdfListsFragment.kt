package com.example.paperxr.ui.pdflist

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paperxr.databinding.FragmentPdfListsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.content.pm.PackageManager

@AndroidEntryPoint
class PdfListsFragment : Fragment() {

    private lateinit var binding: FragmentPdfListsBinding
    private val viewModel: PdfViewModel by viewModels()
    private lateinit var adapter: PdfListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPdfListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PdfListAdapter()
        binding.pdfListRV.layoutManager = LinearLayoutManager(requireContext())
        binding.pdfListRV.adapter = adapter
        viewModel.loadAllPdfs()


        lifecycleScope.launchWhenStarted {
            viewModel.pdfs.collect {
                adapter.differ.submitList(it)
            }
        }

//        binding.loadAllBtn.setOnClickListener {
//            if (hasStoragePermission()) {
//                viewModel.loadAllPdfs()
//            } else {
//                requestStoragePermission()
//            }
//        }
    }

    private fun hasStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            android.os.Environment.isExternalStorageManager()
        } else {
            val read = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            read == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:${requireContext().packageName}")
            startActivity(intent)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        }
    }
}

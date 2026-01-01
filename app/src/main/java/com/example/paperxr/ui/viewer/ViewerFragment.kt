package com.example.paperxr.ui.viewer

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paperxr.R
import com.example.paperxr.databinding.FragmentViewerBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ViewerFragment : Fragment() {
    private lateinit var binding: FragmentViewerBinding
    private lateinit var viewAdapter: PdfViewAdapter
    private lateinit var renderer: PdfRenderer
    private lateinit var pdfViewDescriptor: ParcelFileDescriptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentViewerBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val path=requireArguments().getString("pdfPath")!!
        openPdf(File(path))


    }

    private fun openPdf(file: File) {
        pdfViewDescriptor= ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        renderer= PdfRenderer(pdfViewDescriptor)
        viewAdapter= PdfViewAdapter(renderer)
        binding.recyclerView.adapter=viewAdapter
    }
}
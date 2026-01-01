package com.example.paperxr.ui.pdflist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paperxr.data.model.PdfData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    private val repository: PdfRepository
) : ViewModel() {

    private val _pdfs = MutableStateFlow<List<PdfData>>(emptyList())
    val pdfs = _pdfs.asStateFlow()

    fun loadAllPdfs() {
        viewModelScope.launch {
            _pdfs.value = repository.getAllPdfs()
        }
    }
}

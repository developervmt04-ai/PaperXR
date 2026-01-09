package com.example.paperxr.ui.pdflist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paperxr.data.model.PdfData
import com.example.paperxr.databinding.PdfItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PdfListAdapter(): RecyclerView.Adapter<PdfListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val binding= PdfItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
      val pdfs=differ.currentList[position]
        holder.binding.pdfTitle.text = pdfs.name
        val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        val dateInMillis = pdfs.dateAdded?.times(1000) ?: 0L
        val date = Date(dateInMillis)
        holder.binding.date.text = dateFormatter.format(date)
        holder.binding.root.setOnClickListener {
            onClick?.invoke(pdfs)



        }



    }

    override fun getItemCount(): Int {
     return differ.currentList.size
    }


    val diffCallBack= object : DiffUtil.ItemCallback<PdfData>(){
        override fun areItemsTheSame(
            oldItem: PdfData,
            newItem: PdfData
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PdfData,
            newItem: PdfData
        ): Boolean {
           return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,diffCallBack)
    var onClick:((PdfData)-> Unit)?=null

    inner class ViewHolder(val binding: PdfItemBinding): RecyclerView.ViewHolder(binding.root){





    }
}
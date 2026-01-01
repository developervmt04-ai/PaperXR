package com.example.paperxr.ui.viewer

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PdfViewAdapter(
    private val renderer: PdfRenderer
): RecyclerView.Adapter<PdfViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val image= ImageView(parent.context)
        image.layoutParams= ViewGroup.LayoutParams(MATCH_PARENT,WRAP_CONTENT)
        image.adjustViewBounds=true
        return ViewHolder(image)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
       val page=renderer.openPage(position)
        val bitmap= Bitmap.createBitmap(
            page.width,
            page.height,
            Bitmap.Config.ARGB_8888
        )
        page.render(bitmap,null,null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        holder.image.setImageBitmap(bitmap)
        page.close()
    }

    override fun getItemCount(): Int {
        return renderer.pageCount
    }

    inner class ViewHolder(val image: ImageView): RecyclerView.ViewHolder(image)

}
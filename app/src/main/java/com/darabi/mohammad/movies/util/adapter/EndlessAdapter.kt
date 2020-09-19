package com.darabi.mohammad.movies.util.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class EndlessAdapter<VH: RecyclerView.ViewHolder, O> constructor(
    private val adapterConfigs: AdapterConfigs,
    private val adapterCallback: EndlessAdapterCallback
) : RecyclerView.Adapter<VH>() {

    init {
        adapterCallback.loadNextChunck(adapterConfigs.firstPage)
    }

    protected val objects = ArrayList<O>()

    protected val otherViewType = 0
    private val loadingViewType = 1

    private var page = adapterConfigs.firstPage

    fun setList(newObjects: List<O>) {
        objects.addAll(newObjects)
        notifyItemRangeChanged(page * adapterConfigs.itemCountPerPage, newObjects.size)
    }

    fun clear() {
        objects.clear()
        page = adapterConfigs.firstPage
        notifyItemRangeRemoved(0, objects.size)
    }

    override fun getItemViewType(position: Int): Int =
        if(adapterConfigs.isEnabledLoading)
            getViewType(position + 1)
        else super.getItemViewType(position)

    private fun getViewType(position: Int): Int =
        if(position == objects.size) loadingViewType else otherViewType

    override fun onBindViewHolder(holder: VH, position: Int) {
        if(position % adapterConfigs.itemCountPerPage == 0 && getCurrentPage(position) == page) {
            page++
            adapterCallback.loadNextChunck(page)
        }
    }

    override fun getItemCount(): Int =
        if(adapterConfigs.isEnabledLoading && objects.size > 0)
            objects.size + 1
        else objects.size

    private fun getCurrentPage(position: Int): Int = position / adapterConfigs.itemCountPerPage
}
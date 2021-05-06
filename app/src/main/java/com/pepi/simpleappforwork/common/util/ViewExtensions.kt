package com.pepi.simpleappforwork.common.util

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

fun RecyclerView.initiateSimpleRecycleView(
    adapter: Adapter<out RecyclerView.ViewHolder>,
    layoutManager: RecyclerView.LayoutManager,
    hasFixedSize: Boolean = false
)
{
    this.adapter = adapter
    this.layoutManager = layoutManager
    setHasFixedSize(hasFixedSize)
}
package com.pepi.simpleappforwork.common.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import java.io.IOException

fun RecyclerView.initiateSimpleRecycleView(
    adapter: Adapter<out RecyclerView.ViewHolder>,
    layoutManager: RecyclerView.LayoutManager,
    hasFixedSize: Boolean = false
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
    setHasFixedSize(hasFixedSize)
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleError(
    failure: Throwable,
    retry: (() -> Unit)? = null
) {
    when (failure) {
        is IOException -> requireView().snackbar(
            "Please check your internet connection",
            retry
        )
        is HttpException -> {
            //what happens there is http exception
            //this can be handled in a lot of ways.
            //this just shows the message
            requireView().snackbar(failure.localizedMessage ?: "error ${failure.code()}",retry)
        }
        else -> {
            val error = failure.localizedMessage?.toString()
            error?.let { requireView().snackbar(it,retry) }
        }
    }.exhaustive
}
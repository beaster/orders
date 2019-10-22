package me.sample.orders.presentation.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
    private val listener: ((T) -> Unit)? = null
) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    open fun onBind(item: T) {
        itemView.setOnClickListener {
            listener?.invoke(item)
        }
    }

    open fun onViewRecycled() {
    }
}
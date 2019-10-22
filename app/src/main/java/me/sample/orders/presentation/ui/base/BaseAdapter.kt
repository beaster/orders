package me.sample.orders.presentation.ui.base


import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val data = mutableListOf<T>()

    fun setItems(items: List<T>) {
        data.clear()
        data.addAll(items)
    }

    fun getItem(position: Int) = data[position]

    fun isEmpty() = data.size == 0

    fun clear() = data.clear()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onViewRecycled(holder: BaseViewHolder<T>) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }
}

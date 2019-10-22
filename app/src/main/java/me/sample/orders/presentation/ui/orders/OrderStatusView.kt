package me.sample.orders.presentation.ui.orders

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_status.view.*
import kotlinx.android.synthetic.main.layout_mode.view.*
import me.sample.orders.R
import me.sample.orders.presentation.ui.base.BaseAdapter
import me.sample.orders.presentation.ui.base.BaseViewHolder
import me.sample.orders.utils.toPx


class OrderStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: StatusAdapter
    private val smoothScroller by lazy {
        object : LinearSmoothScroller(context) {

            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }

            override fun calculateDtToFit(
                viewStart: Int,
                viewEnd: Int,
                boxStart: Int,
                boxEnd: Int,
                snapPreference: Int
            ): Int {
                return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
            }
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_mode, this)
        init()
    }

    private fun init() {
        adapter = StatusAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(OffsetDecoration())
    }

    fun setOnItemClickListener(listener: ((OrderStatusItem) -> Unit)) {
        adapter.listener = listener
    }

    fun setData(data: List<OrderStatusItem>) {
        val selectedPosition = data.indexOfFirst { it.isSelected }
        adapter.setItems(data)
        adapter.notifyDataSetChanged()

        smoothScroller.targetPosition = selectedPosition
        recyclerView.layoutManager?.startSmoothScroll(smoothScroller)
    }

    private class StatusAdapter : BaseAdapter<OrderStatusItem>() {

        var listener: ((OrderStatusItem) -> Unit)? = null
        private val innerListener: ((OrderStatusItem) -> Unit) = { item ->
            listener?.invoke(item)
        }

        @Suppress("UNCHECKED_CAST")
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<OrderStatusItem> {
            return StatusViewHolder(parent, innerListener)
        }
    }

    private class StatusViewHolder(parent: ViewGroup, listener: ((OrderStatusItem) -> Unit)) :
        BaseViewHolder<OrderStatusItem>(R.layout.item_status, parent, listener) {

        private val status = itemView.statusTextView

        override fun onBind(item: OrderStatusItem) {
            super.onBind(item)

            status.setText(item.orderStatus.titleRes)
            status.isSelected = item.isSelected
        }
    }

    private class OffsetDecoration : RecyclerView.ItemDecoration() {

        private val padding = 10.toPx()

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(padding, 0, padding, 0)
        }

    }

}
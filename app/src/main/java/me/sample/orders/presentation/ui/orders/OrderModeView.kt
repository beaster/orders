package me.sample.orders.presentation.ui.orders

import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_mode.view.*
import kotlinx.android.synthetic.main.layout_mode.view.*
import me.sample.orders.R
import me.sample.orders.presentation.ui.base.BaseAdapter
import me.sample.orders.presentation.ui.base.BaseViewHolder


class OrderModeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: ModeAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_mode, this)
        init()
    }

    private fun init() {
        adapter = ModeAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        addDivider()
    }

    private fun addDivider() {
        val atrrs = intArrayOf(android.R.attr.listDivider)

        val a = context.obtainStyledAttributes(atrrs)
        val divider = a.getDrawable(0)
        val inset = resources.getDimensionPixelSize(R.dimen.divider_margin)
        val insetDivider = InsetDrawable(divider, inset, 0, 0, 0)
        a.recycle()

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(insetDivider)
        recyclerView.addItemDecoration(itemDecoration)
    }

    fun setOnItemClickListener(listener: ((OrderModeItem) -> Unit)) {
        adapter.listener = listener
    }

    fun setData(data: List<OrderModeItem>) {
        adapter.setItems(data)
        adapter.notifyDataSetChanged()
    }

    private class ModeAdapter : BaseAdapter<OrderModeItem>() {

        var listener: ((OrderModeItem) -> Unit)? = null
        private val innerListener: ((OrderModeItem) -> Unit) = { item ->
            listener?.invoke(item)
        }

        @Suppress("UNCHECKED_CAST")
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<OrderModeItem> {
            return ModeViewHolder(parent, innerListener)
        }
    }

    private class ModeViewHolder(parent: ViewGroup, listener: ((OrderModeItem) -> Unit)) :
        BaseViewHolder<OrderModeItem>(R.layout.item_mode, parent, listener) {

        private val icon = itemView.iconDoneImageView
        private val title = itemView.titleTextView

        override fun onBind(item: OrderModeItem) {
            super.onBind(item)
            icon.isInvisible = !item.isSelected
            title.setText(item.orderMode.title)
        }
    }

}
package me.sample.orders.presentation.ui.orders

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.layout_mode.view.*
import me.sample.orders.R
import me.sample.orders.data.Order
import me.sample.orders.presentation.ui.base.BaseAdapter
import me.sample.orders.presentation.ui.base.BaseViewHolder
import me.sample.orders.utils.TimeUtils

class OrderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: OrderAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_mode, this)
        init()
    }

    private fun init() {
        adapter = OrderAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)
    }

    fun setOnItemClickListener(listener: ((Order) -> Unit)) {
        adapter.listener = listener
    }

    fun setData(data: List<Order>) {
        adapter.setItems(data)
        adapter.notifyDataSetChanged()
    }

    private class OrderAdapter : BaseAdapter<Order>() {

        var listener: ((Order) -> Unit)? = null
        private val innerListener: ((Order) -> Unit) = { item ->
            listener?.invoke(item)
        }

        @Suppress("UNCHECKED_CAST")
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<Order> {
            return ModeViewHolder(parent, innerListener)
        }
    }

    private class ModeViewHolder(parent: ViewGroup, listener: ((Order) -> Unit)) :
        BaseViewHolder<Order>(R.layout.item_order, parent, listener) {

        private val icon = itemView.iconImageView
        private val userName = itemView.userNameTextView
        private val time = itemView.timeTextView
        private val description = itemView.descriptionTextView
        private val status = itemView.statusTextView
        private val total = itemView.totalTextView

        override fun onBind(item: Order) {
            super.onBind(item)

            Glide.with(itemView.context.applicationContext)
                .load(item.userIcon)
                .apply(RequestOptions.circleCropTransform())
                .centerCrop()
                .into(icon)

            userName.text = item.userName
            description.text = item.description
            status.setText(item.status.titleRes)
            total.text = "${item.total} \u20BD"
            time.text = TimeUtils.getTime(itemView.context, item.date)
        }

        override fun onViewRecycled() {
            super.onViewRecycled()
            Glide.with(itemView.context.applicationContext).clear(icon)
        }
    }

}
package me.sample.orders.presentation.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_orders.*
import me.sample.orders.MainActivity
import me.sample.orders.R
import me.sample.orders.utils.AndroidUtils
import me.sample.orders.utils.getViewModel
import me.sample.orders.utils.observeNonNull

class OrdersFragment : Fragment() {

    private lateinit var viewModel: OrdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_orders, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()

        viewModel.showPanelLiveData.observeNonNull(viewLifecycleOwner) { isShowed ->
            toggleCustomerIcon(isShowed)
            togglePanel(isShowed)
        }

        viewModel.changeModeLiveData.observeNonNull(viewLifecycleOwner) { mode ->
            orderTextView.setText(mode.title)
        }

        viewModel.orderModeItemsLiveData.observeNonNull(viewLifecycleOwner) { items ->
            orderModeView.setData(items)
        }

        viewModel.statusItemsLiveData.observeNonNull(viewLifecycleOwner) { items ->
            orderStatusView.setData(items)
        }

        viewModel.ordersLiveData.observeNonNull(viewLifecycleOwner) { items ->
            orderView.setData(items)
        }

        orderTextView.setOnClickListener { viewModel.toggleCustomerPanel() }
        orderModeView.setOnItemClickListener { viewModel.onClickOrderMode(it.orderMode) }
        orderStatusView.setOnItemClickListener { viewModel.onClickStatus(it.orderStatus) }
        orderView.setOnItemClickListener { openUrl() }
    }

    private fun toggleCustomerIcon(isShowed: Boolean) {
        val icon = if (isShowed) {
            R.drawable.ic_arrow_up
        } else {
            R.drawable.ic_arrow_down
        }
        orderTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
    }

    private fun togglePanel(isShowed: Boolean) {
        (activity as MainActivity).showShadowView(isShowed)
        orderModeView.isVisible = isShowed
        shadowPanelView.isVisible = isShowed
    }

    private fun openUrl() {
        AndroidUtils.openUrl(requireContext(), "http://kwork.ru")
    }

}
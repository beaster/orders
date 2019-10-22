package me.sample.orders.presentation.ui.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.sample.orders.data.Order
import me.sample.orders.data.OrderMode
import me.sample.orders.data.OrderStatus
import me.sample.orders.domain.OrderRepository

class OrdersViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    companion object {
        private val DEFAULT_STATUS = OrderStatus.IN_PROGRESS
        private val DEFAULT_MODE = OrderMode.CUSTOMER
    }

    private var currentStatus = DEFAULT_STATUS

    val showPanelLiveData = MutableLiveData<Boolean>()
    val changeModeLiveData = MutableLiveData<OrderMode>()

    val orderModeItemsLiveData = MutableLiveData<List<OrderModeItem>>()
    val statusItemsLiveData = MutableLiveData<List<OrderStatusItem>>()
    val ordersLiveData = MutableLiveData<List<Order>>()

    init {
        showPanelLiveData.value = false
        updateCurrentMode(OrderMode.CUSTOMER)
        updateCurrentStatus(DEFAULT_STATUS)
        fetchOrders(DEFAULT_STATUS)
    }

    fun toggleCustomerPanel() {
        val isShowed = showPanelLiveData.value ?: false
        showPanelLiveData.value = !isShowed
    }

    fun onClickOrderMode(mode: OrderMode) {
        toggleCustomerPanel()
        updateCurrentMode(mode)
    }

    fun onClickStatus(status: OrderStatus) {
        updateCurrentStatus(status)
        fetchOrders(status)
    }

    private fun updateCurrentMode(mode: OrderMode) {
        changeModeLiveData.value = mode
        orderModeItemsLiveData.value = OrderMode.values().map {
            OrderModeItem(it, it == mode)
        }
    }

    private fun updateCurrentStatus(status: OrderStatus) {
        currentStatus = status
        statusItemsLiveData.value = OrderStatus.values().map {
            OrderStatusItem(it, it == status)
        }
    }

    private fun fetchOrders(status: OrderStatus) {
        ordersLiveData.value = orderRepository.getOrders(status)
    }
}

class OrderModeItem(val orderMode: OrderMode, val isSelected: Boolean)
class OrderStatusItem(val orderStatus: OrderStatus, val isSelected: Boolean)


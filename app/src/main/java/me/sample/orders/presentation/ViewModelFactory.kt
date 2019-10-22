package me.sample.orders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.sample.orders.presentation.ui.orders.OrdersViewModel

object ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(OrdersViewModel::class.java) -> OrdersViewModel(Injection.orderRepository) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}
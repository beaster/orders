package me.sample.orders.presentation

import me.sample.orders.domain.OrderRepository

internal object Injection {
    val orderRepository by lazy { OrderRepository() }

}
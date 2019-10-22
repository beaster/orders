package me.sample.orders.data

import android.net.Uri
import java.util.*

class Order(
    val userIcon: Uri,
    val userName: String,
    val date: Date,
    val description: String,
    val status: OrderStatus,
    val total: Int
)
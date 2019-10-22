package me.sample.orders.data

import androidx.annotation.StringRes
import me.sample.orders.R

enum class OrderMode(@StringRes val title: Int) {

    CUSTOMER(R.string.title_customer), SELLER(R.string.title_seller)
}
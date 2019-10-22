package me.sample.orders.data

import androidx.annotation.StringRes
import me.sample.orders.R

enum class OrderStatus(@StringRes val titleRes: Int) {
    NEW(R.string.status_new),
    IN_PROGRESS(R.string.status_in_progress),
    DONE(R.string.status_done),
    NO_DATA(R.string.status_no_data)
}
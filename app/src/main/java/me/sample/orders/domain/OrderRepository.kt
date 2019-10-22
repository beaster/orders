package me.sample.orders.domain

import android.net.Uri
import me.sample.orders.data.Order
import me.sample.orders.data.OrderStatus
import java.util.*

class OrderRepository {

    fun getOrders(status: OrderStatus): List<Order> {
        return MOCK_DATA.filter { it.status == status }
    }

    companion object {
        private val userIcon = Uri.parse("file:///android_asset/userIcon.png")

        private val MOCK_DATA = listOf(
            Order(
                userIcon,
                "Vangelioff",
                Date(),
                "Сделаю элегантный премиум логотип + визитная карточка",
                OrderStatus.IN_PROGRESS,
                155000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 7, 27),
                "Логотип по образцу в векторе в максимальном качестве",
                OrderStatus.IN_PROGRESS,
                32000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 7),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.IN_PROGRESS,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 2),
                "Сделаю элегантный премиум логотип + визитная карточка",
                OrderStatus.NEW,
                155000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(118, 8, 7),
                "Логотип по образцу в векторе в максимальном качестве",
                OrderStatus.DONE,
                32000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 7),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.NO_DATA,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 6),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.IN_PROGRESS,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(118, 8, 7),
                "Логотип по образцу в векторе в максимальном качестве",
                OrderStatus.DONE,
                32000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 7),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.NO_DATA,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 6),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.NO_DATA,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(118, 8, 7),
                "Логотип по образцу в векторе в максимальном качестве",
                OrderStatus.NEW,
                32000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 7),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.IN_PROGRESS,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 6),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.NO_DATA,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(118, 8, 7),
                "Логотип по образцу в векторе в максимальном качестве",
                OrderStatus.NEW,
                32000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 7),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.DONE,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(119, 8, 6),
                "Съемка с квадрокоптера всего процесса обучения",
                OrderStatus.DONE,
                45000
            ),
            Order(
                userIcon,
                "Vangelioff",
                Date(118, 8, 7),
                "Логотип по образцу в векторе в максимальном качестве",
                OrderStatus.DONE,
                32000
            )
        )
    }
}
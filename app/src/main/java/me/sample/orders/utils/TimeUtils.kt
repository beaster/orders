package me.sample.orders.utils

import android.content.Context
import me.sample.orders.R
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    private val TODAY = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    private val CURRENT_YEAR = SimpleDateFormat("dd.MM", Locale.ENGLISH)
    private val OLD = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    fun getTime(context: Context, date: Date): String {
        val currentDate = Date()

        return if (areSameDays(date, currentDate)) {
            if (currentDate.hours - date.hours <= 4) {
                TODAY.format(date)
            } else {
                context.getString(R.string.today)
            }
        } else {
            if (currentDate.year == date.year) {
                if (currentDate.day - date.day == 1) {
                    context.getString(R.string.yesterday)
                } else {
                    CURRENT_YEAR.format(date)
                }
            } else {
                OLD.format(date)
            }
        }
    }

    private fun areSameDays(date1: Date, date2: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date1.time
        val y1 = calendar.get(Calendar.YEAR)
        val m1 = calendar.get(Calendar.MONTH)
        val d1 = calendar.get(Calendar.DATE)
        calendar.timeInMillis = date2.time
        val y2 = calendar.get(Calendar.YEAR)
        val m2 = calendar.get(Calendar.MONTH)
        val d2 = calendar.get(Calendar.DATE)

        return y1 == y2 && m1 == m2 && d1 == d2
    }
}

package me.sample.orders.utils

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import me.sample.orders.presentation.ViewModelFactory


private val density by lazy { Resources.getSystem().displayMetrics.density }
fun Int.toPx(): Int = (this * density).toInt()

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, callback: (T) -> Unit) {
    this.observe(owner, Observer { value ->
        if (value != null) {
            callback.invoke(value)
        }
    })
}

inline fun <reified T : ViewModel> Fragment.getViewModel(): T =
    ViewModelProviders.of(this, ViewModelFactory).get(T::class.java)

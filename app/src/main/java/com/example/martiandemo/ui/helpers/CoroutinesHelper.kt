package com.example.martiandemo.ui.helpers

import kotlinx.coroutines.*


fun <T> lazyDeferred(scope: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            scope.invoke(this)
        }
    }
}
package org.scobca.loggerservice.io

interface AbstractResponse<T> {
    val status: Int
    val message: T?
}
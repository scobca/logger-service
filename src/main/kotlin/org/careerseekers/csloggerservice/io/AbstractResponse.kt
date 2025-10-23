package org.careerseekers.csloggerservice.io

interface AbstractResponse<T> {
    val status: Int
    val message: T?
}
package org.scobca.loggerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CsLoggerServiceApplication

fun main(args: Array<String>) {
    runApplication<CsLoggerServiceApplication>(*args)
}

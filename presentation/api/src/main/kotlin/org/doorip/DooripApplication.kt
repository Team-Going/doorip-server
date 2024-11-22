package org.doorip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DooripApplication

fun main(args: Array<String>) {
    runApplication<DooripApplication>(*args)
}

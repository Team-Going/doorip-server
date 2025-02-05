package org.doorip.support.utils

import kotlin.random.Random

object RandomCodeGenerator {

    fun generateRandomCode(length: Int = 6): String {
        val source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

        return (1..length)
            .map { Random.nextInt(0, source.length) }
            .map(source::get)
            .joinToString("")
    }
}

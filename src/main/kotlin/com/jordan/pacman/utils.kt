package com.jordan.pacman

import kotlinx.coroutines.delay
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.random.Random

suspend fun targeting(millisPerFrame: Int, block: suspend () -> Unit) {
    val start = Instant.now()
    block()
    val time = start.until(Instant.now(), ChronoUnit.MILLIS)
    if (time < millisPerFrame) {
        delay(millisPerFrame - time)
    }
}

inline fun <T, R : Comparable<R>> Iterable<T>.maxByOrNullBreakingTiesRandomly(selector: (T) -> R): T? {
    val iterator = iterator()
    if (!iterator.hasNext()) return null

    var maxElem = iterator.next()
    if (!iterator.hasNext()) return maxElem

    var maxValue = selector(maxElem)
    do {
        val element = iterator.next()
        val value = selector(element)
        if (value > maxValue) {
            maxElem = element
            maxValue = value
        } else if (value == maxValue && Random.nextBoolean()) {
            maxElem = element
            maxValue = value
        }
    } while (iterator.hasNext())

    return maxElem
}
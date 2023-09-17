package com.jordan.pacman.game

import kotlin.math.pow
import kotlin.math.sqrt

data class Position(
    val x: Double,
    val y: Double
) {
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    fun move(direction: Direction, distance: Double): Position {
        return when (direction) {
            Direction.UP -> Position(x, y - distance)
            Direction.DOWN -> Position(x, y + distance)
            Direction.LEFT -> Position(x - distance, y)
            Direction.RIGHT -> Position(x + distance, y)
        }
    }

    fun distanceTo(position: Position): Double {
        return sqrt((x - position.x).pow(2.0) + (y - position.y).pow(2.0))
    }
}
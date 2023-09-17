package com.jordan.pacman.game

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun reverse() = when (this) {
        UP -> DOWN
        DOWN -> UP
        LEFT -> RIGHT
        RIGHT -> LEFT
    }
}
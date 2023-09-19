package com.jordan.pacman.game

class BonusPoints {

    var position: Position = Position(0, 0)
    var points: Int = 0
    var ticks: Int = 0

    fun reward(position: Position, points: Int) {
        this.position = position
        this.points = points
        this.ticks = 120
    }

    fun isActive(): Boolean {
        return ticks > 0
    }
}

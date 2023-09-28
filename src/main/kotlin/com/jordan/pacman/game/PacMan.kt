package com.jordan.pacman.game

import com.jordan.pacman.Globals.SCALING_FACTOR
import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.Globals.TILE_SIZE
import javafx.scene.image.Image

class PacMan {

    private val startingPosition = Position(14.0 * TILE_SIZE, 23.5 * TILE_SIZE)
    var position = startingPosition

    var currentDirection: Direction? = null
    var nextDirection: Direction? = null

    private var mouthOpen = true
    private var mouthOpenTicks = 0

    private var isFrightening = false
    private var frighteningTicks = 0

    private var normalSpeed = Levels[0].pacmanNormalSpeed
    private var frighteningSpeed = Levels[0].pacmanFrighteningSpeed

    private var speedTicks = 0

    private fun tick() {
        if (++mouthOpenTicks == 7) {
            mouthOpenTicks = 0
            mouthOpen = !mouthOpen
        }
    }

    fun getImage(): Image {
        return if (mouthOpen)
            when (currentDirection) {
                Direction.UP -> imageUp
                Direction.DOWN -> imageDown
                Direction.LEFT -> imageLeft
                Direction.RIGHT -> imageRight
                null -> image
            }
        else {
            image
        }
    }

    fun energize() {
        isFrightening = true
    }

    fun reset(level: Level) {
        position = startingPosition

        currentDirection = null
        nextDirection = null

        mouthOpen = true
        mouthOpenTicks = 0

        isFrightening = false
        frighteningTicks = 0

        normalSpeed = level.pacmanNormalSpeed
        frighteningSpeed = level.pacmanFrighteningSpeed

        speedTicks = 0
    }

    fun update(level: Level, maze: Maze) {
        if (isFrightening) {
            if (frighteningTicks++ == level.scaredLimit) {
                isFrightening = false
            }
        }

        if (canTurn(maze, nextDirection)) {
            currentDirection = nextDirection
            nextDirection = null
        }

        val speed = if (isFrightening) frighteningSpeed else normalSpeed
        if (speedTicks >= speed.size) {
            speedTicks = 0
        }
        for (i in 0..<speed[speedTicks++]) {
            move(maze)
            teleportIfAtEndOfTunnel()
        }
    }

    private fun move(direction: Direction) {
        position = position.move(direction, SCALING_FACTOR.toDouble())
    }

    private fun move(maze: Maze) {
        if (canMove(maze, currentDirection)) {
            move(currentDirection!!)

            val corneringAdjustment = maze.directionToCentreOfTileAlongDirection(position, currentDirection)
            if (corneringAdjustment != null) {
                move(corneringAdjustment)
            }

            tick()
        }
    }

    private fun canTurn(maze: Maze, direction: Direction?): Boolean {
        if (direction == null) {
            return false
        }
        return maze.tileInDirection(position, direction).navigable
    }

    private fun canMove(maze: Maze, direction: Direction?): Boolean {
        if (direction == null) {
            return false
        }

        val canMoveToNextTile = maze.tileInDirection(position, direction).navigable
        if (maze.stillWithinSameTile(position, direction, SCALING_FACTOR.toDouble())) {
            return canMoveToNextTile || maze.willNotPassTileCentre(position, direction, SCALING_FACTOR.toDouble())
        }
        return canMoveToNextTile
    }

    private fun teleportIfAtEndOfTunnel() {
        if (position.x < 0.0) {
            position = position.copy(x = 28.0 * TILE_SIZE - SCALING_FACTOR.toDouble())
        } else if (position.x > 28.0 * TILE_SIZE - SCALING_FACTOR.toDouble()) {
            position = position.copy(x = 0.0)
        }
    }

    companion object {
        val image = Image("$SPRITES_BASE_PATH/pacman/pacman.png")
        val imageUp = Image("$SPRITES_BASE_PATH/pacman/pacmanUp.png")
        val imageDown = Image("$SPRITES_BASE_PATH/pacman/pacmanDown.png")
        val imageLeft = Image("$SPRITES_BASE_PATH/pacman/pacmanLeft.png")
        val imageRight = Image("$SPRITES_BASE_PATH/pacman/pacmanRight.png")
    }
}
package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Pinky : Ghost() {

    override val startingPosition = Position(14.0 * TILE_SIZE, 14.5 * TILE_SIZE)
    override var position = startingPosition

    override var currentDirection = Direction.DOWN
    override var nextDirection = Direction.LEFT
    override var directionFromNextTile = Direction.LEFT

    override val upImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyUp.png")
    override val downImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyDown.png")
    override val leftImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyLeft.png")
    override val rightImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyRight.png")

    override var isInGhostHouse = true

    override val dotLimit = 0

    override fun reset(level: Level) {
        super.reset(level)

        currentDirection = Direction.DOWN
        nextDirection = Direction.LEFT
        directionFromNextTile = Direction.LEFT

        isInGhostHouse = true
    }

    /**
     * Target 4 tiles in front of Pac-Man
     */
    override fun getTarget(game: Game): Position {
        if (game.scatterMode()) {
            return Position(2.0 * TILE_SIZE, -4.0 * TILE_SIZE)
        }
        return game.maze.tileAt(game.pacman.position).position
            .move(game.pacman.currentDirection ?: Direction.LEFT, 4.0 * TILE_SIZE)
    }

    override fun moveToEnterGhostHouse() {
        if (position.y < 14.5 * TILE_SIZE) {
            currentDirection = Direction.DOWN
            move(Direction.DOWN)
            return
        }

        isEyes = false
        isEnteringGhostHouse = false
        isInGhostHouse = true
    }
}

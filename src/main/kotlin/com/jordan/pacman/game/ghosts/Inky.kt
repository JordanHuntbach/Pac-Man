package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Inky : Ghost() {

    override val startingPosition = Position(12.0 * TILE_SIZE, 14.5 * TILE_SIZE)
    override var position = startingPosition

    override var currentDirection = Direction.UP
    override var nextDirection = Direction.LEFT
    override var directionFromNextTile = Direction.LEFT

    override val upImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyUp.png")
    override val downImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyDown.png")
    override val leftImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyLeft.png")
    override val rightImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyRight.png")

    override var isInGhostHouse = true

    override var dotLimit = Levels[0].inkyDotLimit

    override fun reset(level: Level) {
        super.reset(level)

        currentDirection = Direction.UP
        nextDirection = Direction.LEFT
        directionFromNextTile = Direction.LEFT

        isInGhostHouse = true

        dotLimit = level.inkyDotLimit
    }

    /**
     * Take Blinky's tile, create a vector from there to 2 tiles in front of Pac-Man, and double it
     */
    override fun getTarget(game: Game): Position {
        if (game.scatterMode()) {
            return Position(27.0 * TILE_SIZE, 31.0 * TILE_SIZE)
        }
        val blinkyPosition = game.maze.tileAt(game.blinky.position).position
        val twoTilesAhead = game.maze.tileAt(game.pacman.position).position
            .move(game.pacman.currentDirection ?: Direction.LEFT, 2.0 * TILE_SIZE)
        val vector = Position(twoTilesAhead.x - blinkyPosition.x, twoTilesAhead.y - blinkyPosition.y)
        return Position(blinkyPosition.x + vector.x * 2, blinkyPosition.y + vector.y * 2)
    }

    override fun moveToEnterGhostHouse() {
        if (position.y < 14.5 * TILE_SIZE) {
            currentDirection = Direction.DOWN
            move(Direction.DOWN)
            return
        }

        if (position.x > startingPosition.x) {
            currentDirection = Direction.LEFT
            move(Direction.LEFT)
            return
        }

        isEyes = false
        isEnteringGhostHouse = false
        isInGhostHouse = true
    }
}

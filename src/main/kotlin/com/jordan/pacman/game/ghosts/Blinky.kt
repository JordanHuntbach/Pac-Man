package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import com.jordan.pacman.game.ghosts.Blinky.State.CRUISE_ELROY
import com.jordan.pacman.game.ghosts.Blinky.State.CRUISE_ELROY_2
import com.jordan.pacman.game.ghosts.Blinky.State.NORMAL
import javafx.scene.image.Image

class Blinky : Ghost() {

    override val startingPosition = Position(14.0 * TILE_SIZE, 11.5 * TILE_SIZE)
    override var position = startingPosition

    override var currentDirection = Direction.LEFT
    override var nextDirection = Direction.LEFT
    override var directionFromNextTile = Direction.LEFT

    override val upImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyUp.png")
    override val downImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyDown.png")
    override val leftImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyLeft.png")
    override val rightImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyRight.png")

    override var isScared = false
    override var isEyes = false
    override var isInGhostHouse = false

    override val dotLimit = 0

    override var normalSpeed = Levels[0].ghostNormalSpeed

    var state = NORMAL

    override fun reset(level: Level) {
        super.reset(level)
        normalSpeed = level.ghostNormalSpeed
        position = startingPosition
        currentDirection = Direction.LEFT
        nextDirection = Direction.LEFT
        directionFromNextTile = Direction.LEFT
        isInGhostHouse = false
        state = NORMAL
    }

    /**
     * Always target Pac-Man's current tile
     */
    override fun getTarget(game: Game): Position {
        if (game.scatterMode() && state == NORMAL) {
            return Position(25.0 * TILE_SIZE, -4.0 * TILE_SIZE)
        }
        return game.maze.tileAt(game.pacman.position).position
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

    fun activateCruiseElroy(level: Level) {
        state = CRUISE_ELROY
        normalSpeed = level.cruiseElroySpeed
    }

    fun activateCruiseElroy2(level: Level) {
        state = CRUISE_ELROY_2
        normalSpeed = level.cruiseElroy2Speed
    }

    enum class State {
        NORMAL,
        CRUISE_ELROY,
        CRUISE_ELROY_2
    }
}

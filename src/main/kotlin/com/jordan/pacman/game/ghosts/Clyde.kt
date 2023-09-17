package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Clyde : Ghost() {

    override val startingPosition = Position(16.0 * TILE_SIZE, 14.5 * TILE_SIZE)
    override var position = startingPosition

    override var currentDirection = Direction.UP
    override var nextDirection = Direction.LEFT
    override var directionFromNextTile = Direction.LEFT

    override val upImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeUp.png")
    override val downImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeDown.png")
    override val leftImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeLeft.png")
    override val rightImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeRight.png")

    override var isScared = false
    override var isEyes = false
    override var isInGhostHouse = true

    override var dotLimit = Levels[0].clydeDotLimit

    override var normalSpeed = Levels[0].ghostNormalSpeed

    override fun reset(level: Level) {
        super.reset(level)
        dotLimit = level.clydeDotLimit
        normalSpeed = level.ghostNormalSpeed
        position = startingPosition
        currentDirection = Direction.UP
        nextDirection = Direction.LEFT
        directionFromNextTile = Direction.LEFT
        isInGhostHouse = true
    }

    /**
     * Target Pac-Man's current tile if Clyde is more than 8 tiles away, otherwise target the bottom left corner
     */
    override fun getTarget(game: Game): Position {
        if (game.scatterMode()) {
            return Position(0.0 * TILE_SIZE, 31.0 * TILE_SIZE)
        }
        val pacmanTile = game.maze.tileAt(game.pacman.position).position
        val clydeTile = game.maze.tileAt(position).position
        return if (clydeTile.distanceTo(pacmanTile) > 8 * TILE_SIZE) {
            pacmanTile
        } else {
            Position(0.0, 30.0 * TILE_SIZE)
        }
    }

    override fun moveToEnterGhostHouse() {
        if (position.y < 14.5 * TILE_SIZE) {
            currentDirection = Direction.DOWN
            move(Direction.DOWN)
            return
        }

        if (position.x < startingPosition.x) {
            currentDirection = Direction.RIGHT
            move(Direction.RIGHT)
            return
        }

        isEyes = false
        isEnteringGhostHouse = false
        isInGhostHouse = true
    }
}

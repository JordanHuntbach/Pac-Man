package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Clyde(
    position: Position = Position(16.0 * TILE_SIZE, 14.5 * TILE_SIZE),

    currentDirection: Direction = Direction.UP,
    nextDirection: Direction = Direction.LEFT,
    directionFromNextTile: Direction = Direction.LEFT,

    isScared: Boolean = false,
    isEyes: Boolean = false,
    isInGhostHouse: Boolean = true,

    isEnteringGhostHouse: Boolean = false,
    isLeavingGhostHouse: Boolean = false,

    scaredTickCount: Int = 0,
    scaredTickLimit: Int = Levels[0].scaredLimit,
    scaredFlashes: Int = Levels[0].scaredFlashes,

    dotCounter: Int = 0,

    normalSpeed: IntArray = Levels[0].ghostNormalSpeed,
    tunnelSpeed: IntArray = Levels[0].ghostTunnelSpeed,
    scaredSpeed: IntArray = Levels[0].ghostScaredSpeed,
    eyesSpeed: IntArray = Levels[0].ghostEyesSpeed,
    houseSpeed: IntArray = Levels[0].ghostHouseSpeed,

    speedTicks: Int = 0,

    shouldReverse: Boolean = false,
    switchedModesWhileInGhostHouse: Boolean = false,

    hasBeenReleased: Boolean = false,

    dotLimit: Int = Levels[0].clydeDotLimit
) : Ghost(
    position = position,

    currentDirection = currentDirection,
    nextDirection = nextDirection,
    directionFromNextTile = directionFromNextTile,

    isScared = isScared,
    isEyes = isEyes,
    isInGhostHouse = isInGhostHouse,

    isEnteringGhostHouse = isEnteringGhostHouse,
    isLeavingGhostHouse = isLeavingGhostHouse,

    scaredTickCount = scaredTickCount,
    scaredTickLimit = scaredTickLimit,
    scaredFlashes = scaredFlashes,

    dotCounter = dotCounter,

    normalSpeed = normalSpeed,
    tunnelSpeed = tunnelSpeed,
    scaredSpeed = scaredSpeed,
    eyesSpeed = eyesSpeed,
    houseSpeed = houseSpeed,

    speedTicks = speedTicks,

    shouldReverse = shouldReverse,
    switchedModesWhileInGhostHouse = switchedModesWhileInGhostHouse,

    hasBeenReleased = hasBeenReleased,

    dotLimit = dotLimit
) {
    override val name = "Clyde"

    override val startingPosition = Position(16.0 * TILE_SIZE, 14.5 * TILE_SIZE)

    override val upImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeUp.png")
    override val downImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeDown.png")
    override val leftImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeLeft.png")
    override val rightImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/clyde/clydeRight.png")

    override fun reset(level: Level) {
        super.reset(level)

        currentDirection = Direction.UP
        nextDirection = Direction.LEFT
        directionFromNextTile = Direction.LEFT

        isInGhostHouse = true

        dotLimit = level.clydeDotLimit
    }

    /**
     * Target Pac-Man's current tile if Clyde is more than 8 tiles away, otherwise target the bottom left corner
     */
    override fun getTarget(game: Game): Position {
        val pacmanTile = game.maze.tileAt(game.pacman.position).position
        val clydeTile = game.maze.tileAt(position).position
        return if (game.scatterMode() || clydeTile.distanceTo(pacmanTile) <= 8 * TILE_SIZE) {
            Position(0.0, 31.0 * TILE_SIZE)
        } else {
            pacmanTile
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

    override fun copy(): Clyde {
        return Clyde(
            position = position,

            currentDirection = currentDirection,
            nextDirection = nextDirection,
            directionFromNextTile = directionFromNextTile,

            isScared = isScared,
            isEyes = isEyes,
            isInGhostHouse = isInGhostHouse,

            isEnteringGhostHouse = isEnteringGhostHouse,
            isLeavingGhostHouse = isLeavingGhostHouse,

            scaredTickCount = scaredTickCount,
            scaredTickLimit = scaredTickLimit,
            scaredFlashes = scaredFlashes,

            dotCounter = dotCounter,

            normalSpeed = normalSpeed,
            tunnelSpeed = tunnelSpeed,
            scaredSpeed = scaredSpeed,
            eyesSpeed = eyesSpeed,
            houseSpeed = houseSpeed,

            speedTicks = speedTicks,

            shouldReverse = shouldReverse,
            switchedModesWhileInGhostHouse = switchedModesWhileInGhostHouse,

            hasBeenReleased = hasBeenReleased,

            dotLimit = dotLimit,
        )
    }

}

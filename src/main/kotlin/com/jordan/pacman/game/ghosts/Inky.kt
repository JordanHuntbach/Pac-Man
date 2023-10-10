package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Inky(
    position: Position = Position(12.0 * TILE_SIZE, 14.5 * TILE_SIZE),

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

    dotLimit: Int = Levels[0].inkyDotLimit
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

    dotLimit = dotLimit,
) {
    override val name = "Inky"

    override val startingPosition = Position(12.0 * TILE_SIZE, 14.5 * TILE_SIZE)

    override val upImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyUp.png")
    override val downImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyDown.png")
    override val leftImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyLeft.png")
    override val rightImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/inky/inkyRight.png")

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

    override fun copy(): Inky {
        return Inky(
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

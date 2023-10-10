package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Pinky(
    position: Position = Position(14.0 * TILE_SIZE, 14.5 * TILE_SIZE),

    currentDirection: Direction = Direction.DOWN,
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

    dotLimit = 0,
) {
    override val name = "Pinky"

    override val startingPosition = Position(14.0 * TILE_SIZE, 14.5 * TILE_SIZE)

    override val upImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyUp.png")
    override val downImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyDown.png")
    override val leftImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyLeft.png")
    override val rightImage = Image("${Globals.SPRITES_BASE_PATH}/ghosts/pinky/pinkyRight.png")

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

    override fun copy(): Pinky {
        return Pinky(
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
        )
    }
}

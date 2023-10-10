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

class Blinky(
    position: Position = Position(14.0 * TILE_SIZE, 11.5 * TILE_SIZE),

    currentDirection: Direction = Direction.LEFT,
    nextDirection: Direction = Direction.LEFT,
    directionFromNextTile: Direction = Direction.LEFT,

    isScared: Boolean = false,
    isEyes: Boolean = false,
    isInGhostHouse: Boolean = false,

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

    var state: State = NORMAL,
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
    override val name = "Blinky"

    override val startingPosition = Position(14.0 * TILE_SIZE, 11.5 * TILE_SIZE)

    override val upImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyUp.png")
    override val downImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyDown.png")
    override val leftImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyLeft.png")
    override val rightImage = Image("$SPRITES_BASE_PATH/ghosts/blinky/blinkyRight.png")

    override fun reset(level: Level) {
        super.reset(level)

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

    override fun copy(): Blinky {
        return Blinky(
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

            state = state,
        )
    }

    enum class State {
        NORMAL,
        CRUISE_ELROY,
        CRUISE_ELROY_2
    }
}

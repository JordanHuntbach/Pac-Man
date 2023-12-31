package com.jordan.pacman.game.ghosts

import com.jordan.pacman.Globals.SCALING_FACTOR
import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Level
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.Maze
import com.jordan.pacman.game.Position
import com.jordan.pacman.game.tiles.EmptyTile
import com.jordan.pacman.game.tiles.Tile
import javafx.scene.image.Image
import mu.KLogging

abstract class Ghost(
    var position: Position,

    var currentDirection: Direction,
    var nextDirection: Direction,
    var directionFromNextTile: Direction,

    var isScared: Boolean = false,
    var isEyes: Boolean = false,
    var isInGhostHouse: Boolean,

    var isEnteringGhostHouse: Boolean = false,
    protected var isLeavingGhostHouse: Boolean = false,

    protected var scaredTickCount: Int = 0,
    protected var scaredTickLimit: Int = Levels[0].scaredLimit,
    protected var scaredFlashes: Int = Levels[0].scaredFlashes,

    var dotCounter: Int = 0,

    protected var normalSpeed: IntArray = Levels[0].ghostNormalSpeed,
    protected var tunnelSpeed: IntArray = Levels[0].ghostTunnelSpeed,
    protected var scaredSpeed: IntArray = Levels[0].ghostScaredSpeed,
    protected var eyesSpeed: IntArray = Levels[0].ghostEyesSpeed,
    protected var houseSpeed: IntArray = Levels[0].ghostHouseSpeed,

    protected var speedTicks: Int = 0,

    protected var shouldReverse: Boolean = false,
    protected var switchedModesWhileInGhostHouse: Boolean = false,

    var hasBeenReleased: Boolean = false,

    var dotLimit: Int
) {
    abstract val name: String

    abstract val startingPosition: Position

    abstract val upImage: Image
    abstract val downImage: Image
    abstract val leftImage: Image
    abstract val rightImage: Image

    fun getImage(): Image {
        return if (isEyes) {
            when (currentDirection) {
                Direction.UP -> eyesUpImage
                Direction.DOWN -> eyesDownImage
                Direction.LEFT -> eyesLeftImage
                Direction.RIGHT -> eyesRightImage
            }
        } else if (isScared) {
            if (scaredTickCount < scaredTickLimit - scaredFlashes * 24) {
                scaredBlueImage
            } else if ((scaredTickCount / 12) % 2 == 0) {
                scaredWhiteImage
            } else {
                scaredBlueImage
            }
        } else {
            when (currentDirection) {
                Direction.UP -> upImage
                Direction.DOWN -> downImage
                Direction.LEFT -> leftImage
                Direction.RIGHT -> rightImage
            }
        }
    }

    open fun reset(level: Level) {
        position = startingPosition

        isScared = false
        isEyes = false

        isEnteringGhostHouse = false
        isLeavingGhostHouse = false

        scaredTickCount = 0
        scaredTickLimit = level.scaredLimit
        scaredFlashes = level.scaredFlashes

        dotCounter = 0

        normalSpeed = level.ghostNormalSpeed
        tunnelSpeed = level.ghostTunnelSpeed
        scaredSpeed = level.ghostScaredSpeed
        eyesSpeed = level.ghostEyesSpeed
        houseSpeed = level.ghostHouseSpeed

        speedTicks = 0

        shouldReverse = false
        switchedModesWhileInGhostHouse = false

        hasBeenReleased = false
    }

    fun eaten() {
        isScared = false
        isEyes = true
    }

    fun scare() {
        isScared = true
        scaredTickCount = 0
        shouldReverse = true
    }

    abstract fun getTarget(game: Game): Position

    /**
     * We always decide where to go next before we enter a new tile.
     *
     * If we're before the halfway point of the tile we're in, just keep moving in that direction.
     * If we're at the halfway point, move in the direction we've decided to go next.
     * If we're past the halfway point, just keep going towards the end of the tile.
     * If we're entering a new tile, look ahead to the tile we've already decided to move in next,
     * and decide where to go from there.
     */
    fun update(game: Game) {
        if (isScared) {
            if (scaredTickCount++ == scaredTickLimit) {
                isScared = false
            }
        }

        if (!isInGhostHouse) {
            val speed = when {
                isEyes -> eyesSpeed
                inTunnel(game.maze) -> tunnelSpeed
                isScared -> scaredSpeed
                isLeavingGhostHouse -> houseSpeed
                else -> normalSpeed
            }
            if (speedTicks >= speed.size) {
                speedTicks = 0
            }
            for (i in 0..<speed[speedTicks++]) {
                if (isEyes && position == Position(14.0 * TILE_SIZE, 11.5 * TILE_SIZE)) {
                    isEnteringGhostHouse = true
                }

                if (isLeavingGhostHouse) {
                    moveToLeaveGhostHouse()
                } else if (isEnteringGhostHouse) {
                    moveToEnterGhostHouse()
                } else {
                    move(game)
                }
            }
        }
    }

    fun leaveGhostHouse() {
        isInGhostHouse = false
        isLeavingGhostHouse = true
        hasBeenReleased = true
    }

    fun switchedGhostMode() {
        if (!isScared && !isEyes && !isInGhostHouse && !isEnteringGhostHouse && !isLeavingGhostHouse) {
            shouldReverse = true
            return
        }
        if (isInGhostHouse || isLeavingGhostHouse) {
            switchedModesWhileInGhostHouse = true
        }
    }

    protected fun move(direction: Direction) {
        position = position.move(direction, SCALING_FACTOR.toDouble())
        teleportIfAtEndOfTunnel()
    }

    abstract fun moveToEnterGhostHouse()

    private fun moveToLeaveGhostHouse() {
        if (position.y == 14.5 * TILE_SIZE) {
            if (position.x < 14.0 * TILE_SIZE) {
                currentDirection = Direction.RIGHT
                move(currentDirection)
                return
            } else if (position.x > 14.0 * TILE_SIZE) {
                currentDirection = Direction.LEFT
                move(currentDirection)
                return
            }
        }

        currentDirection = Direction.UP
        move(currentDirection)

        if (position.y == 11.5 * TILE_SIZE) {
            if (switchedModesWhileInGhostHouse) {
                currentDirection = Direction.RIGHT
                nextDirection = Direction.RIGHT
                directionFromNextTile = Direction.RIGHT
            } else {
                currentDirection = Direction.LEFT
                nextDirection = Direction.LEFT
                directionFromNextTile = Direction.LEFT
            }
            isLeavingGhostHouse = false
        }
    }

    private fun move(game: Game) {
        if (game.maze.willNotPassTileCentre(position, currentDirection, SCALING_FACTOR.toDouble())) {
            move(currentDirection)
            if (game.maze.inCentreOfTileAlongDirection(position, currentDirection)) {
                currentDirection = nextDirection
                nextDirection = directionFromNextTile
            }
            return
        }

        if (game.maze.stillWithinSameTile(position, currentDirection, SCALING_FACTOR.toDouble())) {
            move(currentDirection)
            return
        }

        // Entering a new tile
        val previousTile = game.maze.tileAt(position)
        move(currentDirection)

        if (shouldReverse) {
            directionFromNextTile = pickNextDirection(game, previousTile, currentDirection)
            currentDirection = currentDirection.reverse()
            nextDirection = directionFromNextTile
            shouldReverse = false
            return
        }

        val nextTile = game.maze.tileInDirection(position, directionFromNextTile)
        directionFromNextTile = pickNextDirection(game, nextTile, nextDirection.reverse())
    }

    private fun pickNextDirection(game: Game, nextTile: Tile, backwards: Direction): Direction {
        val adjacentTiles = getAdjacentTiles(game.maze, nextTile, backwards)
        return if (adjacentTiles.size == 1) {
            adjacentTiles.keys.first()
        } else if (isScared) {
            adjacentTiles.keys.random()
        } else {
            val target = if (isEyes) {
                Position(14.0 * TILE_SIZE, 11.5 * TILE_SIZE)
            } else {
                getTarget(game)
            }
            adjacentTiles.minBy { it.value.position.distanceTo(target) }.key
        }
    }

    private fun getAdjacentTiles(maze: Maze, tile: Tile, backwards: Direction): Map<Direction, Tile> {
        val tiles = maze.neighbours(tile).filter { it.value.navigable }.minus(backwards)
        if (!isScared && !isEyes) {
            if (tile == maze.tiles[11][12] ||
                tile == maze.tiles[11][15] ||
                tile == maze.tiles[23][12] ||
                tile == maze.tiles[23][15]
            ) {
                return tiles.minus(Direction.UP)
            }
        }
        return tiles
    }

    private fun teleportIfAtEndOfTunnel() {
        if (position.x < 0.0) {
            position = position.copy(x = 28.0 * TILE_SIZE - SCALING_FACTOR.toDouble())
        } else if (position.x > 28.0 * TILE_SIZE - SCALING_FACTOR.toDouble()) {
            position = position.copy(x = 0.0)
        }
    }

    private fun inTunnel(maze: Maze): Boolean {
        val currentTile = maze.tileAt(position)
        return currentTile is EmptyTile && currentTile.isTunnel
    }

    abstract fun copy(): Ghost

    companion object : KLogging() {
        val eyesUpImage = Image("$SPRITES_BASE_PATH/ghosts/eyes/eyesUp.png")
        val eyesDownImage = Image("$SPRITES_BASE_PATH/ghosts/eyes/eyesDown.png")
        val eyesLeftImage = Image("$SPRITES_BASE_PATH/ghosts/eyes/eyesLeft.png")
        val eyesRightImage = Image("$SPRITES_BASE_PATH/ghosts/eyes/eyesRight.png")
        val scaredBlueImage = Image("$SPRITES_BASE_PATH/ghosts/scaredBlue.png")
        val scaredWhiteImage = Image("$SPRITES_BASE_PATH/ghosts/scaredWhite.png")
    }
}
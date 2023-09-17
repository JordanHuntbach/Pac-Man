package com.jordan.pacman.game

import com.jordan.pacman.Globals.SCALING_FACTOR
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.tiles.EmptyTile
import com.jordan.pacman.game.tiles.Pill
import com.jordan.pacman.game.tiles.PowerPill
import com.jordan.pacman.game.tiles.Tile
import com.jordan.pacman.game.tiles.Wall

/**
 * Note that tiles are 8 x 8 pixels, so the 'centre' is considered as (3,4).
 */
class Maze {

    private val ET = fun(position: Position) = EmptyTile(position)
    private val TU = fun(position: Position) = EmptyTile(position, isTunnel = true)
    private val PI = fun(position: Position) = Pill(position)
    private val PP = fun(position: Position) = PowerPill(position)
    private val HT = fun(position: Position) = Wall(position, Wall.wallHorizontalTop)
    private val HB = fun(position: Position) = Wall(position, Wall.wallHorizontalBottom)
    private val VL = fun(position: Position) = Wall(position, Wall.wallVerticalLeft)
    private val VR = fun(position: Position) = Wall(position, Wall.wallVerticalRight)
    private val TR = fun(position: Position) = Wall(position, Wall.cornerTopRight)
    private val BR = fun(position: Position) = Wall(position, Wall.cornerBottomRight)
    private val TL = fun(position: Position) = Wall(position, Wall.cornerTopLeft)
    private val BL = fun(position: Position) = Wall(position, Wall.cornerBottomLeft)
    private val DR = fun(position: Position) = Wall(position, Wall.door)

    // 28 x 31
    private val data = arrayOf(
        arrayOf(BR,HB,HB,HB,HB,HB,HB,HB,HB,HB,HB,HB,HB,BL,BR,HB,HB,HB,HB,HB,HB,HB,HB,HB,HB,HB,HB,BL),
        arrayOf(VR,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,VL),
        arrayOf(VR,PI,BR,HB,HB,BL,PI,BR,HB,HB,HB,BL,PI,VR,VL,PI,BR,HB,HB,HB,BL,PI,BR,HB,HB,BL,PI,VL),
        arrayOf(VR,PP,VR,ET,ET,VL,PI,VR,ET,ET,ET,VL,PI,VR,VL,PI,VR,ET,ET,ET,VL,PI,VR,ET,ET,VL,PP,VL),
        arrayOf(VR,PI,TR,HT,HT,TL,PI,TR,HT,HT,HT,TL,PI,TR,TL,PI,TR,HT,HT,HT,TL,PI,TR,HT,HT,TL,PI,VL),
        arrayOf(VR,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,VL),
        arrayOf(VR,PI,BR,HB,HB,BL,PI,BR,BL,PI,BR,HB,HB,HB,HB,HB,HB,BL,PI,BR,BL,PI,BR,HB,HB,BL,PI,VL),
        arrayOf(VR,PI,TR,HT,HT,TL,PI,VR,VL,PI,TR,HT,HT,BL,BR,HT,HT,TL,PI,VR,VL,PI,TR,HT,HT,TL,PI,VL),
        arrayOf(VR,PI,PI,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,PI,PI,VL),
        arrayOf(TR,HT,HT,HT,HT,BL,PI,VR,TR,HB,HB,BL,ET,VR,VL,ET,BR,HB,HB,TL,VL,PI,BR,HT,HT,HT,HT,TL),
        arrayOf(ET,ET,ET,ET,ET,VL,PI,VR,BR,HT,HT,TL,ET,TR,TL,ET,TR,HT,HT,BL,VL,PI,VR,ET,ET,ET,ET,ET),
        arrayOf(ET,ET,ET,ET,ET,VL,PI,VR,VL,ET,ET,ET,ET,ET,ET,ET,ET,ET,ET,VR,VL,PI,VR,ET,ET,ET,ET,ET),
        arrayOf(ET,ET,ET,ET,ET,VL,PI,VR,VL,ET,BR,HB,HB,DR,DR,HB,HB,BL,ET,VR,VL,PI,VR,ET,ET,ET,ET,ET),
        arrayOf(HT,HT,HT,HT,HT,TL,PI,TR,TL,ET,VR,ET,ET,ET,ET,ET,ET,VL,ET,TR,TL,PI,TR,HT,HT,HT,HT,HT),
        arrayOf(TU,TU,TU,TU,TU,TU,PI,ET,ET,ET,VR,ET,ET,ET,ET,ET,ET,VL,ET,ET,ET,PI,TU,TU,TU,TU,TU,TU),
        arrayOf(HB,HB,HB,HB,HB,BL,PI,BR,BL,ET,VR,ET,ET,ET,ET,ET,ET,VL,ET,BR,BL,PI,BR,HB,HB,HB,HB,HB),
        arrayOf(ET,ET,ET,ET,ET,VL,PI,VR,VL,ET,TR,HT,HT,HT,HT,HT,HT,TL,ET,VR,VL,PI,VR,ET,ET,ET,ET,ET),
        arrayOf(ET,ET,ET,ET,ET,VL,PI,VR,VL,ET,ET,ET,ET,ET,ET,ET,ET,ET,ET,VR,VL,PI,VR,ET,ET,ET,ET,ET),
        arrayOf(ET,ET,ET,ET,ET,VL,PI,VR,VL,ET,BR,HB,HB,HB,HB,HB,HB,BL,ET,VR,VL,PI,VR,ET,ET,ET,ET,ET),
        arrayOf(BR,HT,HT,HT,HT,TL,PI,TR,TL,ET,TR,HT,HT,BL,BR,HT,HT,TL,ET,TR,TL,PI,TR,HT,HT,HT,HT,BL),
        arrayOf(VR,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,VL),
        arrayOf(VR,PI,BR,HB,HB,BL,PI,BR,HB,HB,HB,BL,PI,VR,VL,PI,BR,HB,HB,HB,BL,PI,BR,HB,HB,BL,PI,VL),
        arrayOf(VR,PI,TR,HT,BL,VL,PI,TR,HT,HT,HT,TL,PI,TR,TL,PI,TR,HT,HT,HT,TL,PI,VR,BR,HT,TL,PI,VL),
        arrayOf(VR,PP,PI,PI,VR,VL,PI,PI,PI,PI,PI,PI,PI,ET,ET,PI,PI,PI,PI,PI,PI,PI,VR,VL,PI,PI,PP,VL),
        arrayOf(TR,HB,BL,PI,VR,VL,PI,BR,BL,PI,BR,HB,HB,HB,HB,HB,HB,BL,PI,BR,BL,PI,VR,VL,PI,BR,HB,TL),
        arrayOf(BR,HT,TL,PI,TR,TL,PI,VR,VL,PI,TR,HT,HT,BL,BR,HT,HT,TL,PI,VR,VL,PI,TR,TL,PI,TR,HT,BL),
        arrayOf(VR,PI,PI,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,VR,VL,PI,PI,PI,PI,PI,PI,VL),
        arrayOf(VR,PI,BR,HB,HB,HB,HB,TL,TR,HB,HB,BL,PI,VR,VL,PI,BR,HB,HB,TL,TR,HB,HB,HB,HB,BL,PI,VL),
        arrayOf(VR,PI,TR,HT,HT,HT,HT,HT,HT,HT,HT,TL,PI,TR,TL,PI,TR,HT,HT,HT,HT,HT,HT,HT,HT,TL,PI,VL),
        arrayOf(VR,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,PI,VL),
        arrayOf(TR,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,HT,TL)
    )

    val tiles: Array<Array<Tile>> = data.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, item ->
            item(Position(columnIndex * TILE_SIZE,rowIndex * TILE_SIZE))
        }.toTypedArray()
    }.toTypedArray()
    val flatTiles = tiles.flatten()
    val pills = flatTiles.filter { it is Pill || it is PowerPill }

    fun reset() {
        pills.forEach { it.isActive = true }
    }

    fun allDotsEaten() = pills.all { !it.isActive }

    fun dotsEaten() = pills.count { !it.isActive }

    fun remainingDots() = pills.count { it.isActive }

    fun stillWithinSameTile(position: Position, direction: Direction, distance: Double): Boolean {
        val xWithinTile = position.x.toInt() % TILE_SIZE
        val yWithinTile = position.y.toInt() % TILE_SIZE
        return when (direction) {
            Direction.UP -> yWithinTile - distance >= 0
            Direction.DOWN -> yWithinTile + distance < TILE_SIZE
            Direction.LEFT -> xWithinTile - distance >= 0
            Direction.RIGHT -> xWithinTile + distance < TILE_SIZE
        }
    }

    fun tileAt(position: Position): Tile {
        val tileColumn = position.x.toInt() / TILE_SIZE
        val tileRow = position.y.toInt() / TILE_SIZE
        return tiles[tileRow][tileColumn]
    }

    fun inCentreOfTileAlongDirection(position: Position, direction: Direction): Boolean {
        return when (direction) {
            Direction.UP,Direction.DOWN -> position.y.toInt() % TILE_SIZE / SCALING_FACTOR == 4
            Direction.LEFT,Direction.RIGHT -> position.x.toInt() % TILE_SIZE / SCALING_FACTOR == 3
        }
    }

    fun directionToCentreOfTileAlongDirection(position: Position, direction: Direction?): Direction? {
        return when (direction) {
            Direction.UP,Direction.DOWN -> {
                if (position.x.toInt() % TILE_SIZE / SCALING_FACTOR < 3) {
                    Direction.RIGHT
                } else if (position.x.toInt() % TILE_SIZE / SCALING_FACTOR > 3) {
                    Direction.LEFT
                } else {
                    null
                }
            }

            Direction.LEFT,Direction.RIGHT -> {
                if (position.y.toInt() % TILE_SIZE / SCALING_FACTOR < 4) {
                    Direction.DOWN
                } else if (position.y.toInt() % TILE_SIZE / SCALING_FACTOR > 4) {
                    Direction.UP
                } else {
                    null
                }
            }

            else -> null
        }
    }

    fun tileInDirection(position: Position, direction: Direction): Tile {
        var tileColumn = position.x.toInt() / TILE_SIZE
        var tileRow = position.y.toInt() / TILE_SIZE
        when (direction) {
            Direction.UP -> tileRow--
            Direction.DOWN -> tileRow++
            Direction.LEFT -> tileColumn--
            Direction.RIGHT -> tileColumn++
        }

        if (tileColumn == -1) {
            tileColumn = tiles[0].size - 1
        } else if (tileColumn == tiles[0].size) {
            tileColumn = 0
        }

        return tiles[tileRow][tileColumn]
    }

    fun willNotPassTileCentre(position: Position, direction: Direction, distance: Double): Boolean {
        val xWithinTile = position.x.toInt() % TILE_SIZE
        val yWithinTile = position.y.toInt() % TILE_SIZE
        return when (direction) {
            Direction.UP -> (yWithinTile - distance) / SCALING_FACTOR >= 4
            Direction.DOWN -> (yWithinTile + distance) / SCALING_FACTOR <= 4
            Direction.LEFT -> (xWithinTile - distance) / SCALING_FACTOR >= 3
            Direction.RIGHT -> (xWithinTile + distance) / SCALING_FACTOR <= 3
        }
    }

    fun neighbours(tile: Tile): Map<Direction, Tile> {
        return mapOf(
            Direction.UP to tileInDirection(tile.position, Direction.UP),
            Direction.LEFT to tileInDirection(tile.position, Direction.LEFT),
            Direction.DOWN to tileInDirection(tile.position, Direction.DOWN),
            Direction.RIGHT to tileInDirection(tile.position, Direction.RIGHT)
        )
    }
}

package com.jordan.pacman.game

import com.jordan.pacman.Globals.SCALING_FACTOR
import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.tiles.Pill
import com.jordan.pacman.game.tiles.PowerPill
import com.jordan.pacman.game.tiles.Tile

/**
 * Note that tiles are 8 x 8 pixels, so the 'centre' is considered as (3,4).
 */
class Maze(
    val tiles: Array<Array<Tile>> = standardMaze
) {

    private val pills = tiles.flatten().filter { it is Pill || it is PowerPill }

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
            Direction.UP, Direction.DOWN -> position.y.toInt() % TILE_SIZE / SCALING_FACTOR == 4
            Direction.LEFT, Direction.RIGHT -> position.x.toInt() % TILE_SIZE / SCALING_FACTOR == 3
        }
    }

    fun directionToCentreOfTileAlongDirection(position: Position, direction: Direction?): Direction? {
        return when (direction) {
            Direction.UP, Direction.DOWN -> {
                if (position.x.toInt() % TILE_SIZE / SCALING_FACTOR < 3) {
                    Direction.RIGHT
                } else if (position.x.toInt() % TILE_SIZE / SCALING_FACTOR > 3) {
                    Direction.LEFT
                } else {
                    null
                }
            }

            Direction.LEFT, Direction.RIGHT -> {
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

    fun copy(): Maze {
        return Maze(tiles.map { row -> row.map { it.copy() }.toTypedArray() }.toTypedArray())
    }
}

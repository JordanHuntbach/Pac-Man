package com.jordan.pacman.game.tiles

import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Wall(
    override val position: Position,
    override val image: Image,
) : Tile {
    override val navigable = false
    override var isActive = true

    companion object {
        val cornerBottomLeft = Image("$SPRITES_BASE_PATH/walls/corner-bottom-left.png")
        val cornerBottomRight = Image("$SPRITES_BASE_PATH/walls/corner-bottom-right.png")
        val cornerTopLeft = Image("$SPRITES_BASE_PATH/walls/corner-top-left.png")
        val cornerTopRight = Image("$SPRITES_BASE_PATH/walls/corner-top-right.png")
        val door = Image("$SPRITES_BASE_PATH/walls/door.png")
        val wallHorizontalBottom = Image("$SPRITES_BASE_PATH/walls/wall-horizontal-bottom.png")
        val wallHorizontalTop = Image("$SPRITES_BASE_PATH/walls/wall-horizontal-top.png")
        val wallVerticalLeft = Image("$SPRITES_BASE_PATH/walls/wall-vertical-left.png")
        val wallVerticalRight = Image("$SPRITES_BASE_PATH/walls/wall-vertical-right.png")
    }
}
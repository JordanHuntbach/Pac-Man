package com.jordan.pacman.game.tiles

import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class Pill(
    override val position: Position,
) : Tile {

    override val image = Pill.image
    override val navigable = true

    companion object {
        val image = Image("$SPRITES_BASE_PATH/pickups/pill.png")
    }
}
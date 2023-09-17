package com.jordan.pacman.game.tiles

import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class PowerPill(
    override val position: Position
) : Tile {

    override val image = PowerPill.image
    override val navigable = true
    override var isActive = true

    companion object {
        val image = Image("$SPRITES_BASE_PATH/pickups/powerpill.png")
    }
}
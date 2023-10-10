package com.jordan.pacman.game.tiles

import com.jordan.pacman.game.Position
import javafx.scene.image.Image

class EmptyTile(
    override val position: Position,
    override var isActive: Boolean = true,
    val isTunnel: Boolean = false
) : Tile {

    override val image: Image? = null
    override val navigable = true

    override fun copy(): EmptyTile {
        return EmptyTile(position.copy(), isActive, isTunnel)
    }
}
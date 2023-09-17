package com.jordan.pacman.game.tiles

import com.jordan.pacman.game.Position
import javafx.scene.image.Image

interface Tile {
    val position: Position
    val image: Image?
    val navigable: Boolean
    var isActive: Boolean
}
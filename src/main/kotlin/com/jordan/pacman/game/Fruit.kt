package com.jordan.pacman.game

import com.jordan.pacman.Globals.SPRITES_BASE_PATH
import com.jordan.pacman.Globals.TILE_SIZE
import javafx.scene.image.Image
import mu.KLogging
import java.time.Instant
import kotlin.random.Random.Default.nextLong

class Fruit {

    val position = Position(14.0 * TILE_SIZE, 17.5 * TILE_SIZE)
    var image = cherry

    var isActive = false

    private var despawnTime: Instant? = null

    fun spawn() {
        val activeTimeMillis = nextLong(9_000, 10_000)
        logger.debug { "Spawning fruit for ${activeTimeMillis}ms" }

        isActive = true
        despawnTime = Instant.now().plusMillis(activeTimeMillis)
    }

    fun shouldDespawn(): Boolean {
        return Instant.now().isAfter(despawnTime)
    }

    fun reset(level: Level) {
        isActive = false
        despawnTime = null
        image = level.fruit
    }

    companion object : KLogging() {
        val cherry = Image("$SPRITES_BASE_PATH/fruit/cherry.png")
        val strawberry = Image("$SPRITES_BASE_PATH/fruit/strawberry.png")
        val peach = Image("$SPRITES_BASE_PATH/fruit/peach.png")
        val apple = Image("$SPRITES_BASE_PATH/fruit/apple.png")
        val grape = Image("$SPRITES_BASE_PATH/fruit/grape.png")
        val galaxian = Image("$SPRITES_BASE_PATH/fruit/galaxian.png")
        val bell = Image("$SPRITES_BASE_PATH/fruit/bell.png")
        val key = Image("$SPRITES_BASE_PATH/fruit/key.png")
    }
}
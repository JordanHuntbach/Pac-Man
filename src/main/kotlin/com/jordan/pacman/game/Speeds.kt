package com.jordan.pacman.game

/**
 * This data structure represents the different speeds that Pac-Man and the ghosts can travel at.
 * The ints represent how many movements the entity is able to move in a given frame.
 * At 50% speed, the entity will move every other frame.
 * At 80% speed, the entity will move 4 out of 5 frames.
 * At 100% speed, the entity will move once every frame.
 * At 105% speed, the entity will move twice every 1 in 20 frames.
 * The skip frames are distributed throughout the cycle for smoother movement.
 */
object Speeds {
    val `40` = intArrayOf(1, 0, 1, 0, 0)
    val `45` = intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0)
    val `50` = intArrayOf(1, 0)
    val `55` = intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0)
    val `60` = intArrayOf(1, 0, 1, 1, 0)
    val `75` = intArrayOf(1, 1, 1, 0)
    val `80` = intArrayOf(1, 1, 1, 1, 0)
    val `85` = intArrayOf(1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0)
    val `90` = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 0)
    val `95` = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0)
    val `100` = intArrayOf(1)
    val `105` = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2)
}
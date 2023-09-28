package com.jordan.pacman.game

/**
 * This data structure represents the different speeds that Pac-Man and the ghosts can travel at.
 * The ints represent how many movements the entity is able to move in a given frame.
 * At 50% speed, the entity will move every other frame.
 * At 80% speed, the entity will move 4 out of 5 frames.
 * At 100% speed, the entity will move once every frame.
 * At 105% speed, the entity will move twice every 1 in 20 frames.
 * The skip frames are distributed throughout the cycle for smoother movement.
 * The sum of frames divided by the number of entries should equal the speed percent.
 */
object Speeds {
    val `40` = intArrayOf(1, 0, 1, 0, 0) // 2 / 5
    val `45` = intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0) // 9 / 20
    val `50` = intArrayOf(1, 0) // 1 / 2
    val `55` = intArrayOf(1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0) // 11 / 20
    val `60` = intArrayOf(1, 0, 1, 1, 0) // 3 / 5
    val `75` = intArrayOf(1, 1, 1, 0) // 3 / 4
    val `80` = intArrayOf(1, 1, 1, 1, 0) // 4 / 5
    val `85` = intArrayOf(1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0) // 17 / 20
    val `90` = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 0) // 9 / 10
    val `95` = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0) // 19 / 20
    val `100` = intArrayOf(1) // 1 / 1
    val `105` = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2) // 21 / 20
    val `150` = intArrayOf(1, 2) // 3 / 2
}

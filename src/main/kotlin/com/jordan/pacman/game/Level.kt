package com.jordan.pacman.game

import javafx.scene.image.Image

data class Level(
    val pacmanNormalSpeed: IntArray,
    val pacmanFrighteningSpeed: IntArray,
    val ghostTunnelSpeed: IntArray,
    val ghostScaredSpeed: IntArray,
    val ghostNormalSpeed: IntArray,
    val ghostEyesSpeed: IntArray,
    val ghostHouseSpeed: IntArray,
    val scatterAndChaseTimes: IntArray,
    val scaredLimit: Int,
    val scaredFlashes: Int,
    val inkyDotLimit: Int,
    val clydeDotLimit: Int,
    val dotTimerLimit: Int,
    val cruiseElroyDotLimit: Int,
    val cruiseElroy2DotLimit: Int,
    val cruiseElroySpeed: IntArray,
    val cruiseElroy2Speed: IntArray,
    val fruit: Image,
    val fruitReward: Int,
)
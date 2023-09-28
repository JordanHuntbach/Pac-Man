package com.jordan.pacman.game

object Levels {
    private val levels = arrayOf(
        Level(
            // 1
            pacmanNormalSpeed = Speeds.`80`,
            pacmanFrighteningSpeed = Speeds.`90`,
            ghostTunnelSpeed = Speeds.`40`,
            ghostScaredSpeed = Speeds.`50`,
            ghostNormalSpeed = Speeds.`75`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(7 * 60, 20 * 60, 7 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, Int.MAX_VALUE),
            scaredLimit = 6 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 30,
            clydeDotLimit = 60,
            dotTimerLimit = 4 * 60,
            cruiseElroyDotLimit = 20,
            cruiseElroy2DotLimit = 10,
            cruiseElroySpeed = Speeds.`80`,
            cruiseElroy2Speed = Speeds.`85`,
            fruit = Fruit.cherry,
            fruitReward = 100,
        ),
        Level(
            // 2
            pacmanNormalSpeed = Speeds.`90`,
            pacmanFrighteningSpeed = Speeds.`95`,
            ghostTunnelSpeed = Speeds.`45`,
            ghostScaredSpeed = Speeds.`55`,
            ghostNormalSpeed = Speeds.`85`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(7 * 60, 20 * 60, 7 * 60, 20 * 60, 5 * 60, 1033 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 5 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 50,
            dotTimerLimit = 4 * 60,
            cruiseElroyDotLimit = 30,
            cruiseElroy2DotLimit = 15,
            cruiseElroySpeed = Speeds.`90`,
            cruiseElroy2Speed = Speeds.`95`,
            fruit = Fruit.strawberry,
            fruitReward = 300,
        ),
        Level(
            // 3
            pacmanNormalSpeed = Speeds.`90`,
            pacmanFrighteningSpeed = Speeds.`95`,
            ghostTunnelSpeed = Speeds.`45`,
            ghostScaredSpeed = Speeds.`55`,
            ghostNormalSpeed = Speeds.`85`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(7 * 60, 20 * 60, 7 * 60, 20 * 60, 5 * 60, 1033 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 4 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 4 * 60,
            cruiseElroyDotLimit = 40,
            cruiseElroy2DotLimit = 20,
            cruiseElroySpeed = Speeds.`90`,
            cruiseElroy2Speed = Speeds.`95`,
            fruit = Fruit.peach,
            fruitReward = 500,
        ),
        Level(
            // 4
            pacmanNormalSpeed = Speeds.`90`,
            pacmanFrighteningSpeed = Speeds.`95`,
            ghostTunnelSpeed = Speeds.`45`,
            ghostScaredSpeed = Speeds.`55`,
            ghostNormalSpeed = Speeds.`85`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(7 * 60, 20 * 60, 7 * 60, 20 * 60, 5 * 60, 1033 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 3 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 4 * 60,
            cruiseElroyDotLimit = 40,
            cruiseElroy2DotLimit = 20,
            cruiseElroySpeed = Speeds.`90`,
            cruiseElroy2Speed = Speeds.`95`,
            fruit = Fruit.peach,
            fruitReward = 500,
        ),
        Level(
            // 5
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 2 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 40,
            cruiseElroy2DotLimit = 20,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.apple,
            fruitReward = 50000,
        ),
        Level(
            // 6
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 5 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 50,
            cruiseElroy2DotLimit = 25,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.apple,
            fruitReward = 50000,
        ),
        Level(
            // 7
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 2 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 50,
            cruiseElroy2DotLimit = 25,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.grape,
            fruitReward = 1000,
        ),
        Level(
            // 8
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 2 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 50,
            cruiseElroy2DotLimit = 25,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.grape,
            fruitReward = 1000,
        ),
        Level(
            // 9
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 1 * 60,
            scaredFlashes = 3,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 60,
            cruiseElroy2DotLimit = 30,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.galaxian,
            fruitReward = 2000,
        ),
        Level(
            // 10
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 5 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 60,
            cruiseElroy2DotLimit = 30,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.galaxian,
            fruitReward = 2000,
        ),
        Level(
            // 11
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 2 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 60,
            cruiseElroy2DotLimit = 30,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.bell,
            fruitReward = 3000,
        ),
        Level(
            // 12
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 1 * 60,
            scaredFlashes = 3,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 80,
            cruiseElroy2DotLimit = 40,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.bell,
            fruitReward = 3000,
        ),
        Level(
            // 13
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 1 * 60,
            scaredFlashes = 3,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 80,
            cruiseElroy2DotLimit = 40,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 14
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 3 * 60,
            scaredFlashes = 5,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 80,
            cruiseElroy2DotLimit = 40,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 15
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 1 * 60,
            scaredFlashes = 3,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 100,
            cruiseElroy2DotLimit = 50,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 16
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 1 * 60,
            scaredFlashes = 3,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 100,
            cruiseElroy2DotLimit = 50,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 17
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 0,
            scaredFlashes = 0,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 100,
            cruiseElroy2DotLimit = 50,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 18
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 0,
            scaredFlashes = 0,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 100,
            cruiseElroy2DotLimit = 50,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 19
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 0,
            scaredFlashes = 0,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 120,
            cruiseElroy2DotLimit = 60,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 20
            pacmanNormalSpeed = Speeds.`100`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 0,
            scaredFlashes = 0,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 120,
            cruiseElroy2DotLimit = 60,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        ),
        Level(
            // 21
            pacmanNormalSpeed = Speeds.`90`,
            pacmanFrighteningSpeed = Speeds.`100`,
            ghostTunnelSpeed = Speeds.`50`,
            ghostScaredSpeed = Speeds.`60`,
            ghostNormalSpeed = Speeds.`95`,
            ghostEyesSpeed = Speeds.`150`,
            ghostHouseSpeed = Speeds.`50`,
            scatterAndChaseTimes = intArrayOf(5 * 60, 20 * 60, 5 * 60, 20 * 60, 5 * 60, 1037 * 60, 1, Int.MAX_VALUE),
            scaredLimit = 0,
            scaredFlashes = 0,
            inkyDotLimit = 0,
            clydeDotLimit = 0,
            dotTimerLimit = 3 * 60,
            cruiseElroyDotLimit = 120,
            cruiseElroy2DotLimit = 60,
            cruiseElroySpeed = Speeds.`100`,
            cruiseElroy2Speed = Speeds.`105`,
            fruit = Fruit.key,
            fruitReward = 5000,
        )
    )

    operator fun get(i: Int): Level {
        return levels[if (i < levels.size) i else levels.size - 1]
    }
}
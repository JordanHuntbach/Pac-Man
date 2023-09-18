package com.jordan.pacman.game

import com.jordan.pacman.game.ghosts.Blinky
import com.jordan.pacman.game.ghosts.Clyde
import com.jordan.pacman.game.ghosts.Inky
import com.jordan.pacman.game.ghosts.Pinky
import com.jordan.pacman.game.tiles.Pill
import com.jordan.pacman.game.tiles.PowerPill
import com.jordan.pacman.game.tiles.Tile
import com.jordan.pacman.ui.GameScene
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import mu.KLogging
import java.time.Instant
import java.time.temporal.ChronoUnit.MILLIS
import kotlin.coroutines.CoroutineContext
import kotlin.math.pow

class Game(
    private val gameScene: GameScene?,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    var paused = true
    private var pausedFrameCounter = 120

    var score = 0
    var lives = 3
    var levelCounter = 0
    var currentLevel = Levels[levelCounter]

    val maze = Maze()

    val pacman = PacMan()

    val blinky = Blinky()
    val pinky = Pinky()
    val inky = Inky()
    val clyde = Clyde()
    val ghosts = listOf(blinky, pinky, inky, clyde)

    val fruit = Fruit()

    private var ghostsEatenWithOneEnergizer = 0

    private var ghostMode = 0
    private var ghostModeTickCount = 0
    private var ghostModeTicks = currentLevel.scatterAndChaseTimes

    private var livesLostThisLevel = 0
    private var globalDotCounter = 0
    private var dotTimer = 0
    private var dotTimerLimit = 4 * 60

    fun start() {
        launch(newSingleThreadContext("Game Thread")) {
            gameScene?.registerDirectionCallback { pacman.nextDirection = it }

            while (lives > 0) {
                targeting(millisPerFrame = 16) {
                    if (pausedFrameCounter >= 0) {
                        pausedFrameCounter--
                    } else {
                        paused = false
                        gameLoop()
                    }
                    render()
                }
            }

            logger.info { "Game Over" }
        }
    }

    fun scatterMode() = ghostMode % 2 == 0

    private suspend fun targeting(millisPerFrame: Int, block: suspend () -> Unit) {
        val start = Instant.now()
        block()
        val time = start.until(Instant.now(), MILLIS)
        if (time < millisPerFrame) {
            delay(millisPerFrame - time)
        }
    }

    private fun gameLoop() {
        pacman.update(currentLevel, maze)

        val currentTile = maze.tileAt(pacman.position)

        val atePill = eatPill(currentTile) || eatPowerPill(currentTile)

        if (atePill && maze.allDotsEaten()) {
            logger.info { "Level $levelCounter complete" }
            currentLevel = Levels[++levelCounter]
            resetLevel()
        }

        releaseGhosts(atePill)

        ghosts.forEach { it.update(this) }

        handleGhostCollisions()

        handleGhostMode()

        handleCruiseElroy()

        handleFruit()
    }

    private fun handleFruit() {
        if (fruit.isActive) {
            if (fruit.shouldDespawn()) {
                fruit.isActive = false
                logger.debug { "Fruit despawned" }
            } else if (maze.tileAt(pacman.position) == maze.tileAt(fruit.position)) {
                fruit.isActive = false
                val reward = currentLevel.fruitReward
                logger.debug { "Fruit eaten for $reward points" }
                score += reward
            }
        } else {
            if (maze.dotsEaten() == 70 || maze.dotsEaten() == 170) {
                fruit.spawn()
            }
        }
    }

    private fun handleCruiseElroy() {
        if (livesLostThisLevel == 0 || clyde.hasBeenReleased) {
            if (maze.remainingDots() == currentLevel.cruiseElroy2DotLimit && blinky.state != Blinky.State.CRUISE_ELROY_2) {
                logger.debug { "Activating Cruise Elroy 2" }
                blinky.activateCruiseElroy2(currentLevel)
            } else if (maze.remainingDots() == currentLevel.cruiseElroyDotLimit && blinky.state != Blinky.State.CRUISE_ELROY) {
                logger.debug { "Activating Cruise Elroy" }
                blinky.activateCruiseElroy(currentLevel)
            }
        }
    }

    private fun releaseGhosts(atePill: Boolean) {
        if (blinky.isInGhostHouse) {
            logger.debug { "Releasing Blinky from ghost house" }
            blinky.leaveGhostHouse()
            return
        }

        if (atePill) {
            if (livesLostThisLevel == 0) {
                for (ghost in ghosts) {
                    if (ghost.isInGhostHouse) {
                        if (ghost.dotCounter++ >= ghost.dotLimit) {
                            logger.debug { "Releasing ${ghost.javaClass.simpleName} from ghost house - hit personal counter" }
                            ghost.leaveGhostHouse()
                        }
                        return
                    }
                }
            } else {
                if (globalDotCounter == 7 && pinky.isInGhostHouse) {
                    logger.debug { "Releasing Pinky from ghost house - hit global counter" }
                    pinky.leaveGhostHouse()
                } else if (globalDotCounter == 17 && inky.isInGhostHouse) {
                    logger.debug { "Releasing Inky from ghost house - hit global counter" }
                    inky.leaveGhostHouse()
                } else if (globalDotCounter == 32 && clyde.isInGhostHouse) {
                    logger.debug { "Releasing Clyde from ghost house - hit global counter" }
                    clyde.leaveGhostHouse()
                }
            }
        } else {
            if (++dotTimer >= dotTimerLimit) {
                for (ghost in ghosts) {
                    if (ghost.isInGhostHouse) {
                        logger.debug { "Releasing ${ghost.javaClass.simpleName} from ghost house - hit timer limit" }
                        ghost.leaveGhostHouse()
                        dotTimer = 0
                        return
                    }
                }
            }
        }
    }

    private fun eatPill(currentTile: Tile): Boolean {
        return if (currentTile is Pill && currentTile.isActive) {
            currentTile.isActive = false
            score += 10
            pacman.manualFramePauses = 1
            globalDotCounter++
            dotTimer = 0
            true
        } else {
            false
        }
    }

    private fun eatPowerPill(currentTile: Tile): Boolean {
        return if (currentTile is PowerPill && currentTile.isActive) {
            ghostsEatenWithOneEnergizer = 0
            currentTile.isActive = false
            score += 50
            pacman.manualFramePauses = 3
            pacman.energize()
            ghosts.forEach { if (!it.isInGhostHouse && !it.isEnteringGhostHouse) it.scare(maze) }
            globalDotCounter++
            dotTimer = 0
            true
        } else {
            false
        }
    }

    private fun handleGhostCollisions() {
        val pacmanTile = maze.tileAt(pacman.position)
        for (ghost in ghosts) {
            val ghostTile = maze.tileAt(ghost.position)
            if (pacmanTile != ghostTile) {
                continue
            }

            if (ghost.isScared) {
                val reward = 200 * 2.0.pow(ghostsEatenWithOneEnergizer++).toInt()
                logger.debug { "Pac-Man ate ${ghost.javaClass.simpleName} for $reward points" }
                score += reward
                ghost.eaten()
            } else if (!ghost.isEyes) {
                logger.debug { "Pac-Man eaten by ${ghost.javaClass.simpleName}" }
                pacman.reset(currentLevel)
                ghosts.forEach { it.reset(currentLevel) }
                lives--
                livesLostThisLevel++
                globalDotCounter = 0
                break
            }
        }
    }

    private fun handleGhostMode() {
        if (ghostModeTickCount++ > ghostModeTicks[ghostMode]) {
            logger.debug { "Switching ghost mode to ${if (scatterMode()) "scatter" else "chase" }" }
            ghostModeTickCount = 0
            ghostMode++
            ghosts.forEach { it.switchedGhostMode() }
        }
    }

    private fun resetLevel() {
        maze.reset()

        pacman.reset(currentLevel)

        ghosts.forEach {
            it.reset(currentLevel)
            it.dotCounter = 0
        }

        fruit.reset(currentLevel)

        ghostMode = 0
        ghostModeTickCount = 0
        ghostModeTicks = currentLevel.scatterAndChaseTimes

        livesLostThisLevel = 0
        globalDotCounter = 0
        dotTimer = 0
        dotTimerLimit = currentLevel.dotTimerLimit
    }

    private suspend fun render() {
        gameScene?.update(this)
    }

    companion object : KLogging()
}
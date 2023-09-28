package com.jordan.pacman.game

import com.jordan.pacman.game.ghosts.Blinky
import com.jordan.pacman.game.ghosts.Clyde
import com.jordan.pacman.game.ghosts.Ghost
import com.jordan.pacman.game.ghosts.Inky
import com.jordan.pacman.game.ghosts.Pinky
import com.jordan.pacman.game.tiles.Pill
import com.jordan.pacman.game.tiles.PowerPill
import com.jordan.pacman.game.tiles.Tile
import com.jordan.pacman.ui.GameOverScene
import com.jordan.pacman.ui.GameScene
import javafx.stage.Stage
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
    stage: Stage?,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    private val gameScene = stage?.let { GameScene(it) }
    private val gameOverScene = stage?.let { GameOverScene(it) }

    var preGame = true
    private var preGameFrameCounter = 120

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

    val bonusPoints = BonusPoints()

    private var ghostsEatenWithOneEnergizer = 0

    private var ghostMode = 0
    private var ghostModeTickCount = 0
    private var ghostModeTicks = currentLevel.scatterAndChaseTimes

    private var livesLostThisLevel = 0
    private var globalDotCounter = 0
    private var dotTimer = 0
    private var dotTimerLimit = 4 * 75

    private var pacmanPausedFrames = 0
    private var ghostsPausedFrames = 0
    private var ghostsJustEaten = mutableSetOf<Ghost>()

    fun start() {
        launch(newSingleThreadContext("Game Thread")) {
            gameScene?.apply {
                setStageScene()
                registerDirectionCallback { pacman.nextDirection = it }
            }

            while (lives > 0) {
                targeting(millisPerFrame = 12) {
                    if (preGameFrameCounter >= 0) {
                        preGameFrameCounter--
                    } else {
                        preGame = false
                        gameLoop()
                    }
                    render()
                }
            }

            gameOver()
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
        if (!pacmanIsPaused()) {
            pacman.update(currentLevel, maze)
        }

        val currentTile = maze.tileAt(pacman.position)

        val atePill = eatPill(currentTile) || eatPowerPill(currentTile)

        if (atePill) {
            if (maze.dotsEaten() == 70 || maze.dotsEaten() == 170) {
                fruit.spawn()
            }
            if (maze.allDotsEaten()) {
                logger.info { "Level $levelCounter complete" }
                currentLevel = Levels[++levelCounter]
                resetLevel()
            }
        }

        releaseGhosts(atePill)

        val ghostsArePaused = ghostsArePaused()
        ghosts.forEach { ghost ->
            if (!ghostsArePaused || (ghost.isEyes && ghost !in ghostsJustEaten)) {
                ghost.update(this)
            }
        }

        handleGhostCollisions()

        handleGhostMode()

        handleCruiseElroy()

        handleFruit()

        handleBonusPoints()
    }

    private fun pacmanIsPaused(): Boolean {
        return if (pacmanPausedFrames > 0) {
            pacmanPausedFrames--
            true
        } else {
            false
        }
    }

    private fun ghostsArePaused(): Boolean {
        return if (ghostsPausedFrames > 0) {
            if (--ghostsPausedFrames == 0) {
                ghostsJustEaten.clear()
            }
            true
        } else {
            false
        }
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
                bonusPoints.reward(fruit.position, reward)
            }
        }
    }

    private fun handleBonusPoints() {
        if (bonusPoints.ticks > 0) {
            bonusPoints.ticks--
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

        if (livesLostThisLevel == 0) {
            for (ghost in ghosts) {
                if (ghost.isInGhostHouse) {
                    if (atePill) {
                        ghost.dotCounter++
                    }
                    if (ghost.dotCounter >= ghost.dotLimit) {
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

        if (!atePill) {
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
            pacmanPausedFrames = 1
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
            pacmanPausedFrames = 3
            pacman.energize()
            ghosts.forEach { if (!it.isEyes) it.scare() }
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
            if (pacmanTile == maze.tileAt(ghost.position)) {
                if (ghost.isScared) {
                    eat(ghost)
                } else if (!ghost.isEyes) {
                    eatenBy(ghost)
                    break
                }
            }
        }
    }

    private fun eat(ghost: Ghost) {
        val reward = 200 * 2.0.pow(ghostsEatenWithOneEnergizer++).toInt()
        logger.debug { "Pac-Man ate ${ghost.javaClass.simpleName} for $reward points" }
        score += reward
        bonusPoints.reward(ghost.position, reward)
        pacmanPausedFrames = 60
        ghostsPausedFrames = 60
        ghost.eaten()
        ghostsJustEaten.add(ghost)
    }

    private fun eatenBy(ghost: Ghost) {
        logger.debug { "Pac-Man eaten by ${ghost.javaClass.simpleName}" }
        pacman.reset(currentLevel)
        ghosts.forEach { it.reset(currentLevel) }
        lives--
        livesLostThisLevel++
        globalDotCounter = 0
        pacmanPausedFrames = 90
        ghostsPausedFrames = 90
    }

    private fun handleGhostMode() {
        if (ghostModeTickCount++ > ghostModeTicks[ghostMode]) {
            logger.debug { "Switching ghost mode to ${if (scatterMode()) "scatter" else "chase"}" }
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

        bonusPoints.ticks = 0

        ghostMode = 0
        ghostModeTickCount = 0
        ghostModeTicks = currentLevel.scatterAndChaseTimes

        livesLostThisLevel = 0
        globalDotCounter = 0
        dotTimer = 0
        dotTimerLimit = currentLevel.dotTimerLimit

        pacmanPausedFrames = 90
        ghostsPausedFrames = 90
        ghostsJustEaten.clear()
    }

    private suspend fun render() {
        gameScene?.render(this)
    }

    private suspend fun gameOver() {
        logger.info { "Game Over\nFinal score: $score" }
        gameOverScene?.render(this)
        gameOverScene?.setStageScene()
    }

    companion object : KLogging()
}
package com.jordan.pacman.ui

import com.jordan.pacman.Globals.TILE_SIZE
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import com.jordan.pacman.game.Levels
import com.jordan.pacman.game.PacMan
import com.jordan.pacman.game.tiles.Pill
import com.jordan.pacman.game.tiles.PowerPill
import com.jordan.pacman.game.tiles.Tile
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.withContext

class GameScene(
    stage: Stage
) : SceneProvider(stage) {

    private val scoreText = Text("00").apply { styleClass.add("score-value") }
    private val scoreNode = VBox().apply {
        styleClass.add("score")
        children.addAll(
            Text("SCORE").apply { styleClass.add("score-label") },
            scoreText
        )
        properties["hbox-hgrow"] = Priority.ALWAYS
    }

    private val highScoreText = Text("00").apply { styleClass.add("high-score-value") }
    private val highScoreNode = VBox().apply {
        styleClass.add("high-score")
        children.addAll(
            Text("HIGH SCORE").apply { styleClass.add("high-score-label") },
            highScoreText
        )
        properties["hbox-hgrow"] = Priority.ALWAYS
    }

    private val readyText = Text("READY!").apply {
        styleClass.add("ready-text")
        translateX = 4.0
        translateY = 12.0
    }

    private val bonusPointsText = Text("200").apply {
        styleClass.add("bonus-points-text")
    }

    private val canvas = Canvas(28.0 * TILE_SIZE, 34.0 * TILE_SIZE)
    private val graphicsContext = canvas.getGraphicsContext2D()

    private val root = BorderPane().apply {
        top = HBox(scoreNode, highScoreNode)
        center = StackPane(canvas, readyText, bonusPointsText)
    }

    override val scene = Scene(root, 28.0 * TILE_SIZE, 35.0 * TILE_SIZE, Color.BLACK).apply {
        stylesheets.add("game.css")
    }

    private fun shouldDrawTile(tile: Tile, game: Game): Boolean {
        if (tile is Pill || tile is PowerPill) {
            return !game.maze.hashesOfDotsEaten.contains(tile.hashCode())
        }
        return true
    }

    suspend fun render(game: Game) = withContext(Dispatchers.JavaFx) {
        graphicsContext.fill = Color.BLACK
        graphicsContext.fillRect(0.0, 0.0, canvas.width, canvas.height)

        for (row in game.maze.tiles) {
            for (tile in row) {
                if (tile.image != null && shouldDrawTile(tile, game)) {
                    graphicsContext.drawImage(tile.image, tile.position.x, tile.position.y)
                }
            }
        }

        if (game.fruit.isActive) {
            val fruitImage = game.fruit.image
            graphicsContext.drawImage(
                fruitImage,
                game.fruit.position.x - fruitImage.width / 2,
                game.fruit.position.y - fruitImage.height / 2
            )
        }

        for (ghost in game.ghosts) {
            val ghostImage = ghost.getImage()
            graphicsContext.drawImage(
                ghostImage,
                ghost.position.x - ghostImage.width / 2,
                ghost.position.y - ghostImage.height / 2
            )
        }

        val pacmanImage = game.pacman.getImage()
        graphicsContext.drawImage(
            pacmanImage,
            game.pacman.position.x - pacmanImage.width / 2,
            game.pacman.position.y - pacmanImage.height / 2
        )

        for (i in 1..<game.lives) {
            graphicsContext.drawImage(PacMan.imageLeft, (2 * i) * TILE_SIZE.toDouble(), 31.0 * TILE_SIZE)
        }

        scoreText.text = game.score.toString().padStart(2, '0')
        highScoreText.text = game.score.toString().padStart(2, '0')

        (0..game.levelCounter).forEachIndexed { i, level ->
            val fruit = Levels[level].fruit
            graphicsContext.drawImage(fruit, (25 - (2 * i)) * TILE_SIZE.toDouble(), 31.0 * TILE_SIZE)
        }

        if (game.preGame) {
            readyText.text = "READY!"
        } else {
            readyText.text = ""
        }

        if (game.bonusPoints.isActive()) {
            bonusPointsText.text = game.bonusPoints.points.toString()

            val width = 28 * TILE_SIZE
            val height = 31 * TILE_SIZE

            val xCenter = width / 2
            val yCenter = height / 2

            bonusPointsText.translateX = game.bonusPoints.position.x - xCenter
            bonusPointsText.translateY = game.bonusPoints.position.y - yCenter - 1.5 * TILE_SIZE
        } else {
            bonusPointsText.text = ""
        }
    }

    fun registerDirectionCallback(callback: (Direction) -> Unit) {
        scene.onKeyPressed = EventHandler { e ->
            when (e.code) {
                KeyCode.LEFT -> callback(Direction.LEFT)
                KeyCode.RIGHT -> callback(Direction.RIGHT)
                KeyCode.UP -> callback(Direction.UP)
                KeyCode.DOWN -> callback(Direction.DOWN)
                else -> {}
            }
        }
    }
}

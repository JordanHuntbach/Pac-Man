package com.jordan.pacman.ui

import com.jordan.pacman.Globals
import com.jordan.pacman.game.Game
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import mu.KLogging

class GameOverScene(
    stage: Stage,
) : SceneProvider(stage) {

    private val scoreText = Text("Score: 00").apply { styleClass.add("score") }
    private val highScoreText = Text("High Score: 00").apply { styleClass.add("high-score") }

    private val root = VBox().apply {
        alignment = Pos.CENTER
        spacing = 25.0
        padding = Insets(25.0)

        children.addAll(
            Text("GAME OVER").apply { styleClass.add("title") },

            scoreText,

            highScoreText,

            Button().apply {
                setOnAction {
                    Game(stage, newSingleThreadContext("Game Thread")).start()
                }
                setButtonTextAlternatingCaseOnHover("new game")
            },

            Button().apply {
                setOnAction { Platform.exit() }
                setButtonTextAlternatingCaseOnHover("quit")
            }
        )
    }

    override val scene = Scene(root, 28.0 * Globals.TILE_SIZE, 35.0 * Globals.TILE_SIZE, Color.BLACK).apply {
        stylesheets.add("gameOver.css")
    }

    suspend fun render(game: Game) = withContext(Dispatchers.JavaFx) {
        scoreText.text = "Score: ${game.score}"
        highScoreText.text = "High Score: ${game.score}"
    }

    companion object : KLogging()
}

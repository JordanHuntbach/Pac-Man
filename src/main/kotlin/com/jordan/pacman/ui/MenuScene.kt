package com.jordan.pacman.ui

import com.jordan.pacman.game.Game
import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage
import mu.KLogging
import java.util.Locale

class MenuScene(
    stage: Stage,
    game: Game,
    gameScene: GameScene,
) : SceneProvider {

    private val mainMenu = VBox().apply {
        alignment = Pos.CENTER
        spacing = 10.0
        padding = Insets(25.0)

        children.addAll(
            Text("PAC-MAN").apply { styleClass.add("title") },

            ImageView(Image("menu.png", true)).apply {
                VBox.setMargin(this, Insets(100.0, 0.0, 50.0, 0.0))
            },

            Button().apply {
                setOnAction {
                    stage.scene = gameScene.scene
                    game.start()
                }
                setButtonTextAlternatingCaseOnHover("start game")
            },

            Button().apply {
                setOnAction { Platform.exit() }
                setButtonTextAlternatingCaseOnHover("quit")
            },

            Text("Created by Jordan Huntbach\n2023").apply {
                styleClass.add("info")
                VBox.setMargin(this, Insets(50.0, 0.0, 0.0, 0.0))
            }
        )
    }
    override val scene = Scene(mainMenu, 592.0, 720.0, Color.BLACK).apply { stylesheets.add("menu.css") }

    private fun Button.setButtonTextAlternatingCaseOnHover(text: String) {
        textProperty().bind(
            Bindings.`when`(hoverProperty())
                .then(text.lowercase(Locale.getDefault()))
                .otherwise(text.uppercase(Locale.getDefault()))
        )
    }

    companion object : KLogging()
}

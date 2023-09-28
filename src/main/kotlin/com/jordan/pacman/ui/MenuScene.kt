package com.jordan.pacman.ui

import com.jordan.pacman.Globals
import com.jordan.pacman.game.Game
import javafx.application.Platform
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
import kotlinx.coroutines.newSingleThreadContext
import mu.KLogging

class MenuScene(
    stage: Stage,
) : SceneProvider(stage) {

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
                    Game(stage, newSingleThreadContext("Game Thread")).start()
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

    override val scene = Scene(mainMenu, 28.0 * Globals.TILE_SIZE, 35.0 * Globals.TILE_SIZE, Color.BLACK).apply { stylesheets.add("menu.css") }

    companion object : KLogging()
}

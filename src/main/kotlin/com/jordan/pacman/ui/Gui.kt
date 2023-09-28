package com.jordan.pacman.ui

import javafx.application.Application
import javafx.scene.text.Font
import javafx.stage.Stage

class Gui : Application() {

    fun main(args: Array<String>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage) {
        Font.loadFont(this::class.java.getResourceAsStream("fonts/PacFont.ttf"), 16.0)
        Font.loadFont(this::class.java.getResourceAsStream("fonts/PressStart2P.ttf"), 16.0)
        primaryStage.title = "Pac-Man"
        val menuScene = MenuScene(primaryStage)
        primaryStage.scene = menuScene.scene
        primaryStage.show()
    }
}

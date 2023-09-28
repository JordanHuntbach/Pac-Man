package com.jordan.pacman.ui

import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.withContext

abstract class SceneProvider(
    private val stage: Stage
) {
    abstract val scene: Scene

    suspend fun setStageScene() = withContext(Dispatchers.JavaFx) {
        stage.scene = scene
    }
}
package com.jordan.pacman.ui

import javafx.beans.binding.Bindings
import javafx.scene.control.Button
import java.util.Locale

fun Button.setButtonTextAlternatingCaseOnHover(text: String) {
    textProperty().bind(
        Bindings.`when`(hoverProperty())
            .then(text.lowercase(Locale.getDefault()))
            .otherwise(text.uppercase(Locale.getDefault()))
    )
}

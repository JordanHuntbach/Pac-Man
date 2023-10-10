package com.jordan.pacman.ai.mcts

import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game

class Node(
    val gameState: Game,
    val parent: Node? = null,
    val instructionsToReachNode: List<Direction?>? = null,
    val isTerminal: Boolean = false,
) {

    var children: List<Node>? = null

    var visitCount = 0
    val scores = mutableListOf<Int>()

    fun update(score: Int) {
        visitCount++
        scores.add(score)
    }

}

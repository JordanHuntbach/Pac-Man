package com.jordan.pacman.ai.mcts.expansion

import com.jordan.pacman.ai.mcts.Node
import com.jordan.pacman.game.Direction

/**
 * Expands the node by adding a child for Pac-Man travelling in each possible direction to the next junction.
 * If Pac-Man is eaten on the way to the junction, a node is placed there.
 */
class JunctionBasedExpansionPolicy : ExpansionPolicy {
    override fun expand(parentNode: Node): List<Node> {
        val newNodes = mutableListOf<Node>()

        for (direction in Direction.entries) {
            if (!parentNode.gameState.pacman.canMove(parentNode.gameState.maze, direction)) continue

            val game = parentNode.gameState.deepCopy()
            val lives = game.lives
            val instructions = mutableListOf<Direction?>()
            do {
                instructions.add(direction)
                game.playSingleFrame(direction)
            } while (!game.pacman.isInCentreOfJunction(game.maze) && game.lives == lives)
            newNodes.add(
                Node(
                    gameState = game,
                    parent = parentNode,
                    instructionsToReachNode = instructions,
                    isTerminal = game.lives < lives
                )
            )
        }

        return newNodes
    }
}
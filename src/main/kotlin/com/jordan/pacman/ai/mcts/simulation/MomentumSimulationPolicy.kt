package com.jordan.pacman.ai.mcts.simulation

import com.jordan.pacman.ai.mcts.Node

/**
 * Similar to RandomPlaySelectionPolicy, but will never reverse direction.
 */
class MomentumSimulationPolicy : SimulationPolicy {

    override fun simulateAndReturnScore(node: Node): Int {
        val game = node.gameState.deepCopy()

        val lives = game.lives
        for (i in 0 until 100) {
            val validMoves = game.pacman.validMoves(game.maze).minus(game.pacman.currentDirection?.reverse())
            game.playSingleFrame(validMoves.random())
            if (game.lives < lives) return 1
        }

        return game.score
    }

}
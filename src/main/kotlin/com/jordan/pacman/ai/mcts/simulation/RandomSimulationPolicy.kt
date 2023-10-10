package com.jordan.pacman.ai.mcts.simulation

import com.jordan.pacman.ai.mcts.Node

/**
 * Make a random move every frame until either:
 *   * 100 frames have been played
 *   * pacman is eaten
 */
class RandomSimulationPolicy : SimulationPolicy {

    override fun simulateAndReturnScore(node: Node): Int {
        val game = node.gameState.deepCopy()

        val lives = game.lives
        for (i in 0 until 100) {
            val validMoves = game.pacman.validMoves(game.maze)
            game.playSingleFrame(validMoves.random())
            if (game.lives < lives) break
        }

        return game.score
    }

}
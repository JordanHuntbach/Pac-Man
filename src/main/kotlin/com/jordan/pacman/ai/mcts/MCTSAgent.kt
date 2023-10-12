package com.jordan.pacman.ai.mcts

import com.jordan.pacman.ai.mcts.evaluation.MaxScoreEvaluationPolicy
import com.jordan.pacman.ai.mcts.expansion.JunctionBasedExpansionPolicy
import com.jordan.pacman.ai.mcts.selection.UCTSelectionPolicy
import com.jordan.pacman.ai.mcts.simulation.MomentumSimulationPolicy
import com.jordan.pacman.game.Game
import com.jordan.pacman.targeting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mu.KLogging
import kotlin.coroutines.CoroutineContext

class MCTSAgent(
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    fun playGame(game: Game) {
        launch(coroutineContext) {
            game.initialise()
            game.preGame = false

            val selectionPolicy = UCTSelectionPolicy()
            val expansionPolicy = JunctionBasedExpansionPolicy()
            val simulationPolicy = MomentumSimulationPolicy()
            val evaluationPolicy = MaxScoreEvaluationPolicy()

            while (game.lives > 0) {
                val mcts = MCTS(game, selectionPolicy, expansionPolicy, simulationPolicy, evaluationPolicy)
                mcts.run(60)
                mcts.selectBestMoves().forEach { direction ->
                    targeting(millisPerFrame = 12) {
                        game.playSingleFrame(direction)
                        game.render()
                    }
                }
            }

            logger.info { "Scored ${game.score} points" }
        }
    }

    companion object : KLogging()
}
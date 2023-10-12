package com.jordan.pacman.ai.mcts.evaluation

import com.jordan.pacman.ai.mcts.Node
import com.jordan.pacman.maxByOrNullBreakingTiesRandomly

/**
 * Selects the node with the best average score, breaking ties at random.
 */
class AverageScoreEvaluationPolicy : EvaluationPolicy {
    override fun selectBestNode(nodes: List<Node>): Node? {
        return nodes.maxByOrNullBreakingTiesRandomly { it.scores.average() }
    }
}

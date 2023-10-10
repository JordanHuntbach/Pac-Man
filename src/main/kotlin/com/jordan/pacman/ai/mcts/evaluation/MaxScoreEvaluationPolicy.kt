package com.jordan.pacman.ai.mcts.evaluation

import com.jordan.pacman.ai.mcts.Node

class MaxScoreEvaluationPolicy : EvaluationPolicy {
    override fun selectBestNode(nodes: List<Node>): Node? {
        return nodes.maxByOrNull { it.scores.maxOrNull() ?: 0 }
    }
}

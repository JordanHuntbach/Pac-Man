package com.jordan.pacman.ai.mcts.evaluation

import com.jordan.pacman.ai.mcts.Node

class AverageScoreEvaluationPolicy : EvaluationPolicy {
    override fun selectBestNode(nodes: List<Node>): Node? {
        return nodes.maxByOrNull { it.scores.average() }
    }
}

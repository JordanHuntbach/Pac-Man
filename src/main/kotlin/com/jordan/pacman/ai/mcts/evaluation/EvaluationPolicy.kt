package com.jordan.pacman.ai.mcts.evaluation

import com.jordan.pacman.ai.mcts.Node

interface EvaluationPolicy {
    fun selectBestNode(nodes: List<Node>): Node?
}
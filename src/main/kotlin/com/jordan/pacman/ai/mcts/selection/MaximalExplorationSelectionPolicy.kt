package com.jordan.pacman.ai.mcts.selection

import com.jordan.pacman.ai.mcts.Node

/**
 * Returns nodes sorted by their visit count.
 */
class MaximalExplorationSelectionPolicy : SelectionPolicy {
    override fun select(parent: Node, nodes: Collection<Node>) = nodes.sortedBy { it.visitCount }
}
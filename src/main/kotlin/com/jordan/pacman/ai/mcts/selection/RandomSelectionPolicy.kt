package com.jordan.pacman.ai.mcts.selection

import com.jordan.pacman.ai.mcts.Node

/**
 * Returns nodes in a random order.
 */
class RandomSelectionPolicy : SelectionPolicy {
    override fun select(parent: Node, nodes: Collection<Node>) = nodes.sortedBy { Math.random() }
}
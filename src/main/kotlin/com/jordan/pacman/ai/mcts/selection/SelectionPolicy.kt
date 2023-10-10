package com.jordan.pacman.ai.mcts.selection

import com.jordan.pacman.ai.mcts.Node

interface SelectionPolicy {
    fun select(parent: Node, nodes: Collection<Node>): List<Node>
}
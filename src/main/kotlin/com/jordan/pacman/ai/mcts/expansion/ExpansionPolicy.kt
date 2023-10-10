package com.jordan.pacman.ai.mcts.expansion

import com.jordan.pacman.ai.mcts.Node

interface ExpansionPolicy {
    fun expand(parentNode: Node): List<Node>
}
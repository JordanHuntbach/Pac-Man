package com.jordan.pacman.ai.mcts.simulation

import com.jordan.pacman.ai.mcts.Node

interface SimulationPolicy {
    fun simulateAndReturnScore(node: Node): Int
}
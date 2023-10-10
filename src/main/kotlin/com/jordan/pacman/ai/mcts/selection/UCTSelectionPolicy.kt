package com.jordan.pacman.ai.mcts.selection

import com.jordan.pacman.ai.mcts.Node
import kotlin.math.ln
import kotlin.math.sqrt

/**
 * Returns nodes in a sorted by their UCT (Upper Confidence bound applied to Trees) value.
 * UCT = v_i + c * sqrt(ln(n_p) / n_i), where:
 * v_i = mean score of playouts from the child node
 * c = exploration coefficient
 * n_p = visit count of the parent node
 * n_i = visit count of the child node
 *
 * Ikehata, N. & Ito, T. (2011), Monte-carlo tree search in Ms. Pac-Man,
 * in ‘2011 IEEE Conference on Computational Intelligence and Games (CIG’11)’, pp. 39–46.
 */
class UCTSelectionPolicy(
    private val explorationCoefficient: Int = 10
) : SelectionPolicy {
    override fun select(parent: Node, nodes: Collection<Node>) = nodes.sortedBy { node ->
        if (node.visitCount == 0) {
            Double.MAX_VALUE
        } else {
            node.scores.average() + explorationCoefficient * sqrt(ln(parent.visitCount.toDouble()) / node.visitCount)
        }
    }
}
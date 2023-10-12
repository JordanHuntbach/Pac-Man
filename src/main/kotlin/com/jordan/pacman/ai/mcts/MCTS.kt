package com.jordan.pacman.ai.mcts

import com.jordan.pacman.ai.mcts.evaluation.EvaluationPolicy
import com.jordan.pacman.ai.mcts.expansion.ExpansionPolicy
import com.jordan.pacman.ai.mcts.selection.SelectionPolicy
import com.jordan.pacman.ai.mcts.simulation.SimulationPolicy
import com.jordan.pacman.game.Direction
import com.jordan.pacman.game.Game
import mu.KLogging

/**
 * 1. Selection:
 *  Starting at the root node, which represents the current game state, children are recursively chosen
 *  according to some selection policy. When a leaf node is reached that does not represent a terminal
 *  (game ending) state, it is selected for expansion.
 *
 * 2. Expansion:
 *  The selected leaf node is expanded. Children are added to the node, with each child representing a
 *  legal move from the game state encoded by the leaf.
 *
 * 3. Simulation:
 *  Starting from one of the child nodes expanded in the previous stage, a playout is run to completion;
 *  this means simulating game play until either the game ends, or some other termination criteria is met
 *  (such as a limit on the number of moves taken). In pure MCTS, moves are selected at random during this playout,
 *  however, some heuristic is often employed here to improve performance.
 *
 * 4. Backpropagation:
 *  Each node maintains a visit count, as well some measure of the success of playouts that follow from that node.
 *  The result of the simulated playout is propagated up the tree, updating these statistics.
 */
class MCTS(
    game: Game,
    private val selectionPolicy: SelectionPolicy,
    private val expansionPolicy: ExpansionPolicy,
    private val simulationPolicy: SimulationPolicy,
    private val evaluationPolicy: EvaluationPolicy,
) {

    private val rootNode = Node(game)

    fun run(rounds: Int) {
        logger.debug { "Running $rounds rounds of MCTS" }
        for (index in 0..<rounds) {
            var node = selection(rootNode) ?: break
            node = expansion(node)
            val score = simulation(node)
            backpropagation(node, score)
        }
        logger.debug { "Finished running $rounds rounds of MCTS" }
    }

    fun selectBestMoves(): List<Direction?>? {
        val children = rootNode.children ?: return null
        return evaluationPolicy.selectBestNode(children.filter { !it.isTerminal })?.instructionsToReachNode
    }

    private fun selection(parent: Node): Node? {
        val children = parent.children
            ?: return if (!parent.isTerminal) {
                parent
            } else {
                null
            }

        for (child in selectionPolicy.select(parent, children.filter { !it.isTerminal })) {
            val result = selection(child)
            if (result != null) {
                return result
            }
        }

        return null
    }

    private fun expansion(node: Node): Node {
        val children = expansionPolicy.expand(node)
        node.children = children
        return children.first()
    }

    private fun simulation(node: Node): Int {
        return simulationPolicy.simulateAndReturnScore(node)
    }

    private fun backpropagation(startNode: Node, score: Int) {
        var node: Node? = startNode
        while (node != null) {
            node.update(score)
            node = node.parent
        }
    }

    companion object : KLogging()
}

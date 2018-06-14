package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NiveauGraphImpl extends ResidualNetImpl implements NiveauGraph {

    private int index;

    public NiveauGraphImpl(ResidualNet residualNet) {
        int size = residualNet.getNumberOfNodes();
        this.adjMatrix = new Edge[size][size];
        LinkedList<Integer> allNodesOfAllLevels = new LinkedList<>();
        LinkedList<Integer> nodesOfCurrentLevel = new LinkedList<>();
        // manually add the root to the first current level
        nodesOfCurrentLevel.add(0);
        while (sinkNotReached()) {
                if (allNodesOfAllLevels.isEmpty()) {
                    for (int j = 1; j < size; j++) {
                        if (residualNet.hasEdge(0, j)) {
                            adjMatrix[0][j] = new Edge(0, j, residualNet.getEdgeCapacity(0, j));
                            nodesOfCurrentLevel.add(j);
                        }
                    }
                } else {
                    LinkedList<Integer> nodesOfLastLevel = new LinkedList<>();
                    nodesOfLastLevel.addAll(nodesOfCurrentLevel);
                    nodesOfCurrentLevel.clear();
                    for (Integer node : nodesOfLastLevel) {
                        for (int k = 0; k < size; k++) {
                            if (!allNodesOfAllLevels.contains(k) && residualNet.hasEdge(node, k)) {
                                adjMatrix[node][k] = new Edge(node, k, residualNet.getEdgeCapacity(node, k));
                                nodesOfCurrentLevel.add(k);
                            }

                        }
                    }
                }
            allNodesOfAllLevels.addAll(nodesOfCurrentLevel);
        }


    }

    private boolean sinkNotReached() {
        for (Edge[] aNiveauGraph : this.adjMatrix) {
            if (aNiveauGraph[this.adjMatrix.length - 1] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean allreadyInBFS(Edge edge) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] findPath() {
        return new Integer[0];
    }

}

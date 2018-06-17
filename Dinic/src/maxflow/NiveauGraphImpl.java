package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NiveauGraphImpl extends ResidualNetImpl implements NiveauGraph {

    private int index;

    public NiveauGraphImpl(ResidualNet resNet) {
        int size = resNet.getNumberOfNodes();
        this.adjMatrix = new Edge[size][size];
        LinkedList<Integer> allNodesOfAllLevels = new LinkedList<>();
        LinkedList<Integer> nodesOfCurrentLevel = new LinkedList<>();
        // manually add the root to the first current level
        int source = resNet.getSource();
        int sink = resNet.getSink();
        nodesOfCurrentLevel.add(source);
        while (allNodesOfAllLevels.isEmpty()
                || !allNodesOfAllLevels.contains(sink)) {
                if (allNodesOfAllLevels.isEmpty()) {
                    for (int j = 0; j < size; j++) {
                        if (resNet.hasEdge(0, j)) {
                            adjMatrix[0][j] = new Edge(0, j, resNet.getEdgeCapacity(0, j));
                            nodesOfCurrentLevel.add(j);
                        }
                    }
                } else {
                    LinkedList<Integer> nodesOfLastLevel = new LinkedList<>();
                    nodesOfLastLevel.addAll(nodesOfCurrentLevel);
                    nodesOfCurrentLevel.clear();
                    for (Integer node : nodesOfLastLevel) {
                        for (int k = 0; k < size; k++) {
                            // check all outgoing edges
                            if (!allNodesOfAllLevels.contains(k) && resNet.hasEdge(node, k)) {
                                adjMatrix[node][k] = new Edge(node, k, resNet.getEdgeCapacity(node, k));
                                nodesOfCurrentLevel.add(k);
                            }
                            // check all incoming edges
              //              if (!nodesOfCurrentLevel.contains(node) && resNet.hasEdge(k, node)) {
              //                  adjMatrix[k][node] = new Edge(k, node, resNet.getEdgeCapacity(k, node));
              //                  nodesOfCurrentLevel.add(node);
              //              }
                        }
                    }
                }
            allNodesOfAllLevels.addAll(nodesOfCurrentLevel);
        }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] findPath() {
        return new Integer[0];
    }

}

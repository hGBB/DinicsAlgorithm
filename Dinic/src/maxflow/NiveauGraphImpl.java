package maxflow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public class NiveauGraphImpl extends ResidualNetImpl implements NiveauGraph {
    private Map<Integer, Integer> nodeAndDepth = new HashMap<>();
    private int[] index;

    /**
     * Constructor
     *
     * @param resNet a residual net out of which the niveau graph is calculated
     */
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
                        adjMatrix[0][j] =
                                new Edge(0, j, resNet.getEdgeCapacity(0, j));
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
                        if (!allNodesOfAllLevels.contains(k)
                                && resNet.hasEdge(node, k)) {
                            adjMatrix[node][k] = new Edge(node, k,
                                    resNet.getEdgeCapacity(node, k));
                            nodesOfCurrentLevel.add(k);
                        }
                    }
                }
            }
            allNodesOfAllLevels.addAll(nodesOfCurrentLevel);
        }
        createIndexes(source, sink);
    }

    private void createIndexes(int source, int sink) {
        int counter = 0;
        int length = adjMatrix.length;
        index = new int[length];
        LinkedList<Integer> checkedNodes = new LinkedList<>();
        LinkedList<Integer> lastNodes = new LinkedList<>();
        LinkedList<Integer> currentNodes = new LinkedList<>();
        // add the source
        currentNodes.add(source);
        while (!checkedNodes.contains(sink)) {
            counter++;
            lastNodes.addAll(currentNodes);
            currentNodes.clear();
            for (Integer i : lastNodes) {
                if (!checkedNodes.contains(i)) {
                    index[i] = counter;

                    for (int j = 0; j < length; j++) {
                        if (adjMatrix[i][j] != null && !checkedNodes.contains(j) && !lastNodes.contains(j)) {
                            currentNodes.add(j);
                        }
                    }
                }
            }
            checkedNodes.addAll(lastNodes);
            lastNodes.clear();
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

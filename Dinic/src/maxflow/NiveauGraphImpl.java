package maxflow;

import java.util.*;

/**
 * {@inheritDoc}
 */
public class NiveauGraphImpl extends ResidualNetImpl implements NiveauGraph {
    private int[] index;

    /**
     * Constructor
     *
     * @param resNet a residual net out of which the niveau graph is calculated
     */
    public NiveauGraphImpl(ResidualNet resNet) {
        int size = resNet.getNumberOfNodes();
        this.adjMatrix = new int[size][size];
        LinkedList<Integer> allNodesOfAllLevels = new LinkedList<>();
        LinkedList<Integer> nodesOfCurrentLevel = new LinkedList<>();
        // manually add the root to the first current level
        int source = resNet.getSource();
        int sink = resNet.getSink();
        nodesOfCurrentLevel.add(source);
        int counter = source;
        while (counter <= sink) {
            if (allNodesOfAllLevels.isEmpty()) {
                for (int j = 0; j < size; j++) {
                    if (resNet.hasEdge(0, j)) {
                        adjMatrix[0][j] = resNet.getEdgeCapacity(0, j);
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
                            adjMatrix[node][k] = resNet.getEdgeCapacity(node, k);
                            nodesOfCurrentLevel.add(k);
                        }
                    }
                }
            }
            counter++;
            allNodesOfAllLevels.addAll(nodesOfCurrentLevel);
            if (allNodesOfAllLevels.contains(sink)
                    && sink == getNumberOfNodes() - 1) {
                break;
            }
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
                        if (adjMatrix[i][j] != 0 && !checkedNodes.contains(j)
                                && !lastNodes.contains(j)) {
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
        if (!isSinkReachableFromSource()) {
            return null;
        } else {
            int max = index[getSink()];
            Integer[] result = new Integer[max];
            LinkedList<Integer> edgesInNiveau = new LinkedList<>();
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    if (adjMatrix[i][j] > 0) {
                        edgesInNiveau.add(i);
                    }
                }
            }
            int emptyPositions = max - 1;
            int depth = 0;
            int counter = 0;
            while (emptyPositions >= 0) {
                int source = edgesInNiveau.get(counter);
                    // if sink can be reached and the depth
                    // is higher than the prior node add to result
                    if (isSinkReachableFromEdge(source)
                            && index[source] == depth + 1) {
                        result[depth] = source;
                        emptyPositions--;
                        depth++;
                    }
                    if (hasEdge(source, getSink())) {
                        result[max - 1] = getSink();
                        return result;
                    }
                    counter++;
                }
        }
        return null;
    }

    private boolean isSinkReachableFromEdge(int position) {
        List<Integer> checkedNodes = new ArrayList<>();
        List<Integer> currentlyChecking = new ArrayList<>();
        currentlyChecking.add(getSink());
        List<Integer> nextChecking = new ArrayList<>();
        while (true) {
            if (currentlyChecking.isEmpty()) {
                return false;
            } else {
                for (Integer i : currentlyChecking) {
                    if (i == position) {
                        return true;
                    } else {
                        for (int j = 0; j < adjMatrix.length - 1; j++) {
                            if ((checkedNodes.isEmpty()
                                    || !checkedNodes.contains(j))
                                    && !currentlyChecking.contains(j)
                                    && adjMatrix[j][i] > 0) {
                                nextChecking.add(j);
                            }
                        }
                    }
                }
                checkedNodes.addAll(currentlyChecking);
                currentlyChecking.clear();
                currentlyChecking.addAll(nextChecking);
                nextChecking.clear();
            }
        }
    }
}


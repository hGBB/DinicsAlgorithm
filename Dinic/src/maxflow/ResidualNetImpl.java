package maxflow;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class ResidualNetImpl implements ResidualNet {
    protected int[][] adjMatrix;

    /**
     * Standard constructor
     */
    public ResidualNetImpl() {
    }

    public ResidualNetImpl(int[][] adjMatrix, NetImpl.Flow flow) {
        this.adjMatrix = new int[adjMatrix.length][adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != 0) {
                    int flo = flow.getEdgeFlow(i, j);
                    int capacity = adjMatrix[i][j];
                    if (capacity - flo > 0) {
                        this.adjMatrix[i][j] = capacity - flo;
                    }
                    if (flo > 0 && adjMatrix[j][i] == 0) {
                        this.adjMatrix[j][i] = flo;
                    } else if (flo > 0 && adjMatrix[j][i] != 0) {
                        this.adjMatrix[j][i] += flo;
                    }
                }
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getEdgeCapacity(int source, int target) {
        return adjMatrix[source][target];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {
            adjMatrix[source][target] = capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEdge(int source, int target, int capacity) {
        return adjMatrix[source][target] == capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSinkReachableFromSource() {
        List<Integer> checkedNodes = new ArrayList<>();
        List<Integer> currentlyChecking = new ArrayList<>();
        currentlyChecking.add(getNumberOfNodes() - 1);
        List<Integer> nextChecking = new ArrayList<>();
        while (true) {
            if (currentlyChecking.isEmpty()) {
                return false;
            } else {
                for (Integer i : currentlyChecking) {
                    if (i == 0) {
                        return true;
                    } else {
                        for (int j = 0; j < adjMatrix.length - 1; j++) {
                            if ((checkedNodes.isEmpty()
                                    || !checkedNodes.contains(j))
                                    && !currentlyChecking.contains(j)
                                    && adjMatrix[j][i] != 0) {
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


    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNodes() {
        return adjMatrix.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSource() {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSink() {
        // arrays start at 0 therefore we have to subtract 1 from the length.
        for (int i = adjMatrix.length - 1; i > 0; i--) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[j][i] != 0) {
                    return i;
                }
            }
        }
        System.out.print("noooo getSink");
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(int source, int target) {
        return adjMatrix[source][target] > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != 0) {
                    sb.append(adjMatrix[i][j]);
                } else {
                    sb.append("0");
                }
                if (j < adjMatrix.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}

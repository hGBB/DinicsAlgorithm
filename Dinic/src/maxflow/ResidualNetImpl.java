package maxflow;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class ResidualNetImpl implements ResidualNet {
    protected Edge[][] adjMatrix;

    /**
     * Standard constructor
     */
    public ResidualNetImpl() {}

    public ResidualNetImpl(Edge[][] adjMatrix, NetImpl.Flow flow) {
        this.adjMatrix = new Edge[adjMatrix.length][adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    int flo = flow.getEdgeFlow(i, j);
                    int capacity = adjMatrix[i][j].getCapacity();
                    if (capacity - flo > 0) {
                        this.adjMatrix[i][j] = new Edge(i, j, capacity - flo);
                    }
                    if (flo > 0 && adjMatrix[j][i] == null) {
                        this.adjMatrix[j][i] = new Edge(j, i, flo);
                    } else if (flo > 0 && adjMatrix[j][i] != null) {
                        this.adjMatrix[j][i].addCapacity(flo);
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
        return adjMatrix[source][target].getCapacity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEdge(int source, int target, int capacity) {

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSinkReachableFromSource() {
        List<Integer> checkedNodes = new ArrayList<>();
        List<Integer> currentlyChecking = new ArrayList<>();
        currentlyChecking.add(getSink());
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
                            if ((checkedNodes.isEmpty() || !checkedNodes.contains(j)) && !currentlyChecking.contains(j) && adjMatrix[j][i] != null) {
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
                if (adjMatrix[i][j] != null) {
                    return i;
                }
            }
        }
        System.out.print("nooooGetSource");
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
                if (adjMatrix[j][i] != null) {
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
        return adjMatrix[source][target] != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge[] anAdjMatrix : adjMatrix) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (anAdjMatrix[j] != null) {
                    sb.append(anAdjMatrix[j].getCapacity());
                } else {
                    sb.append("0");
                }
                if (j < adjMatrix.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString().trim();}
}

package maxflow;

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
                    int flo = adjMatrix[i][j].getFlow();
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
        return reachableSink(0);
    }

    private boolean reachableSink(int startingNode) {
        return false;
    }

    /**
     * Helper method for isSinkReachableFromSource() parses the Node / Edge net
     *
     * @param node Starting node to parse through the net towards sink
     * @return True only if the sink is connected to the source
     */
    private boolean parseAllNodes(Node node) {
        return false;
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
    return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSink() {
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

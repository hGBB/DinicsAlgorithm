package maxflow;

/**
 * {@inheritDoc}
 */
public class ResidualNetImpl implements ResidualNet {
    private int[][] adjMatrix;


    public ResidualNetImpl() {}

    public ResidualNetImpl(int[][] net, int[][] flow) {
        adjMatrix = new int[net.length][net.length];
        for (int i = 0; i < net.length; i++) {
            for (int j = 0; j < net.length; j++) {
                adjMatrix[i][j] = net[i][j] - flow[i][j];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEdgeCapacity(int source, int target) {
        return 0;
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
        // parse all nodes starting from the source
        // -> the first node in the array
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
        return adjMatrix.length;
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
        for (int[] anAdjMatrix : adjMatrix) {
            for (int j = 0; j < adjMatrix.length; j++) {
                sb.append(anAdjMatrix[j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

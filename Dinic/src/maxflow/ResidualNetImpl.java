package maxflow;

/**
 * {@inheritDoc}
 */
public class ResidualNetImpl implements ResidualNet {
    private int[][] adjMatrix;

    /**
     * Standard constructor
     */
    public ResidualNetImpl() {}

    /**
     * Constructor for a ResidualNet
     * @param net The ResidualNet's net
     * @param flow The ResidualNet's flow
     */
    public ResidualNetImpl(int[][] net, int[][] flow) {
        // TODO reverse this! from target to source
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
        return reachableSink(0);
    }

    private boolean reachableSink(int startingNode) {
    /*    for (int i = adjMatrix.length - 1; i >= 0; i--) {
            if (adjMatrix[startingNode][0] != 0) {
                return true;
            } else if (adjMatrix[startingNode][i] != 0) {
                return reachableSink(i);
            }
        }
*/

        //    System.out.println(startingNode);
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[startingNode][adjMatrix.length - 1] != 0) {
                return true;
            } else if (adjMatrix[startingNode][i] != 0) {
                return reachableSink(i);
            }
        }

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
                if (j < adjMatrix.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

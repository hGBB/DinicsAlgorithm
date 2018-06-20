package maxflow;

/**
 * {@inheritDoc}
 */
public class MaxFlowImpl implements MaxFlow {

    /**
     * Constructor
     */
    public MaxFlowImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeMaxFlow(Net net) {
        boolean reachable;
        do {
            ResidualNet residualNet = net.createResidualNet();
           // System.out.println(residualNet);
            reachable = residualNet.isSinkReachableFromSource();
            if (reachable) {
                NiveauGraph niveauGraph = net.createNiveauGraph(residualNet);
                int[][] flow = computeBlockingFlow(niveauGraph);
                for (int i = 0; i < flow.length; i++) {
                    for (int j = 0; j < flow.length; j++) {
                        if (flow[i][j] > 0) {
                            net.getFlow().addEdgeFlow(j, i, flow[i][j]);
                        }
                    }
                }
            }
        } while (reachable);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void step(Net net) {
        int size = net.getNumberOfNodes();
        boolean reachable;
    }

    private int[][] computeBlockingFlow(NiveauGraph niveauGraph) {
        int size = niveauGraph.getNumberOfNodes();
        int[][] flow = new int[size][size];
        int[][] capacity = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                flow[i][j] = 0;
                if (niveauGraph.hasEdge(i, j)) {
                    capacity[i][j] = niveauGraph.getEdgeCapacity(i, j);
                }
            }
        }
        int minimumCapacity = 99999;
        do {
            Integer[] path = niveauGraph.findPath();
            for (int i = 0; i < path.length - 1; i++) {
                if (capacity[path[i]][path[i + 1]] < minimumCapacity) {
                    minimumCapacity = capacity[path[i]][path[i + 1]];
                }
                niveauGraph.setEdgeCapacity(path[i], path[i + 1], 0);
            }
            revertArray(path);
            for (int i = 0; i < path.length - 1; i++) {
                flow[path[i]][path[i + 1]] += minimumCapacity;
            }
        } while (niveauGraph.isSinkReachableFromSource());
        return flow;
    }

    /**
     * Helper method to revert an array like 1234 -> 4321
     *
     * @param array the content order will be reverted
     */
    private void revertArray(Integer[] array) {
        int arrayLength = array.length;
        int placeholder;
        for (int i = 0; i < (arrayLength / 2); i++) {
            placeholder = array[i];
            array[i] = array[arrayLength - i - 1];
            array[arrayLength - i - 1] = placeholder;
        }
    }
}


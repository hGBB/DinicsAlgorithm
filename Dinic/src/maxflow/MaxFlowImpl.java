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
            reachable = residualNet.isSinkReachableFromSource();
            if (reachable) {
                NiveauGraph niveauGraph = net.createNiveauGraph(residualNet);
                computeBlockingFlow(niveauGraph);
                System.out.println("yay");

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

    private void computeBlockingFlow(NiveauGraph niveauGraph) {
        int size = niveauGraph.getNumberOfNodes();
        Integer[][] flow = new Integer[size][size];
        Integer[][] capacity = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                flow[i][j] = 0;
                if (niveauGraph.hasEdge(i, j)) {
                    capacity[i][j] = niveauGraph.getEdgeCapacity(i, j);
                }
            }
        }
        int c_min = 99999;
        do {
            Integer[] path = niveauGraph.findPath();
            for (int i = 0; i < path.length - 1; i++) {
                if (capacity[path[i]][path[i + 1]] < c_min) {
                    c_min = capacity[path[i]][path[i + 1]];
                }
                niveauGraph.setEdgeCapacity(path[i], path[i + 1], 0);
            }
            revertArray(path);
            for (int i = 0; i < path.length - 1; i++) {
                flow[path[i]][path[i + 1]] += c_min;
            }
        } while (niveauGraph.isSinkReachableFromSource());
    }

    /**
     * Helper method to revert an array
     *
     * @param array the content order will be reverted i.e 1->6 and 6->1
     */
    private void revertArray(Integer[] array) {
        int arrayLength = array.length - 1;
        int placeholder;
        for (int i = 0; i < arrayLength / 2; i++) {
            placeholder = array[i];
            array[i] = array[arrayLength - i];
            array[arrayLength - i] = placeholder;
        }
    }
}


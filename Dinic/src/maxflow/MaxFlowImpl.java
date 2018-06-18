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


            }
        } while (reachable);
        System.out.println(net);
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
        Integer[] path = niveauGraph.findPath();
        revertArray(path);
    }

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


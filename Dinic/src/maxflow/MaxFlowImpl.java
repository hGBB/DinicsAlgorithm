package maxflow;

/**
 * {@inheritDoc}
 */
public class MaxFlowImpl implements MaxFlow {

    public MaxFlowImpl() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeMaxFlow(Net net) {
        ResidualNet residualNet = net.createResidualNet();
        boolean reachabel = residualNet.isSinkReachableFromSource();
        NiveauGraph niveauGraph = net.createNiveauGraph(residualNet);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void step(Net net) {

    }
}

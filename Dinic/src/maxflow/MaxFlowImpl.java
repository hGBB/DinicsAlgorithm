package maxflow;

import java.util.ArrayList;
import java.util.List;

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
        ResidualNet residualNet = net.createResidualNet();
        boolean reachabel = residualNet.isSinkReachableFromSource();
        NiveauGraph niveauGraph = net.createNiveauGraph(residualNet);
    }

    private void dinic(NiveauGraph niveauGraph, Net net) {
        int size = niveauGraph.getNumberOfNodes();
        int[][] flow = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                flow[i][j] = 0;
            }
        }

    }

    public boolean isSourceReachableFromSink(NiveauGraph niveauGraph) {
        List<Integer> checkedNodes = new ArrayList<>();
        List<Integer> currentlyChecking = new ArrayList<>();
        currentlyChecking.add(niveauGraph.getSource());
        List<Integer> nextChecking = new ArrayList<>();
        while (true) {
            if (currentlyChecking.isEmpty()) {
                return false;
            } else {
                for (Integer i : currentlyChecking) {
                    if (i == niveauGraph.getSink()) {
                        return true;
                    } else {
                        for (int j = 0; j < niveauGraph.getNumberOfNodes() - 1; j++) {
                            if ((checkedNodes.isEmpty()
                                    || !checkedNodes.contains(j))
                                    && !currentlyChecking.contains(j)
                                    && niveauGraph.hasEdge(j, i)) {
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
    public void step(Net net) {

    }
}

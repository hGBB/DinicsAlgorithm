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
        boolean reachable;
        do {
            ResidualNet residualNet = net.createResidualNet();
        //    System.out.println(residualNet);
        //    System.out.println(" ***** ");
            NiveauGraph niveauGraph = net.createNiveauGraph(residualNet);
            Integer blockingNodes [] = dinic(niveauGraph);
            int min_c = 0;
            if (blockingNodes != null) {
                for (int i = 0; i < blockingNodes.length - 1; i++) {
                    int edgeCapacity = niveauGraph.getEdgeCapacity(blockingNodes[i], blockingNodes[i + 1]);
                    min_c += edgeCapacity;
                }
                for (int i = 0; i < blockingNodes.length - 1; i++) {
                    net.getFlow().addEdgeFlow(blockingNodes[i], blockingNodes[i + 1], min_c);
                }
            } else {
                System.out.println(niveauGraph);
            }
            reachable = residualNet.isSinkReachableFromSource();
        } while (reachable);
        System.out.println(net);
    }

    private Integer[] dinic(NiveauGraph niveauGraph) {
        return niveauGraph.findPath();
    }

    private void revertEdge(int source, int target) {

    }

    public boolean isSourceReachableFromSink(NiveauGraph niveau) {
        List<Integer> checkedNodes = new ArrayList<>();
        List<Integer> currentlyChecking = new ArrayList<>();
        currentlyChecking.add(niveau.getSource());
        List<Integer> nextChecking = new ArrayList<>();
        while (true) {
            if (currentlyChecking.isEmpty()) {
                return false;
            } else {
                for (Integer i : currentlyChecking) {
                    if (i == niveau.getSink()) {
                        return true;
                    } else {
                        for (int j = 0; j < niveau.getNumberOfNodes() - 1;
                             j++) {
                            if ((checkedNodes.isEmpty()
                                    || !checkedNodes.contains(j))
                                    && !currentlyChecking.contains(j)
                                    && niveau.hasEdge(j, i)) {
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

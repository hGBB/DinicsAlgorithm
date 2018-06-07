package maxflow;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private Node[] nodes;
    private Edge[] edges;

//    private Node source; -> erster node in der Liste
//    private Node target; -> letzter node in der Liste
//    private flow capacity -> ad hoc berechnet


    /**
     * {@inheritDoc}
     */
    @Override
    public Flow getFlow() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResidualNet createResidualNet() {
        ResidualNet newResNet = new ResidualNetImpl(nodes, edges);
        return newResNet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NiveauGraph createNiveauGraph(ResidualNet residualNet) {
        return null;
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
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNodes() {
        return 0;
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
        for (Edge e : edges) {
            if (e.getSource() == source && e.getTarget() == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public class Flow implements Net.Flow {
        /**
         * {@inheritDoc}
         */
        @Override
        public int getEdgeFlow(int source, int target) {

            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addEdgeFlow(int source, int target, int flowAdd) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setEdgeFlow(int source, int target, int flow) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isValidFlow() {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {
            nodes = new Node[]{};
            edges = new Edge[]{};
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTotalFlow() {
            return 0;
        }
    }
}

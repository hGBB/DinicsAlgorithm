package maxflow;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private Node[] nodes;
    private Edge[] edges;
    private Flow flow;
//    private Node source; -> erster node in der Liste
//    private Node target; -> letzter node in der Liste
//    private flow capacity -> ad hoc berechnet


    /**
     * {@inheritDoc}
     */
    @Override
    public Flow getFlow() {
        return flow;
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
        for (Edge e : edges) {
            if (e.getSource() == source && e.getTarget() == target) {
                return e.getCapacity();
            }
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {
        for (Edge e : edges) {
            if (e.getSource() == source && e.getTarget() == target) {
                e.setCapacity(capacity);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEdge(int source, int target, int capacity) {
        for (Edge e : edges) {
            if (e.getSource() == source && e.getTarget() == target &&
                    e.getCapacity() == capacity) {
                return true;
            }
        }
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
        return nodes.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSource() {
        return nodes[0].getNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSink() {
        return nodes[nodes.length - 1].getNumber();
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
            for (Edge e : edges) {
                if (e.getSource() == source && e.getTarget() == target) {
                    e.setFlow(flow);
                }
            }
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

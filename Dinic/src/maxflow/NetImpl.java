package maxflow;

public class NetImpl extends ResidualNetImpl implements Net {
    private Flow flow;


    @Override
    public Flow getFlow() {
        return flow;
    }

    @Override
    public ResidualNet createResidualNet() {
        return null;
    }

    @Override
    public NiveauGraph createNiveauGraph(ResidualNet residualNet) {
        return null;
    }

    @Override
    public int getEdgeCapacity(int source, int target) {
        return 0;
    }

    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {

    }

    @Override
    public boolean isValidEdge(int source, int target, int capacity) {
        return false;
    }

    @Override
    public boolean isSinkReachableFromSource() {
        return false;
    }

    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public int getSource() {
        return 0;
    }

    @Override
    public int getSink() {
        return 0;
    }

    @Override
    public boolean hasEdge(int source, int target) {
        return false;
    }

    public class Flow implements Net.Flow {
        @Override
        public int getEdgeFlow(int source, int target) {
            return 0;
        }

        @Override
        public void addEdgeFlow(int source, int target, int flowAdd) {

        }

        @Override
        public void setEdgeFlow(int source, int target, int flow) {

        }

        @Override
        public boolean isValidFlow() {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public int getTotalFlow() {
            return 0;
        }
    }
}

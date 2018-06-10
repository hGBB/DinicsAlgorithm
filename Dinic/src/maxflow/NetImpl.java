package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private Node[] nodes;
    private Edge[] edges;
    private int[][] adjMatrix;
    private Flow flow;

    public NetImpl(LinkedList<int[]> input) {
        int matrixSize = input.get(0)[0];
        this.adjMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        for (int i = 1; i < input.size(); i++) {
            int [] currentLine = input.get(i);
            this.adjMatrix[currentLine[0] - 1][currentLine[1] - 1] = currentLine[2];
        }
    }



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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] anAdjMatrix : adjMatrix) {
            for (int j = 0; j < adjMatrix.length; j++) {
                sb.append(anAdjMatrix[j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
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

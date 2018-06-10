package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private int[][] adjMatrix;
    private Flow flow;

    public NetImpl(LinkedList<int[]> input) {
        // create matrix size is number given in the input's first line
        int matrixSize = input.get(0)[0];
        this.adjMatrix = new int[matrixSize][matrixSize];
        this.flow = new Flow();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        for (int i = 1; i < input.size(); i++) {
            // fill the matrix
            int[] currentLine = input.get(i);
            this.adjMatrix[currentLine[0]][currentLine[1]] = currentLine[2];
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
        return null;
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
        // shift to the left because arrays start at 0
        return adjMatrix[source - 1][target - 1];

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {
        // shift to the left because arrays start at 0
        adjMatrix[source - 1][target - 1] = capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEdge(int source, int target, int capacity) {
        // shift to the left because arrays start at 0
        return adjMatrix[source - 1][target - 1] == capacity;
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
        return adjMatrix.length;
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
        return adjMatrix.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(int source, int target) {
        return adjMatrix.length < source && adjMatrix.length < target;
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
        int[][] flowMatrix;

        /**
         * {@inheritDoc}
         */
        @Override
        public int getEdgeFlow(int source, int target) {
            // shift to the left because arrays start at 0
            return flowMatrix[source - 1][target - 1];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addEdgeFlow(int source, int target, int flowAdd) {
            // shift to the left because arrays start at 0
            flowMatrix[source - 1][target - 1] += flowAdd;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setEdgeFlow(int source, int target, int flow) {
            // shift to the left because arrays start at 0
            flowMatrix[source - 1][target - 1] = flow;
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
            for (int i = 0; i < flowMatrix.length; i++) {
                for (int j = 0; j < flowMatrix.length; j++) {
                    flowMatrix[i][j] = 0;
                }
            }
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

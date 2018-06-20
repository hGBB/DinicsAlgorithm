package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private final Flow flow;

    /**
     * Constructor for a new net
     *
     * @param input a list out of which a net is created
     */
    public NetImpl(LinkedList<int[]> input) {
        // create matrix with size given in the input's first line
        int matrixSize = input.get(0)[0];
        this.adjMatrix = new int[matrixSize][matrixSize];
        // create new list of edges
        this.flow = new Flow();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                // fill matrix
                adjMatrix[i][j] = EMPTY_CAPACITY;
            }
        }
        for (int i = 1; i < input.size(); i++) {
            // fill the matrix with the values of the input edges
            int[] currentLine = input.get(i);
            int source = currentLine[0];
            int target = currentLine[1];
            int capacity = currentLine[2];
            this.adjMatrix[source][target] = capacity;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flow getFlow() {
        return this.flow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResidualNet createResidualNet() {
        return new ResidualNetImpl(adjMatrix, flow);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NiveauGraph createNiveauGraph(ResidualNet residualNet) {
        return new NiveauGraphImpl(residualNet);
    }

    /**
     * {@inheritDoc}
     */
    public class Flow implements Net.Flow {
        private final int[][] flowMatrix;

        public Flow() {
            flowMatrix = new int[adjMatrix.length][adjMatrix.length];
            for (int i = 0; i < flowMatrix.length; i++) {
                for (int j = 0; j < flowMatrix.length; j++) {
                    flowMatrix[i][j] = EMPTY_CAPACITY;
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getEdgeFlow(int source, int target) {
            return flowMatrix[source][target];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addEdgeFlow(int source, int target, int flowAdd) {
            flowMatrix[source][target] += flowAdd;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setEdgeFlow(int source, int target, int flow) {
            if (adjMatrix[source][target] >= flow) {
                flowMatrix[source][target] = flow;
                // Todo move this error to shell!
            } else {
                System.out.println("Error! ladida");
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isValidFlow() {
            int size = adjMatrix.length;
            for (int i = 0; i < size; i++) {
                // nothing flows into the source ( -> first column always 0)
                // nothing flows out of the sink ( -> last row always 0)
                if ((adjMatrix[i][0] != EMPTY_CAPACITY
                        && flowMatrix[i][0] > EMPTY_CAPACITY)
                        || (adjMatrix[size - 1][i] != EMPTY_CAPACITY
                        && flowMatrix[size - 1][i] > EMPTY_CAPACITY)) {
                    return false;
                }
            }
            // not possible for and edge to have a higher flow than capacity
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (flowMatrix[i][j] != EMPTY_CAPACITY && flowMatrix[i][j]
                            > adjMatrix[i][j]) {
                        return false;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                int incoming = 0;
                int outgoing = 0;
                for (int j = 0; j < size; j++) {
                    if (i != getSource() && i != getSink()) {
                        incoming += flowMatrix[i][j];
                        outgoing += flowMatrix[j][i];
                    }
                }
                if (incoming != outgoing) {
                    System.out.print("kirchhoff is mad!");
                    return false;
                }
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    if (adjMatrix[i][j] != EMPTY_CAPACITY) {
                        flowMatrix[i][j] = EMPTY_CAPACITY;
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getTotalFlow() {
            int flow = 0;
            for (int[] aFlowMatrix : flowMatrix) {
                flow += aFlowMatrix[getSink()];
            }
            return flow;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    if (adjMatrix[i][j] != EMPTY_CAPACITY) {
                        if (flowMatrix[i][j] != EMPTY_CAPACITY) {
                            sb.append("(").append(i + 1)
                                    .append(", ").append(j + 1)
                                    .append(") (")
                                    .append(flowMatrix[i][j])
                                    .append("/").append(adjMatrix[i][j])
                                    .append(")").append("\n");
                        }
                    }
                }
            }
            return sb.toString().trim();
        }
    }
}

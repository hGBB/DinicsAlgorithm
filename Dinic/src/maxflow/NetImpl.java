package maxflow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private int[][] adjMatrix;
    private Flow flow;

    public NetImpl(LinkedList<int[]> input) {
        // create matrix with size given in the input's first line
        int matrixSize = input.get(0)[0];
        this.adjMatrix = new int[matrixSize][matrixSize];
        this.flow = new Flow();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        for (int i = 1; i < input.size(); i++) {
            // fill the matrix with the values of the following lines
            int[] currentLine = input.get(i);
            this.adjMatrix[currentLine[0]][currentLine[1]] = currentLine[2];
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

        return new ResidualNetImpl(adjMatrix, flow.flowMatrix);
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] anAdjMatrix : adjMatrix) {
            for (int j = 0; j < adjMatrix.length; j++) {
                sb.append(anAdjMatrix[j]);
                if (j < adjMatrix.length - 1) {
                    sb.append(" ");
                }
            }
            if (!Arrays.equals(anAdjMatrix, adjMatrix[anAdjMatrix.length - 1]))
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    public class Flow implements Net.Flow {
        private int[][] flowMatrix;

        public Flow() {
            flowMatrix = new int[adjMatrix.length][adjMatrix.length];
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

        public int getEdgeFlow(int source, int target) {
            // shift to the left because arrays start at 0
            return flowMatrix[source][target];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addEdgeFlow(int source, int target, int flowAdd) {
            // shift to the left because arrays start at 0
            flowMatrix[source][target] += flowAdd;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setEdgeFlow(int source, int target, int flow) {
            flowMatrix[source][target] = flow;
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < flowMatrix.length; i++) {
                for (int j = 0; j < flowMatrix.length; j++) {
                    if (flowMatrix[i][j] != 0) {
                        // the array starts at 0 therefore we need a right shift of the position of the edges
                        sb.append("(").append(i + 1).append(", ").append(j + 1)
                                .append(") (").append(flowMatrix[i][j])
                                .append("/").append(adjMatrix[i][j])
                                .append(")").append("\n ");
                    }
                }
            }
            return sb.toString();
        }
    }

}

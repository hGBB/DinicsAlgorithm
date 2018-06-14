package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class NetImpl extends ResidualNetImpl implements Net {
    private Flow flow;


    public NetImpl(LinkedList<int[]> input) {
        // create matrix with size given in the input's first line
        int matrixSize = input.get(0)[0];
        this.adjMatrix = new Edge[matrixSize][matrixSize];
        // create new list of edges
        this.flow = new Flow();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                // fill matrix
                adjMatrix[i][j] = null;
            }
        }
        for (int i = 1; i < input.size(); i++) {
            // fill the matrix with the values of the input edges
            int[] currentLine = input.get(i);
            int source = currentLine[0];
            int target = currentLine[1];
            int capacity = currentLine[2];
            this.adjMatrix[source][target] = new Edge(source, target, capacity);
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
        // TODO flow is in capacity not in flow
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
            if (adjMatrix[source][target].getCapacity() >= flow) {
                adjMatrix[source][target].setFlow(flow);
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
            for (int i = 0; i < adjMatrix.length; i++) {
                // nothing flows into the source ( -> first column always 0)
                // nothing flows out of the sink ( -> last row always 0)
                if ((adjMatrix[i][0] != null && adjMatrix[i][0].getFlow() > 0)
                        || (adjMatrix[adjMatrix.length - 1][i] != null && adjMatrix[adjMatrix.length - 1][i].getFlow() > 0)) {
                    return false;
                }
            }
            // it is not possible for and edge to have a higher flow than capacity
            for (Edge[] edgeArray : adjMatrix)
            for (Edge e : edgeArray) {
                if (e != null && e.getFlow() > e.getCapacity()) {
                    return false;
                }
            }
            // TODO kirchhoffsches gesetz!!!
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {

            for (int i = 0; i < adjMatrix.length; i++) {
                for (int j = 0; j < adjMatrix.length; j++) {
                    if (adjMatrix[i][j] != null)
                    adjMatrix[i][j].setFlow(0);
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
            // TODO overwork this!
            StringBuilder sb = new StringBuilder();
            for (Edge[] edgeArray : adjMatrix) {
                for (Edge e : edgeArray) {
                    if (e != null && e.getFlow() != 0) {
                        sb.append("(").append(e.getSource() + 1).append(", ").append(e.getTarget() + 1)
                                .append(") (").append(e.getFlow())
                                .append("/").append(e.getCapacity())
                                .append(")").append("\n");
                    }
                }
            }
            return sb.toString().trim();
        }
    }

}

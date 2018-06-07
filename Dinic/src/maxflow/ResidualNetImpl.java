package maxflow;

import java.util.LinkedList;

/**
 * {@inheritDoc}
 */
public class ResidualNetImpl implements ResidualNet {
    private Node[] nodes;
    private int nodenr;
    private Edge[] edges;

    public ResidualNetImpl() {}

    public ResidualNetImpl(Node[] nodes, Edge[] edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.nodenr = nodes.length;
    }

    public ResidualNetImpl(LinkedList<int[]> convertedInput) {
        this.nodenr = convertedInput.get(0)[0];
        this.nodes = new Node[nodenr];
        for (int i = 0; i < nodenr; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 1; i < convertedInput.size() - 1; i++) {
            int[] input = convertedInput.get(i);
            // arrays start at 0, the nodes at 1 therefore we have to subtract 1 of the position
            Edge edge = new Edge(input[0] - 1, input[1] - 1, input[2]);
            nodes[input[0] - 1].addEdge(edge);
            nodes[input[1] - 1].addEdgeIn(edge);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEdgeCapacity(int source, int target) {
        for (Node node : nodes) {
            if (node.getNumber() == source) {
                for (Edge edge : node.getOutgoingEdges()) {
                    if (edge.getSource() == source && edge.getTarget() == target) {
                        return edge.getCapacity();
                    }
                }
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
            if (e.getSource() == source && e.getTarget() == target && capacity >= 0) {
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
        Node source = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].getNumber() < source.getNumber()) {
                source = nodes[i];
            }
        }
        return source.getNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSink() {
        Node source = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].getNumber() > source.getNumber()) {
                source = nodes[i];
            }
        }
        return source.getNumber();
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
}

package maxflow;

import java.util.Map;

public class Node {
    private int number;
    private Map<Node, Integer> incomingEdges;
    private Map<Node, Integer> outgoingEdges;

    public Node(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Map<Node, Integer> getIncomingEdges() {
        return incomingEdges;
    }

    public Map<Node, Integer> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void addOutgoingEdge(Node node, Integer capacity) {
        this.outgoingEdges.put(node, capacity);
    }

    public void addIncomingEdge(Node node, Integer capacity) {
        this.incomingEdges.put(node, capacity);
    }
}

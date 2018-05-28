package maxflow;

import java.util.LinkedList;

public class Node {
    private int number;
    private LinkedList<Edge> edges;

    public Node(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public LinkedList<Edge> getOutgoingEdges() {
        return edges;
    }

    public void addOutgoingEdge(int source, int target, int capacity) {
        this.edges.add(new Edge(source, target, capacity));
    }
}

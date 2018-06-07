package maxflow;

import java.util.LinkedList;

public class Node {
    private int number;
    private LinkedList<Edge> outgoingEdges;
    private LinkedList<Edge> incomingEdges;

    public Node(int number) {
        this.number = number;
        this.outgoingEdges = new LinkedList<>();
        this.incomingEdges = new LinkedList<>();
    }

    public void addEdge(Edge edge) {
        outgoingEdges.add(edge);
    }

    public void addEdgeIn(Edge edge) {
        incomingEdges.add(edge);
    }

    public int getNumber() {
        return number;
    }

    public LinkedList<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public LinkedList<Edge> getIncomingEdges() {
        return incomingEdges;
    }


}

package maxflow;

public class Edge {
    private int source;
    private int target;
    private int capacity; // the maximum amount which CAN flow through an edge
    private int flow;     // the amount which IS flowing through an edge
    private int depth;

    public Edge(int s, int t, int capacity) {
        source = s;
        target = t;
        this.capacity = capacity;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addCapacity(int capacity) {
        this.capacity = this.capacity + capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
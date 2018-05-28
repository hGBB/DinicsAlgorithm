package maxflow;

public class Edge {
    private int source;
    private int capacity;
    private int target;
    private Net.Flow flow;

    public Edge(int s, int t, int capacity) {
        source = s;
        target = t;
        this.capacity = capacity;
    }

    public int getSource() {
        return source;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTarget() {
        return target;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Net.Flow getFlow() {
        return flow;
    }

    public void setFlow(Net.Flow flow) {
        this.flow = flow;
    }
}

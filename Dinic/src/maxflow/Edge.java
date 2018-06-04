package maxflow;

public class Edge {
    private int source;
    private int target;
    private int capacity;

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
}
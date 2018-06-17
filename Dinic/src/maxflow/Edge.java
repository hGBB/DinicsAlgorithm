package maxflow;

public class Edge {
    private int source;
    private int target;
    private int capacity; // the maximum amount which CAN flow through an edge

    /**
     * Constructor for edges
     * @param s the source node
     * @param t the target node
     * @param capacity the amount which can flow through the edge
     */
    public Edge(int s, int t, int capacity) {
        source = s;
        target = t;
        this.capacity = capacity;
    }

    /**
     * Getter for source
     *
     * @return the source Node
     */
    public int getSource() {
        return source;
    }

    /**
     * Getter for target
     *
     * @return the target node
     */
    public int getTarget() {
        return target;
    }

    /**
     * Getter for capacity
     *
     * @return the amount of the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setter for the capacity
     *
     * @param capacity amount of the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds to the capacity
     *
     * @param capacity the amount to increment the capacity with
     */
    public void addCapacity(int capacity) {
        this.capacity = this.capacity + capacity;
    }
    }
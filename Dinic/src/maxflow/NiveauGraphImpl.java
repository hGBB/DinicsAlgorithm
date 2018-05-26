package maxflow;

public class NiveauGraphImpl implements NiveauGraph {
    @Override
    public Integer[] findPath() {
        return new Integer[0];
    }

    @Override
    public int getEdgeCapacity(int source, int target) {
        return 0;
    }

    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {

    }

    @Override
    public boolean isValidEdge(int source, int target, int capacity) {
        return false;
    }

    @Override
    public boolean isSinkReachableFromSource() {
        return false;
    }

    @Override
    public int getNumberOfNodes() {
        return 0;
    }

    @Override
    public int getSource() {
        return 0;
    }

    @Override
    public int getSink() {
        return 0;
    }

    @Override
    public boolean hasEdge(int source, int target) {
        return false;
    }
}

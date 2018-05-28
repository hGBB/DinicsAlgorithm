package maxflow;

public class Flow implements Net.Flow {
    @Override
    public int getEdgeFlow(int source, int target) {
        return 0;
    }

    @Override
    public void addEdgeFlow(int source, int target, int flowAdd) {

    }

    @Override
    public void setEdgeFlow(int source, int target, int flow) {

    }

    @Override
    public boolean isValidFlow() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getTotalFlow() {
        return 0;
    }
}

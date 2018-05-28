package maxflow;

/**
 * {@inheritDoc}
 */
public class NiveauGraphImpl extends ResidualNetImpl implements NiveauGraph {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer[] findPath() {
        return new Integer[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEdgeCapacity(int source, int target) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEdgeCapacity(int source, int target, int capacity) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidEdge(int source, int target, int capacity) {
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
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSource() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSink() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(int source, int target) {
        return false;
    }
}

package algorithm;

public class State<T> implements Comparable{
    T state;
    State<T> cameFrom;
    double cost = 0;
    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public State<T> getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // check equals()
    public boolean equals(State<T> state) {
        return state.toString().equals(this.state.toString());
    }

    // hashcode?
    public int hashcode() {
        return this.getState().hashCode();

    }

    @Override
    public int compareTo(Object o) {
        State<T> otherState = (State<T>) o;

        if (this.getCost() == otherState.getCost())
            return 0;
        if (this.getCost() > otherState.getCost())
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}

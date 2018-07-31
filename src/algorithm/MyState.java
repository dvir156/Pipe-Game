package algorithm;

public class MyState {
    Integer x;
    Integer y;
    Character value;
    Integer cost;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public MyState(Integer x, Integer y, Character value, Integer cost) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.cost = cost;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return x + "," + y + " , " + value;
    }

}

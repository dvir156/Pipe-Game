package algorithm;

import java.util.ArrayList;

public class Solution<T> {
    private ArrayList<T> solution;

    public ArrayList<T> getSolution() {
        return solution;
    }
    public String toString() {
        String str = "[";
        for(int i = 0; i < this.solution.size(); i++) {
            str += solution.get(i).toString();
        }
        str += "]";
        return str;
    }
    public void setSolution(ArrayList<T> solution) {
        this.solution = solution;
    }

}

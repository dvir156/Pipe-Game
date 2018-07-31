package algorithm;

import server.CommonSearcher;

import java.util.*;

public class BFS<T> extends CommonSearcher<T> {

    Queue<State<T>> queue = new LinkedList<>();
    @Override
    public Solution search(Searchable<T> s) {

        queue.add(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<State<T>>();

        while (queue.size() > 0) {
            State<T> n = queue.poll();
            closedSet.add(n);
            if (n.equals(s.getGoalState())) {
                return backTrace(n, s.getInitialState());
            }

            ArrayList<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !queue.contains(state)) {
                    state.setCameFrom(n);
                    queue.add(state);

                }
            }

        }
        return null;
    }


}

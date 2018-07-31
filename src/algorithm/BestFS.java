package algorithm;

import server.CommonSearcher;

import java.util.*;


public class BestFS<T> extends CommonSearcher<T> {

    @Override
    public Solution<T> search(Searchable<T> s) {
        openList.add(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<State<T>>();

        while (openList.size() > 0) {
            State<T> n = popOpenList();
            closedSet.add(n);
            if (n.equals(s.getGoalState())){
                return backTrace(n, s.getInitialState());
            }

            ArrayList<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !openList.contains(state)) {
                    state.setCameFrom(n);
                    openList.add(state);

                }
            }

        }
        return null;
    }

}

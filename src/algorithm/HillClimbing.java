package algorithm;

import server.CommonSearcher;

public class HillClimbing<T> extends CommonSearcher<T> {
    private long timeToRun;
    private Heuristic<T> heuristic;
    public interface Heuristic<T> {
        double power();
        boolean compare(double left, double right);
        double calc(State<T> state);
    }
    public HillClimbing(long timeToRun, Heuristic<T> heuristic) {
        this.timeToRun = timeToRun;
        this.heuristic = heuristic;
    }

    public Solution<T> search(Searchable<T> s)
    {
//        algorithm.State<T> n = s.getInitialState();
////        long time0 = System.currentTimeMillis();
////        while (System.currentTimeMillis() - time0 < timeToRun) {
////            ArrayList<algorithm.State<T>> neighbors = s.getAllPossibleStates(n);
////
////            if (n == s.getGoalState()) {
////                return backTrace(n,s.getInitialState());
////            }
////            if (neighbors.size() > 0)
////            {
////                if (Math.random() < 0.7)
////                {
////                    double grade =  heuristic.power();
////                    for (algorithm.State<T> step : neighbors) {
////                        double g = heuristic.calc(step);
////                        if (heuristic.compare(g , grade))
////                        {
////                            grade = g;
////                            n = step;
////                        }
////                    }
////                }
////                else
////                    {
////                        n = neighbors.get(new Random().nextInt(neighbors.size()));
////                    }
////            }
////        }
        return null;
    }
}
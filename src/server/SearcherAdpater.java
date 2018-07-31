package server;

import algorithm.BestFS;
import algorithm.Searchable;
import algorithm.Searcher;
import algorithm.Solution;

public class SearcherAdpater<T> implements Searcher<T> {

    Searcher<T> searcher = null;

    public SearcherAdpater(Integer N) {
        searcher = new BestFS<>();
        // searcher = new algorithm.BFS<T>();
    }

    @Override
    public Solution<T> search(Searchable<T> searchable) {
        return searcher.search(searchable);
    }

}
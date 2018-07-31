package server;

import algorithm.PipeGameSearchable;
import algorithm.MyState;
import algorithm.Solution;
import algorithm.State;

import java.util.ArrayList;

public class MySolver implements Solver {

    ArrayList<State<MyState>> boardState = new ArrayList<State<MyState>>();

    public String solve(String game) {
        String[] list = game.split("\n");
        char[][] board = new char[list.length][];

        State<MyState> startPoint = null;
        State<MyState> endPoint = null;

        for (int y = 0; y < list.length; y++) {
            for (int x = 0; x < list[y].length(); x++) {
                State<MyState> currentState = new State<MyState>();
                MyState pipelineState = new MyState(x, y, list[y].charAt(x), 0);
                currentState.setState(pipelineState);
                boardState.add(currentState);

                if (board[y] == null) {
                    board[y] = new char[list[y].length()];
                }

                board[y][x] = list[y].charAt(x);

                if (list[y].charAt(x) == 's') {
                    startPoint = currentState;
                } else if (list[y].charAt(x) == 'g') {
                    endPoint = currentState;
                }
            }
        }

        PipeGameSearchable searchable = new PipeGameSearchable(new PipeGameBoard(board));
        searchable.setInitialState(startPoint);
        searchable.setGoalState(endPoint);

        SearcherAdpater<MyState> searcher = new SearcherAdpater<>(board.length);
        Solution<MyState> solution = searcher.search(searchable);

        if (solution != null) {
            String moves = "";
            for (int i = solution.getSolution().size() - 2; i >= 1; i--) {
                MyState currentState = solution.getSolution().get(i);
                moves += currentState.getY() + "," + currentState.getX() + "," + currentState.getCost().toString();

                if (i != 1) {
                    moves += "\n";
                }
            }
            return moves;
        } else {
            return "Can't solve!";
        }
    }

    public static void main(String[] args) {
        MySolver mySolver = new MySolver();
        String level =  "s-7 \n"+
                        " |L7\n"+
                        "-F |\n"+
                        "7F-J\n"+
                        " g -";

        System.out.println(mySolver.solve(level));

    }
}

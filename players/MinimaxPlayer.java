package players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import snake.GameState;
import snake.Snake;
import static players.RandomPlayer.rand;

/**
 *
 * @author C1835576
 */

public class MinimaxPlayer extends RandomPlayer{

    Map<Position, Integer> positions;

    public MinimaxPlayer(GameState state, int index, Snake game) {
        super(state, index, game);
    }
    public void doMove(){

        initPositions();
        doRandomMove();
    }
    private Node searchTarget(){
        PriorityQueue<Node> frontier = new PriorityQueue();
        Set<Position> closed = new HashSet();
        frontier.add(new Node(state.getPlayerX(index).get(0), state.getPlayerY(index).get(0), state.getTargetX(), state.getTargetY()));
        while (!frontier.isEmpty()){
            Node n = frontier.poll();
            return n;
        }
        return null;
    }

//    private double getMinScore{
//
//    }
//    private double getMaxScore{
//
//    }
//    private double evaluateState{
//
//    }
//    private double getMinDistance{
//
//    }
    private void initPositions() {
        positions = new HashMap();
        for (int i = 0; i < state.getNrPlayers(); i++) {
            if (!state.isDead(i)) {
                List<Integer> xList = state.getPlayerX(i);
                List<Integer> yList = state.getPlayerY(i);
                for (int j = 0; j < xList.size(); j++) {
                    positions.put(new Position(xList.get(j), yList.get(j)), xList.size() - j);
                }
            }
        }
    }
}

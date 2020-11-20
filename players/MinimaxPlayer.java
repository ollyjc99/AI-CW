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

public class MinimaxPlayer extends AStarPlayer{

    Map<Position, Integer> positions;

    public MinimaxPlayer(GameState state, int index, Snake game) {
        super(state, index, game);
    }
    public void doMove(){

        initPositions();
        doRandomMove();
    }

        private double evaluateState(){
            if (state.isDead(index)){
                return -1;
            }
            return 0;
    }

    private int searchTarget(){
        PriorityQueue<Node> frontier = new PriorityQueue();
        Map<Position, Integer> closed = new HashMap();
        frontier.add(new Node(state.getPlayerX(index).get(0), state.getPlayerY(index).get(0), state.getTargetX(), state.getTargetY()));
        while (!frontier.isEmpty()){
            Node n = frontier.poll();
            Position[] potentialPos = {
                    new Position(n.x +1, n.y),
                    new Position(n.x - 1, n.y),
                    new Position(n.x, n.y +1),
                    new Position(n.x, n.y -1),
            };
            for (Position pos : potentialPos){
                if (pos.x == state.getTargetX() && pos.y == state.getTargetY())
                    return n.distanceTravelled + 1;

                if (state.isOccupied(pos.x, pos.y))
                    continue;

                Integer lowestDistanceTravelled = closed.get(pos);
                int newDistanceTravelled = n.distanceTravelled + 1;

                if (lowestDistanceTravelled == null || newDistanceTravelled < lowestDistanceTravelled){
                    frontier.add(new Node(pos.x, pos.y, n));
                    closed.put(pos, newDistanceTravelled);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

//    private double getMinScore{
//
//    }
//    private double getMaxScore{
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

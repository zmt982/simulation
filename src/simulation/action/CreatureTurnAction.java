package simulation.action;

import simulation.Coordinates;
import simulation.Map;
import simulation.entity.Creature;
import simulation.entity.Entity;

import java.util.List;
import java.util.Set;

abstract public class CreatureTurnAction extends Action {
    private final int DECREASE_HP_IN_1_TURN = 1;

    protected void creaturesTurns(Map map, Set<Coordinates> eatersCells, Set<Coordinates> foodsCells) {
        for (Coordinates eaterCell : eatersCells) {
            Entity eaterEntity = map.getEntity(eaterCell);
            Creature eater = (Creature) eaterEntity;

            boolean ateFood = false; // флаг, чтобы понять, поело ли существо

            for (Coordinates foodCell : foodsCells) {
                if (!map.isEmptyCell(foodCell) && map.isNeighbor(eaterCell, foodCell)) {
                    eater.eat(foodCell, map);
                    if (map.isEmptyCell(foodCell)) {
                        foodsCells.remove(foodCell);
                    }

                    ateFood = true; // существо поело, значит дальше не надо двигаться
                    break;
                }
            }

            if (!ateFood) {
                List<Coordinates> path = map.findPath(eaterCell, foodsCells);

                if (!path.isEmpty()) {
                    eater.makeMove(path, map);
                }
            }
            eater.decreaseHP(DECREASE_HP_IN_1_TURN);
        }
    }

    @Override
    abstract public void execute(Map map);
}

package simulation.action;

import simulation.Coordinates;
import simulation.Map;
import simulation.entity.Creature;
import simulation.entity.Entity;

import java.util.List;
import java.util.Set;

abstract public class CreatureTurnAction extends Action {
    static void creaturesTurns(Map map, Set<Coordinates> eatersCells, Set<Coordinates> foodsCells, boolean isHerbivore) {
        for (Coordinates eaterCell : eatersCells) {
            Entity eaterEntity = map.getEntity(eaterCell);
            Creature eater = (Creature) eaterEntity;

            for (Coordinates foodCell : foodsCells) {
                if (map.isNeighbor(eaterCell, foodCell)) {
                    if (isHerbivore) {
                        // если травоядное ест траву
                        eater.eat(foodCell, map);
                    } else {
                        // если хищник кусает травоядное
                        eater.eat(foodCell, map);
                    }

                    if (map.isEmptyCell(foodCell)) {
                        foodsCells.remove(foodCell);
                    }
                    break;
                } else {
                    List<Coordinates> path = map.findPath(eaterCell, foodsCells);

                    if (!path.isEmpty()) {
                        Coordinates destination = path.get(path.size() - 1);
                        eater.makeMove(destination, map);
                        break;
                    }
                }
            }
        }
    }

    @Override
    abstract public void execute(Map map);
}

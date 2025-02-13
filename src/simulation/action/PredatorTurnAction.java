package simulation.action;

import simulation.Coordinates;
import simulation.Map;
import simulation.entity.Creature;
import simulation.entity.Entity;
import simulation.entity.Herbivore;
import simulation.entity.Predator;

import java.util.List;
import java.util.Set;

public class PredatorTurnAction extends CreatureTurnAction {
    public void predatorTurn() {
        // получить из карты всех хищников
        // найти еду (получить из карты всех травоядных)
        // если на соседней клетке то кушать
        // или найти путь к еде
        // или сделать ход к еде
    }

    @Override
    public void execute(Map map) {
        Set<Coordinates> predatorsCells = map.findAllByType(Predator.class);
        Set<Coordinates> herbivoresCells = map.findAllByType(Herbivore.class);

        creaturesTurns(map, predatorsCells, herbivoresCells, false);
    }
}
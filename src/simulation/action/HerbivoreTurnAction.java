package simulation.action;

import simulation.Coordinates;
import simulation.Map;
import simulation.entity.Grass;
import simulation.entity.Herbivore;

import java.util.Set;

public class HerbivoreTurnAction extends CreatureTurnAction {
    // получить из карты всех травоядных
    //map.getHerbivores();
    // найти еду (получить из карты всю траву)
    // если на соседней клетке то кушать
    // или найти путь к траве
    // или сделать ход к траве

    @Override
    public void execute(Map map) {
        // получить из карты всех травоядных
        Set<Coordinates> herbivoresCells = map.findAllByType(Herbivore.class);
        // получить из карты все клетки с травой
        Set<Coordinates> grassesCells = map.findAllByType(Grass.class);

        // перебираем всех травоядных
        PredatorTurnAction.creaturesTurns(map, herbivoresCells, grassesCells, true);
    }
}
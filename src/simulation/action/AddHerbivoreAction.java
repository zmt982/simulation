package simulation.action;

import simulation.Map;
import simulation.Renderer;
import simulation.entity.Herbivore;

public class AddHerbivoreAction extends Action {
    @Override
    public void execute(Map map) {
        if (map.countEntities(Herbivore.class) == 0) {
            addEntities(map, Renderer.HERBIVORE, InitEntity.INIT_HERBIVORE_COUNT);
        }
    }
}
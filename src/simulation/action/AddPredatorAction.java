package simulation.action;

import simulation.Map;
import simulation.Renderer;
import simulation.entity.Predator;

public class AddPredatorAction extends Action {
    @Override
    public void execute(Map map) {
        if (map.countEntities(Predator.class) == 0) {
            addEntities(map, Renderer.PREDATOR, InitEntity.INIT_PREDATOR_COUNT);
        }
    }
}
package simulation.action;

import simulation.Map;
import simulation.Renderer;

public class AddPredatorAction extends Action {
    @Override
    public void execute(Map map) {
        addEntities(map, Renderer.PREDATOR, InitEntity.INIT_PREDATOR_COUNT);
    }
}
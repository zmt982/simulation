package simulation.action;

import simulation.Map;
import simulation.Renderer;

public class AddHerbivoreAction extends Action {
    @Override
    public void execute(Map map) {
        addEntities(map, Renderer.HERBIVORE, InitEntity.INIT_HERBIVORE_COUNT);
    }
}
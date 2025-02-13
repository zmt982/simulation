package simulation.action;

import simulation.Map;
import simulation.Renderer;

public class AddGrassAction extends Action {
    @Override
    public void execute(Map map) {
        addEntities(map, Renderer.GRASS, InitEntity.INIT_GRASS_COUNT);
    }
}
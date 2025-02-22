package simulation.action;

import simulation.Map;
import simulation.Renderer;
import simulation.entity.Grass;

public class AddGrassAction extends Action {
    @Override
    public void execute(Map map) {
        if (map.countEntities(Grass.class) == 0) {
            addEntities(map, Renderer.GRASS, InitEntity.INIT_GRASS_COUNT);
        }
    }
}
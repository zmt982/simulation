package simulation.action;

import simulation.Map;
import simulation.Renderer;

// расставить объекты и существ на карте
public class InitEntity extends Action {
    public static int INIT_GRASS_COUNT = 12;
    public static int INIT_HERBIVORE_COUNT = 6;
    public static int INIT_PREDATOR_COUNT = 3;
    public static int INIT_ROCK_COUNT = 6;
    public static int INIT_TREE_COUNT = 6;

    @Override
    public void execute(Map map) {
        addEntities(map, Renderer.GRASS, INIT_GRASS_COUNT);
        addEntities(map, Renderer.HERBIVORE, INIT_HERBIVORE_COUNT);
        addEntities(map, Renderer.PREDATOR, INIT_PREDATOR_COUNT);
        addEntities(map, Renderer.ROCK, INIT_ROCK_COUNT);
        addEntities(map, Renderer.TREE, INIT_TREE_COUNT);
    }
}
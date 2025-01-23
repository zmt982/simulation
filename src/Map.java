import entity.*;

import java.util.HashMap;

public class Map {
    java.util.Map<Coordinates, Entity> entities = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

//    public void removeEntity(Coordinates coordinates) {
//        entities.remove(coordinates);
//    }

    public void setupEntitiesStartPositions() {
        setEntity(new Coordinates(0, 1), new Grass(new Coordinates(0, 1)));
        setEntity(new Coordinates(0, 2), new Herbivore(new Coordinates(0, 2), 1, 5));
        setEntity(new Coordinates(0, 3), new Predator(new Coordinates(0, 3), 2, 10, 2));
        setEntity(new Coordinates(0, 4), new Rock(new Coordinates(0, 4)));
        setEntity(new Coordinates(0, 5), new Tree(new Coordinates(0, 5)));
    }
}
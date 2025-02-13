package simulation;

import simulation.entity.*;

public class EntityFactory {
    public static Entity createEntity (String type, Coordinates coordinates) {
        switch (type) {
            case "Grass":
                return new Grass(coordinates);
            case "Herbivore":
                return new Herbivore(coordinates);
            case "Predator":
                return new Predator(coordinates);
            case "Rock":
                return new Rock(coordinates);
            case "Tree":
                return new Tree(coordinates);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + type);
        }
    }
}

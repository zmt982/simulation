package simulation.entity;

import simulation.Coordinates;

abstract public class Entity {
    public Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
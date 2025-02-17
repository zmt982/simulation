package simulation.entity;

import simulation.Coordinates;
import simulation.Map;

// Стремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature {
    private static final int MIN_HERBIVORE_SPEED = 1;
    private static final int MAX_HERBIVORE_SPEED = 3;
    private static final int MIN_HERBIVORE_HP = 5;
    private static final int MAX_HERBIVORE_HP = 15;
    private final int INCREASE_HP_FOR_EAT_GRASS = 3;

    public Herbivore(Coordinates coordinates) {
        super(coordinates,
                MIN_HERBIVORE_SPEED, MAX_HERBIVORE_SPEED,
                MIN_HERBIVORE_HP, MAX_HERBIVORE_HP);
    }

    @Override
    public boolean isEatable(Entity entity) {
        return entity instanceof Grass;
    }

    @Override
    public void eat(Coordinates foodCoordinates, Map map) {
        Entity foodEntity = map.getEntity(foodCoordinates);
        if (isEatable(foodEntity)) {
            map.removeEntity(foodCoordinates);
            this.increaseHP(INCREASE_HP_FOR_EAT_GRASS);
        }
    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "speed=" + speed +
                ", HP=" + HP +
                ", coordinates " + coordinates +
                '}';
    }
}
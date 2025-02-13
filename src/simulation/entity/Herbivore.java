package simulation.entity;

import simulation.Coordinates;
import simulation.Map;

// Стремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature {
    public static final int HERBIVORE_SPEED = 3;
    public static final int MAX_HERBIVORE_HP = 10;
    public static final int INCREASE_HP_FOR_EAT_GRASS = 3;

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
        this.speed = HERBIVORE_SPEED;
        this.HP = MAX_HERBIVORE_HP;
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
            this.increaseHP(INCREASE_HP_FOR_EAT_GRASS, MAX_HERBIVORE_HP);
        }
    }

    @Override
    public String toString() {
        return "Herbivore{" + "INCREASE_HP_FOR_EAT_GRASS=" + INCREASE_HP_FOR_EAT_GRASS + ", speed=" + speed + ", HP=" + HP + ", coordinates " + coordinates + '}';
    }
}
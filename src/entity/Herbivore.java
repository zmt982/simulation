package entity;

// Стремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int HP) {
        super(coordinates, speed, HP);
    }
}
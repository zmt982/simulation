package entity;

import java.util.Map;

abstract public class Creature extends Entity {
    int speed; // сколько клеток может пройти за 1 ход
    int HP; // Hit Points (очки здоровья)

    public Creature(Coordinates coordinates, int speed, int HP) {
        super(coordinates);
        this.speed = speed;
        this.HP = HP;
    }

    public void makeMove(Map map) {

    }
}
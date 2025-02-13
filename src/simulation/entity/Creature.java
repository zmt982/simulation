package simulation.entity;

import simulation.Coordinates;
import simulation.CoordinatesShift;
import simulation.Map;

import java.util.HashSet;
import java.util.Set;

abstract public class Creature extends Entity {
    protected int speed; // сколько клеток может пройти за 1 ход
    protected int HP; // Hit Points (очки здоровья)
    private final int MIN_HP = 0;
    public static final int DECREASE_HP_FOR_1_TURN = 1;

    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    // получить перечень доступных ходов
    public Set<Coordinates> getAvailableMoveCells(Map map) {
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift shift : getCreaturesMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (isCellAvailableForMove(newCoordinates, map)) {
                    result.add(newCoordinates);
                }
            }
        }

        return result;
    }

    // доступно ли поле для перемещения в него
    public boolean isCellAvailableForMove(Coordinates coordinates, Map map) {
        Entity entity = map.getEntity(coordinates);
        return map.isEmptyCell(coordinates) || isEatable(entity);
    }

    // получить список всех передвижений в зависимости от скорости передвижения существа
    protected Set<CoordinatesShift> getCreaturesMoves() {
        Set<CoordinatesShift> result = new HashSet<>();

        for (int x = -speed; x <= speed; x++) {
            for (int y = -speed; y <= speed; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                if (Math.max(Math.abs(x), Math.abs(y)) <= speed) {
                    result.add(new CoordinatesShift(x, y));
                }
            }
        }

        return result;
    }

    public void makeMove(Coordinates to, Map map) {
        Set<Coordinates> availableSquares = getAvailableMoveCells(map);
        if (availableSquares.contains(to)) {
            map.removeEntity(coordinates);
            map.setEntity(to, this);
        }
    }

    public void increaseHP(int value, int max_HP) {
        HP = Math.min(HP + value, max_HP); // Ограничиваем HP максимумом
    }

    public void decreaseHP(int value) {
        HP = Math.max((HP - value), MIN_HP);
    }

    protected boolean isAlive() {
        return HP > MIN_HP;
    }

    public abstract boolean isEatable(Entity entity);

    public abstract void eat(Coordinates foodCoordinates, Map map);

    protected int getSpeed() {
        return speed;
    }

    protected int getHP() {
        return HP;
    }

    protected void setHP(int HP) {
        this.HP = HP;
    }
}
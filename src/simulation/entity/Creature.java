package simulation.entity;

import simulation.Coordinates;
import simulation.CoordinatesShift;
import simulation.Map;
import simulation.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract public class Creature extends Entity {
    protected int speed; // сколько клеток может пройти за 1 ход
    protected int HP; // Hit Points (очки здоровья)
    protected int INITIAL_HP;
    private final int MIN_HP = 0;
    public static final int DECREASE_HP_FOR_1_TURN = 1;

    public Creature(Coordinates coordinates, int minSpeed, int maxSpeed, int minHP, int maxHP) {
        super(coordinates);
        this.speed = Utils.getRandomIntInRange(minSpeed, maxSpeed);
        this.HP = Utils.getRandomIntInRange(minHP, maxHP);
        this.INITIAL_HP = this.HP;
    }

    // получить перечень доступных ходов
    public Set<Coordinates> getAvailableMoveCells(Map map) {
        Set<Coordinates> result = new HashSet<>();

        for (CoordinatesShift shift : getCreaturesMoves()) {
            if (coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if (map.isCellAvailableForMove(newCoordinates)) {
                    result.add(newCoordinates);
                }
            }
        }

        return result;
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

    public void makeMove(List<Coordinates> path, Map map) {
        Set<Coordinates> availableCellsForMove = getAvailableMoveCells(map);

        for (int i = path.size() - 1; i >= 0; i--) {
            Coordinates to = path.get(i);
            if (availableCellsForMove.contains(to)) {
                map.removeEntity(coordinates);
                map.setEntity(to, this);
                break;
            }
        }
    }

    public void increaseHP(int value) {
        this.HP = Math.min(this.HP + value, this.INITIAL_HP); // Ограничиваем HP максимумом
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
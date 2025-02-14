package simulation.entity;

import simulation.Coordinates;
import simulation.Map;

//В дополнение к полям класса Creature, имеет силу атаки. На что может потратить ход хищник:
//Переместиться (чтобы приблизиться к жертве - травоядному)
//Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
// Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature {
    public static final int PREDATOR_SPEED = 2;
    public static final int MAX_PREDATOR_HP = 15;
    private final int ATTACK_POWER = 3;

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.speed = PREDATOR_SPEED;
        this.HP = MAX_PREDATOR_HP;
    }

    @Override
    public boolean isEatable(Entity entity) {
        return entity instanceof Herbivore;
    }

    @Override
    public void eat(Coordinates foodCoordinates, Map map) {
        Entity foodEntity = map.getEntity(foodCoordinates);
        if (isEatable(foodEntity)) {
            Creature herbivore = (Creature) foodEntity;

            herbivore.decreaseHP(ATTACK_POWER);

            System.out.println(this.coordinates + " атаковал " + herbivore.coordinates.toString());

            if (!herbivore.isAlive()) {

                System.out.println(foodEntity.getClass().getSimpleName() + " удален с карты");

                map.removeEntity(foodCoordinates);
            }

            this.increaseHP(ATTACK_POWER, MAX_PREDATOR_HP);
        }
    }

    @Override
    public String toString() {
        return "Predator{" +
                "ATTACK_POWER=" + ATTACK_POWER +
                ", speed=" + speed +
                ", HP=" + HP +
                ", coordinates " + coordinates +
                '}';
    }
}
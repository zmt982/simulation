package simulation.entity;

import simulation.Coordinates;
import simulation.Map;
import simulation.Utils;

//В дополнение к полям класса Creature, имеет силу атаки. На что может потратить ход хищник:
//Переместиться (чтобы приблизиться к жертве - травоядному)
//Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
// Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature {
    private static final int MIN_PREDATOR_SPEED = 2;
    private static final int MAX_PREDATOR_SPEED = 5;
    private static final int MIN_PREDATOR_HP = 5;
    private static final int MAX_PREDATOR_HP = 15;
    private final int MIN_ATTACK_POWER = 2;
    private final int MAX_ATTACK_POWER = 4;
    private int attackPower;

    public Predator(Coordinates coordinates) {
        super(coordinates,
                MIN_PREDATOR_SPEED, MAX_PREDATOR_SPEED,
                MIN_PREDATOR_HP, MAX_PREDATOR_HP);
        this.attackPower = Utils.getRandomIntInRange(MIN_ATTACK_POWER, MAX_ATTACK_POWER);
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

            herbivore.decreaseHP(attackPower);

            System.out.println(this.coordinates + " атаковал " + herbivore.coordinates.toString());

            if (!herbivore.isAlive()) {

//                System.out.println(foodEntity.getClass().getSimpleName() + " удален с карты");

                map.removeEntity(foodCoordinates);
            }

            this.increaseHP(attackPower);
        }
    }

    @Override
    public String toString() {
        return "Predator{" +
                "speed=" + speed +
                ", HP=" + HP +
                ", attackPower=" + attackPower +
                ", coordinates " + coordinates +
                '}';
    }
}
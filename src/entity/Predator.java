package entity;

//В дополнение к полям класса Creature, имеет силу атаки. На что может потратить ход хищник:
//Переместиться (чтобы приблизиться к жертве - травоядному)
//Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
// Если значение HP жертвы опускается до 0, травоядное исчезает
public class Predator extends Creature {
    int attackPower;

    public Predator(Coordinates coordinates, int speed, int HP, int attackPower) {
        super(coordinates, speed, HP);
        this.attackPower = attackPower;
    }
}
import java.util.Random;

public class DiceRoll {
    public int roll_d4() {
        return new Random().nextInt(1,5);
    }

    public int roll_d6() {
        return new Random().nextInt(1,7);
    }

    public int roll_d8() {
        return new Random().nextInt(1,9);
    }

    public int roll_d10() {
        return new Random().nextInt(1,11);
    }

    public int roll_d20() {
        return new Random().nextInt(1,20);
    }

}

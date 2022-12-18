import java.util.HashMap;

public class Heroes_main {
    public static void main(String[] args) {
       Rouge r1 = new Rouge(8, 17,10,14,9, 12);
        System.out.println(r1.seeAttributes());
        System.out.println(r1.read_skills());
        System.out.println("Проверка знания магии: "+r1.skill_check(4));

        HashMap<Integer, String> h1 = r1.skill_names;

        System.out.println(h1.get(5));
    }
}

public class Heroes_main {
    public static void main(String[] args) {
       Rouge r1 = new Rouge(8, 17,10,14,9, 12);
        System.out.println(r1.seeAttributes());
        System.out.println("Проверка проворности: "+r1.skill_check(2));
    }
}

import java.util.HashMap;

public abstract class base_unit {

    // attributes
    private int strength;  private int strength_modifier;
    private int agility;   private int agility_modifier;
    private int constitution; private int constitution_modifier;
    private int intellect; private int intellect_modifier;
    private int wisdom;     private int wisdom_modifier;
    private int charisma;   private int charisma_modifier;

    private int hit_points;

    // реализовать систему бонусов и штрафов
    private int equip_skill_bonus=0;
    private int skill_penalties=0;
    private int attack;
    private int defence;


    /*------------------характеристики--и--расчёт-модификаторов-характеристик-----------------------------------*/
    private int set_attribute_modifier(int attribute){
        if (attribute>10)
            return  (attribute-10)/2;
        else if (attribute<10)
            return  -(10-attribute)/2;
        else return 0;
    }

    private int get_strength_modifier(){ return strength_modifier; }
    private int get_agility_modifier(){ return agility_modifier; }
    private int get_constitution_modifier(){ return constitution_modifier; }
    private int get_intellect_modifier(){ return intellect_modifier; }
    private int get_wisdom_modifier(){ return wisdom_modifier; }
    private int get_charisma_modifier(){ return charisma_modifier; }
    public int get_hp(){ return hit_points; }

    private void set_hit_points(){
        Dice dice = new Dice();
        if (constitution_modifier > 0)
            for (int i = 0; i < 3 + constitution_modifier; i++)
                hit_points += dice.roll_d8();
        else
            for (int i = 0; i < 3; i++)
                hit_points += dice.roll_d8();
    }
/*--------------------------------модификаторы-посчитаны----------------------------------------*/
/*---------------------------------конструктор--------------------------------------------------*/
    public base_unit(int strength, int agility, int constitution, int intellect, int wisdom, int charisma) {
        this.strength = strength;
        this.agility = agility;
        this.constitution = constitution;
        this.intellect = intellect;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.strength_modifier = set_attribute_modifier(strength);
        this.agility_modifier = set_attribute_modifier(agility);
        this.constitution_modifier = set_attribute_modifier(constitution);
        this.intellect_modifier = set_attribute_modifier(intellect);
        this.wisdom_modifier = set_attribute_modifier(wisdom);
        this.charisma_modifier = set_attribute_modifier(charisma);
        setSkill_names();
        set_hit_points();
    }
/*---------------------------------конец-конструктора-----------------------------------------------------------*/
/*----------------------------------------СКИЛЛЫ---------------------------------------------------------------*/
    private int[] skills_values = new int[11];
    protected HashMap<Integer, String> skill_names = new HashMap<>();

    private void setSkill_names(){
        skill_names.put(0, "Атлетика");
        skill_names.put(1, "Подвижность");
        skill_names.put(2, "Проворство");
        skill_names.put(3, "Скрытность");
        skill_names.put(4, "Знание магии");
        skill_names.put(5, "Знание мира");
        skill_names.put(6, "Знание природы");
        skill_names.put(7, "Знание религии");
        skill_names.put(8, "Внимание");
        skill_names.put(9, "Убеждение");
        skill_names.put(10, "Использование магических устройств");
    }
//    public String getSkillName(int skill_key) { return skills_values.get(skill_key); }
    public void increase_skill(int skill_key){
        skills_values[skill_key] ++;
    }
    public int skill_check(int skill_key){
        Dice dice = new Dice();
        int dice_roll = dice.roll_d20();
        int attribute_mod = choose_modifier(skill_key);
        return skills_values[skill_key]+attribute_mod+equip_skill_bonus+skill_penalties+dice_roll;
    }
    private int choose_modifier (int skill_key){
        if (skill_key == 0)
            return strength_modifier;
        else if (skill_key>0 && skill_key<4) {
            return agility_modifier;
        }
        else if (skill_key>3 && skill_key<6) {
            return intellect_modifier;
        }
        else if (skill_key>5 && skill_key<9) {
            return wisdom_modifier;
        }
        else
            return charisma_modifier;
    }
    private int getSkill (int skill_key){
        int attribute_mod = choose_modifier(skill_key);
        return skills_values[skill_key]+attribute_mod+ equip_skill_bonus + skill_penalties;
    }
    /*-------------------------------------СКИЛЛЫ-конец---------------------------------------------------------*/
    /*----------------------------------------ВЫВОД------------------------------------------------------------*/
    public String read_skills(){
        StringBuilder data = new StringBuilder();
        skill_names.forEach((k,v) -> data.append(v+": " + getSkill(k)+"\n"));
        return data.toString();
    }
    public String seeAttributes(){
        String HP = String.format("     Здоровье: %d \n\n", get_hp());
        String Str = String.format("        Сила: %d || %d\n",strength, get_strength_modifier());
        String Agi = String.format("    Ловкость: %d || %d\n",agility, get_agility_modifier());
        String Con = String.format("Выносливость: %d || %d\n",constitution, get_constitution_modifier());
        String Int = String.format("   Интеллект: %d || %d\n",intellect, get_intellect_modifier());
        String Wis = String.format("    Мудрость: %d || %d\n",wisdom, get_wisdom_modifier());
        String Chr = String.format("     Харизма: %d || %d\n",charisma, get_charisma_modifier());
        return HP+Str+Agi+Con+Int+Wis+Chr;
    }
    /*----------------------------------------конец-вывода-----------------------------------------------------------*/
    /*-----------------------------------------------урон-и-лечение--------------------------------------------------*/
    private void die() {
        System.out.println("ТЫ ПОГИБ");
    }
    private void take_damage (int enemy_damage){
        this.hit_points -= enemy_damage;
        if (hit_points <= 0)
            die();
        else
            System.out.println("ауч!");
    }
    public void face_attack(int enemy_attack, int enemy_damage){
        if (enemy_attack <= this.defence)
            System.out.println("Промах");
        else
            take_damage(enemy_damage);
    }

}

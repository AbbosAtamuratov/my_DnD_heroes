import java.util.HashMap;

public abstract class base_unit {

    // stats
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


    /*------------------характеристики--и--расчёт-модификаторов-характеристик-----------------------------------*/
    private void set_strength_modifier(){
        if (strength>10)
            strength_modifier = (strength-10)/2;
        else if (strength<10)
            strength_modifier = -(10-strength)/2;
        else strength_modifier = 0;
    }

    private void set_agility_modifier(){
        if (agility>10)
            agility_modifier = (agility-10)/2;
        else if (agility<10)
            agility_modifier = -(10-agility)/2;
        else agility_modifier = 0;
    }
    private void set_constitution_modifier(){
        if (constitution>10)
            constitution_modifier = (constitution-10)/2;
        else if (constitution<10)
            constitution_modifier = -(10-constitution)/2;
        else constitution_modifier = 0;
    }
    private void set_intellect_modifier(){
        if (intellect>10)
            intellect_modifier = (intellect-10)/2;
        else if (intellect<10)
            intellect_modifier = -(10-intellect)/2;
        else intellect_modifier = 0;
    }
    private void set_wisdom_modifier(){
        if (wisdom>10)
            wisdom_modifier = (wisdom-10)/2;
        else if (wisdom<10)
            wisdom_modifier = -(10-wisdom)/2;
        else wisdom_modifier = 0;
    }
    private void set_charisma_modifier(){
        if (charisma>10)
            charisma_modifier = (charisma-10)/2;
        else if (charisma<10)
            charisma_modifier = -(10-charisma)/2;
        else charisma_modifier = 0;
    }

    public int get_strength_modifier(){ return strength_modifier; }
    public int get_agility_modifier(){ return agility_modifier; }
    public int get_constitution_modifier(){ return constitution_modifier; }
    public int get_intellect_modifier(){ return intellect_modifier; }
    public int get_wisdom_modifier(){ return wisdom_modifier; }
    public int get_charisma_modifier(){ return charisma_modifier; }
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
        this.agility= agility;
        this.constitution = constitution;
        this.intellect = intellect;
        this.wisdom = wisdom;
        this.charisma = charisma;
        set_strength_modifier();
        set_agility_modifier();
        set_constitution_modifier();
        set_intellect_modifier();
        set_wisdom_modifier();
        set_charisma_modifier();
        set_hit_points();
    }
/*---------------------------------конец-конструктора-----------------------------------------------------------*/
/*----------------------------------------СКИЛЛЫ---------------------------------------------------------------*/
    private int[] skills_values = new int[11];
    private HashMap<Integer, String> skill_names = new HashMap<>();

    protected void setSkill_names(){
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
    //public String getSkillName(int skill_key) { return skills_values.get(skill_key);}
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
        skill_names.forEach((k,v) -> data.append(v+" - " + getSkill(k)+"\n"));
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
}

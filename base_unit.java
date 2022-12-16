public abstract class base_unit {

    // stats
    private int strength;
    private int agility;
    private int constitution;
    private int intellect;
    private int wisdom;
    private int charisma;

    private int strength_modifier;
    private int agility_modifier;
    private int constitution_modifier;
    private int intellect_modifier;
    private int wisdom_modifier;
    private int charisma_modifier;

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
    }

    public int get_strength_modifier(){
        return strength_modifier;
    }

    public int get_agility_modifier(){
        return agility_modifier;
    }
    public int get_constitution_modifier(){
        return constitution_modifier;
    }
    public int get_intellect_modifier(){
        return intellect_modifier;
    }
    public int get_wisdom_modifier(){
        return wisdom_modifier;
    }
    public int get_charisma_modifier(){
        return charisma_modifier;
    }

    @Override
    public String toString(){
        String Str = String.format("        Сила: %d || %d\n",strength, get_strength_modifier());
        String Agi = String.format("    Ловкость: %d || %d\n",agility, get_agility_modifier());
        String Con = String.format("Выносливость: %d || %d\n",constitution, get_constitution_modifier());
        String Int = String.format("   Интеллект: %d || %d\n",intellect, get_intellect_modifier());
        String Wis = String.format("    Мудрость: %d || %d\n",wisdom, get_wisdom_modifier());
        String Chr = String.format("     Харизма: %d || %d\n",charisma, get_charisma_modifier());
        return Str+Agi+Con+Int+Wis+Chr;
    }
}

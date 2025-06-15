package com.example.rpgteambuilder;

public abstract class Character {
    private int id;
    private String name;
    private String characterClass;
    private int str;    // Strength
    private int dex;    // Dexterity
    private int con;    // Constitution
    private int intel;  // Intelligence
    private int wis;    // Wisdom
    private int cha;    // Charisma
    private int spd;    // Speed
    private int lck;    // Luck
    private int sta;    // Stamina
    private int hp;     // Health Points
    private int mp;     // Magic Points

    public Character(int id, String name, String characterClass, int str, int dex, int con, int intel, int wis, int cha, int spd, int lck, int sta, int hp, int mp) {
        this.id = id;
        this.name = name;
        this.characterClass = characterClass;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        this.spd = spd;
        this.lck = lck;
        this.sta = sta;
        this.hp = hp;
        this.mp = mp;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getStr() { return str; }
    public int getDex() { return dex; }
    public int getCon() { return con; }
    public int getIntel() { return intel; }
    public int getWis() { return wis; }
    public int getCha() { return cha; }
    public int getSpd() { return spd; }
    public int getLck() { return lck; }
    public int getSta() { return sta; }
    public int getHp() { return hp; }
    public int getMp() { return mp; }

    public void setStr(int str) { this.str = str; }
    public void setDex(int dex) { this.dex = dex; }
    public void setCon(int con) { this.con = con; }
    public void setIntel(int intel) { this.intel = intel; }
    public void setWis(int wis) { this.wis = wis; }
    public void setCha(int cha) { this.cha = cha; }
    public void setSpd(int spd) { this.spd = spd; }
    public void setLck(int lck) { this.lck = lck; }
    public void setSta(int sta) { this.sta = sta; }
    public void setHp(int hp) { this.hp = hp; }
    public void setMp(int mp) { this.mp = mp; }

    // Summary for team overview (5-6 elements)
    public String toSummaryString() {
        String keyStat;
        switch (characterClass) {
            case "Warrior":
            case "Paladin":
            case "Barbarian":
                keyStat = "STR: " + str;
                break;
            case "Mage":
            case "Sorcerer":
            case "Necromancer":
                keyStat = "INT: " + intel;
                break;
            case "Rogue":
            case "Ranger":
            case "Monk":
                keyStat = "DEX: " + dex;
                break;
            case "Cleric":
            case "Druid":
                keyStat = "WIS: " + wis;
                break;
            case "Bard":
                keyStat = "CHA: " + cha;
                break;
            default:
                keyStat = "STA: " + sta;
        }
        return name + " (" + characterClass + ") - HP: " + hp + ", MP: " + mp + ", " + keyStat;
    }

    // Detailed view for all stats
    @Override
    public String toString() {
        return "Character: " + name + " (" + characterClass + ")\n" +
                "STR: " + str + "\n" +
                "DEX: " + dex + "\n" +
                "CON: " + con + "\n" +
                "INT: " + intel + "\n" +
                "WIS: " + wis + "\n" +
                "CHA: " + cha + "\n" +
                "SPD: " + spd + "\n" +
                "LCK: " + lck + "\n" +
                "STA: " + sta + "\n" +
                "HP: " + hp + "\n" +
                "MP: " + mp;
    }
}
package com.example.rpgteambuilder;

import java.util.Random;

public class Warrior extends Character {
    private static final Random rand = new Random();

    public Warrior(int id, String name) {
        super(id, name, "Warrior",
                rand.nextInt(6) + 15, // STR: 15-20
                rand.nextInt(6) + 10, // DEX: 10-15
                rand.nextInt(6) + 15, // CON: 15-20
                rand.nextInt(6) + 5,  // INT: 5-10
                rand.nextInt(6) + 5,  // WIS: 5-10
                rand.nextInt(6) + 5,  // CHA: 5-10
                rand.nextInt(6) + 10, // SPD: 10-15
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(11) + 20,// STA: 20-30
                rand.nextInt(21) + 30,// HP: 30-50
                0                     // MP: 0
        );
    }
}
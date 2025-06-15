package com.example.rpgteambuilder;

import java.util.Random;

public class Cleric extends Character {
    private static final Random rand = new Random();

    public Cleric(int id, String name) {
        super(id, name, "Cleric",
                rand.nextInt(6) + 5,  // STR: 5-10
                rand.nextInt(6) + 5,  // DEX: 5-10
                rand.nextInt(6) + 10, // CON: 10-15
                rand.nextInt(6) + 5,  // INT: 5-10
                rand.nextInt(6) + 15, // WIS: 15-20
                rand.nextInt(6) + 15, // CHA: 15-20
                rand.nextInt(6) + 5,  // SPD: 5-10
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(6) + 15, // STA: 15-20
                rand.nextInt(16) + 25,// HP: 25-40
                rand.nextInt(16) + 30 // MP: 30-45
        );
    }
}
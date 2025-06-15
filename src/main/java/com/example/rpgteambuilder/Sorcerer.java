package com.example.rpgteambuilder;

import java.util.Random;

public class Sorcerer extends Character {
    private static final Random rand = new Random();

    public Sorcerer(int id, String name) {
        super(id, name, "Sorcerer",
                rand.nextInt(6) + 5,  // STR: 5-10
                rand.nextInt(6) + 5,  // DEX: 5-10
                rand.nextInt(6) + 5,  // CON: 5-10
                rand.nextInt(6) + 15, // INT: 15-20
                rand.nextInt(6) + 10, // WIS: 10-15
                rand.nextInt(6) + 15, // CHA: 15-20
                rand.nextInt(6) + 5,  // SPD: 5-10
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(6) + 5,  // STA: 5-10
                rand.nextInt(11) + 20,// HP: 20-30
                rand.nextInt(21) + 50 // MP: 50-70
        );
    }
}

package com.example.rpgteambuilder;

import java.util.Random;

public class Rogue extends Character {
    private static final Random rand = new Random();

    public Rogue(int id, String name) {
        super(id, name, "Rogue",
                rand.nextInt(6) + 10, // STR: 10-15
                rand.nextInt(6) + 15, // DEX: 15-20
                rand.nextInt(6) + 5,  // CON: 5-10
                rand.nextInt(6) + 5,  // INT: 5-10
                rand.nextInt(6) + 5,  // WIS: 5-10
                rand.nextInt(6) + 10, // CHA: 10-15
                rand.nextInt(6) + 15, // SPD: 15-20
                rand.nextInt(6) + 10, // LCK: 10-15
                rand.nextInt(6) + 10, // STA: 10-15
                rand.nextInt(11) + 25,// HP: 25-35
                0                     // MP: 0
        );
    }
}
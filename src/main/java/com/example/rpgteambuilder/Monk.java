package com.example.rpgteambuilder;

import java.util.Random;

public class Monk extends Character {
    private static final Random rand = new Random();

    public Monk(int id, String name) {
        super(id, name, "Monk",
                rand.nextInt(6) + 10, // STR: 10-15
                rand.nextInt(6) + 15, // DEX: 15-20
                rand.nextInt(6) + 5,  // CON: 5-10
                rand.nextInt(6) + 5,  // INT: 5-10
                rand.nextInt(6) + 10, // WIS: 10-15
                rand.nextInt(6) + 5,  // CHA: 5-10
                rand.nextInt(6) + 15, // SPD: 15-20
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(11) + 20,// STA: 20-30
                rand.nextInt(11) + 25,// HP: 25-35
                0                     // MP: 0
        );
    }
}
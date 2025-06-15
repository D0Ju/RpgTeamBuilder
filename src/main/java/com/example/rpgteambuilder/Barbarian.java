package com.example.rpgteambuilder;

import java.util.Random;

public class Barbarian extends Character {
    private static final Random rand = new Random();

    public Barbarian(int id, String name) {
        super(id, name, "Barbarian",
                rand.nextInt(6) + 15, // STR: 15-20
                rand.nextInt(6) + 10, // DEX: 10-15
                rand.nextInt(6) + 15, // CON: 15-20
                rand.nextInt(6) + 5,  // INT: 5-10
                rand.nextInt(6) + 5,  // WIS: 5-10
                rand.nextInt(6) + 5,  // CHA: 5-10
                rand.nextInt(6) + 5,  // SPD: 5-10
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(11) + 25,// STA: 25-35
                rand.nextInt(21) + 35,// HP: 35-55
                0                     // MP: 0
        );
    }
}
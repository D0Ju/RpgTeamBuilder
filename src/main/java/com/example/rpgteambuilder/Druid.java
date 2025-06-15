package com.example.rpgteambuilder;

import java.util.Random;

public class Druid extends Character {
    private static final Random rand = new Random();

    public Druid(int id, String name) {
        super(id, name, "Druid",
                rand.nextInt(6) + 5,  // STR: 5-10
                rand.nextInt(6) + 5,  // DEX: 5-10
                rand.nextInt(6) + 15, // CON: 15-20
                rand.nextInt(6) + 10, // INT: 10-15
                rand.nextInt(6) + 15, // WIS: 15-20
                rand.nextInt(6) + 5,  // CHA: 5-10
                rand.nextInt(6) + 5,  // SPD: 5-10
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(6) + 15, // STA: 15-20
                rand.nextInt(16) + 25,// HP: 25-40
                rand.nextInt(16) + 30 // MP: 30-45
        );
    }
}
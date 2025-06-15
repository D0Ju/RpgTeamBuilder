package com.example.rpgteambuilder;

import java.util.Random;

public class Bard extends Character {
    private static final Random rand = new Random();

    public Bard(int id, String name) {
        super(id, name, "Bard",
                rand.nextInt(6) + 5,  // STR: 5-10
                rand.nextInt(6) + 15, // DEX: 15-20
                rand.nextInt(6) + 5,  // CON: 5-10
                rand.nextInt(6) + 10, // INT: 10-15
                rand.nextInt(6) + 5,  // WIS: 5-10
                rand.nextInt(6) + 15, // CHA: 15-20
                rand.nextInt(6) + 15, // SPD: 15-20
                rand.nextInt(6) + 10, // LCK: 10-15
                rand.nextInt(6) + 5,  // STA: 5-10
                rand.nextInt(11) + 20,// HP: 20-30
                rand.nextInt(11) + 20 // MP: 20-30
        );
    }
}
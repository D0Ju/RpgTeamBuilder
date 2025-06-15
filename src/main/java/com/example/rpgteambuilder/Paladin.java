package com.example.rpgteambuilder;

import java.util.Random;

public class Paladin extends Character {
    private static final Random rand = new Random();

    public Paladin(int id, String name) {
        super(id, name, "Paladin",
                rand.nextInt(6) + 15, // STR: 15-20
                rand.nextInt(6) + 5,  // DEX: 5-10
                rand.nextInt(6) + 15, // CON: 15-20
                rand.nextInt(6) + 5,  // INT: 5-10
                rand.nextInt(6) + 10, // WIS: 10-15
                rand.nextInt(6) + 15, // CHA: 15-20
                rand.nextInt(6) + 5,  // SPD: 5-10
                rand.nextInt(6) + 5,  // LCK: 5-10
                rand.nextInt(6) + 15, // STA: 15-20
                rand.nextInt(21) + 30,// HP: 30-50
                rand.nextInt(11) + 10 // MP: 10-20
        );
    }
}
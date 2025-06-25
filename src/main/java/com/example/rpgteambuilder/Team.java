package com.example.rpgteambuilder;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private int userId;
    private String name;
    private List<Character> characters;

    public Team(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.characters = new ArrayList<>();
    }
    //nepotrebno sad
/*
    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }
*/
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public List<Character> getCharacters() { return characters; }
}
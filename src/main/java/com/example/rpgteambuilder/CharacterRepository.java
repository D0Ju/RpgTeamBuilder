package com.example.rpgteambuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepository {
    private DatabaseConnection dbConnection;



    public CharacterRepository() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public List<Character> getCharactersForTeam(int teamId) {
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT * FROM characters WHERE team_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String characterClass = rs.getString("character_class");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int str = rs.getInt("str");
                int dex = rs.getInt("dex");
                int con = rs.getInt("con");
                int intel = rs.getInt("intel");
                int wis = rs.getInt("wis");
                int cha = rs.getInt("cha");
                int spd = rs.getInt("spd");
                int lck = rs.getInt("lck");
                int sta = rs.getInt("sta");
                int hp = rs.getInt("hp");
                int mp = rs.getInt("mp");

                Character character;
                switch (characterClass) {
                    case "Warrior":
                        character = new Warrior(id, name);
                        break;
                    case "Mage":
                        character = new Mage(id, name);
                        break;
                    case "Rogue":
                        character = new Rogue(id, name);
                        break;
                    case "Cleric":
                        character = new Cleric(id, name);
                        break;
                    case "Ranger":
                        character = new Ranger(id, name);
                        break;
                    case "Paladin":
                        character = new Paladin(id, name);
                        break;
                    case "Sorcerer":
                        character = new Sorcerer(id, name);
                        break;
                    case "Bard":
                        character = new Bard(id, name);
                        break;
                    case "Druid":
                        character = new Druid(id, name);
                        break;
                    case "Monk":
                        character = new Monk(id, name);
                        break;
                    case "Barbarian":
                        character = new Barbarian(id, name);
                        break;
                    case "Necromancer":
                        character = new Necromancer(id, name);
                        break;
                    default:
                        continue;
                }
                // Set actual database values
                character.setStr(str);
                character.setDex(dex);
                character.setCon(con);
                character.setIntel(intel);
                character.setWis(wis);
                character.setCha(cha);
                character.setSpd(spd);
                character.setLck(lck);
                character.setSta(sta);
                character.setHp(hp);
                character.setMp(mp);
                characters.add(character);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    public void createCharacter(int teamId, String name, String characterClass) {
        Character tempCharacter;
        switch (characterClass) {
            case "Warrior":
                tempCharacter = new Warrior(0, name);
                break;
            case "Mage":
                tempCharacter = new Mage(0, name);
                break;
            case "Rogue":
                tempCharacter = new Rogue(0, name);
                break;
            case "Cleric":
                tempCharacter = new Cleric(0, name);
                break;
            case "Ranger":
                tempCharacter = new Ranger(0, name);
                break;
            case "Paladin":
                tempCharacter = new Paladin(0, name);
                break;
            case "Sorcerer":
                tempCharacter = new Sorcerer(0, name);
                break;
            case "Bard":
                tempCharacter = new Bard(0, name);
                break;
            case "Druid":
                tempCharacter = new Druid(0, name);
                break;
            case "Monk":
                tempCharacter = new Monk(0, name);
                break;
            case "Barbarian":
                tempCharacter = new Barbarian(0, name);
                break;
            case "Necromancer":
                tempCharacter = new Necromancer(0, name);
                break;
            default:
                throw new IllegalArgumentException("Unknown character class: " + characterClass);
        }

        String sql = "INSERT INTO characters (team_id, name, character_class, str, dex, con, intel, wis, cha, spd, lck, sta, hp, mp) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            stmt.setString(2, name);
            stmt.setString(3, characterClass);
            stmt.setInt(4, tempCharacter.getStr());
            stmt.setInt(5, tempCharacter.getDex());
            stmt.setInt(6, tempCharacter.getCon());
            stmt.setInt(7, tempCharacter.getIntel());
            stmt.setInt(8, tempCharacter.getWis());
            stmt.setInt(9, tempCharacter.getCha());
            stmt.setInt(10, tempCharacter.getSpd());
            stmt.setInt(11, tempCharacter.getLck());
            stmt.setInt(12, tempCharacter.getSta());
            stmt.setInt(13, tempCharacter.getHp());
            stmt.setInt(14, tempCharacter.getMp());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create character: " + e.getMessage());
        }
    }

    public void updateCharacter(int characterId, String newName, String newClass) {
        String sql = "UPDATE characters SET name = ?, character_class = ? WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setString(2, newClass);
            stmt.setInt(3, characterId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update character: " + e.getMessage());
        }
    }
    public int getCharacterCountForTeam(int teamId) {
        String sql = "SELECT COUNT(*) FROM characters WHERE team_id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
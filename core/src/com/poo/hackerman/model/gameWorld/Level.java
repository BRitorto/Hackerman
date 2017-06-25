package com.poo.hackerman.model.gameWorld;

import com.poo.hackerman.model.Managers.EntityManager;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Entity;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.CameraGuard;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Created by Sebastian on 7/6/17.
 */
public class Level {

    public static String newLine = System.getProperty("line.separator");
    private String filename;

    public Level(String filename){
        this.filename = filename;
    }

    public EntityManager getEntityManager() {

        String s = readFile(filename);

        String[] guards = s.split(" GUARDS" + newLine);
        String[] cameraguards = guards[guards.length - 1].split(" CAMERAGUARDS" + newLine);
        String[] computers = cameraguards[cameraguards.length - 1].split(" COMPUTERS" + newLine);
        String[] doors = computers[computers.length - 1].split(" DOORS" + newLine);
        String[] desks = doors[doors.length - 1].split(" DESKS" + newLine);
        String[] hackers = desks[desks.length - 1].split(" HACKER" + newLine);
        String[] maps = hackers[hackers.length - 1].split(" MAP");

        String map = maps[0];
        String[] mapRows = map.split("/" + newLine);

        LinkedList<Obstacle> obstacleList = new LinkedList<Obstacle>();
        LinkedList<Computer> computerList = new LinkedList<Computer>();
        LinkedList<EnemyCharacter> enemyList = new LinkedList<EnemyCharacter>();

        PlayerCharacter hacker = null;
        Door door = null;

        int guardIndex = 0, cameraIndex = 0, computerIndex = 0, doorIndex = 0, deskIndex = 0;
        int rowNumber = 0;

        for (String row : mapRows) {
            String[] cells = row.split(",");
            int cellNumber = 0;
            for (String cell : cells) {
                Position position = new Position( cellNumber * GameMap.CELL_SIZE + GameMap.CELL_SIZE/2,  rowNumber * GameMap.CELL_SIZE + GameMap.CELL_SIZE/2);
                if(cell.equals("WALL")) {
                    Direction direction = new Direction(0);
                    obstacleList.add(new Obstacle(position, direction, Obstacle.obstacleType.WALL));
                }
                else if(cell.equals("DOOR")) {
                    String[] properties = doors[doorIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    door = new Door(position, direction);
                }
                else if(cell.equals("GUARD")) {
                    String[] properties = guards[guardIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int velocity = Integer.valueOf(properties[1]);
                    int range = Integer.valueOf(properties[2]);
                    if (properties.length > 3) {
                        ArrayList<Position> instructions = new ArrayList<Position>();
                        for (int i = 3; i < properties.length-1; i += 2) {
                            instructions.add(new Position(Integer.valueOf(properties[i]) * GameMap.CELL_SIZE, Integer.valueOf(properties[i + 1]) * GameMap.CELL_SIZE));
                        }
                        enemyList.add(new Guard(position, direction, velocity, range, instructions));
                    } else {
                        enemyList.add(new Guard(position, direction, velocity, range));
                    }
                }
                else if(cell.equals("CAMERAGUARD")) {
                    String[] properties = cameraguards[cameraIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    Integer range = Integer.valueOf(properties[1]);
                    if (properties.length > 2) {
                        ArrayList<Direction> instructions = new ArrayList<Direction>();
                        for (int i = 2; i < properties.length; i++) {
                            instructions.add(new Direction(Integer.valueOf(properties[i])));
                        }
                        enemyList.add(new CameraGuard(position, direction, range, instructions));
                    } else {
                        enemyList.add(new CameraGuard(position, direction, range));
                    }
                }
                else if(cell.equals("PLAYER")) {
                    String[] properties = hackers[0].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    Integer velocity = Integer.valueOf(properties[1]);
                    hacker = new PlayerCharacter(position, direction, velocity);
                }
                else if(cell.equals("COMPUTER")) {
                    String[] properties = computers[computerIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int consecutiveHacks = Integer.valueOf(properties[1]);
                    computerList.add(new Computer(position, direction, consecutiveHacks));
                }

                else if(cell.equals("DESK")) {
                    String[] properties = desks[deskIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    obstacleList.add(new Obstacle(position, direction, Obstacle.obstacleType.DESK));
                }

                cellNumber++;
            }
            rowNumber++;
        }
        return new EntityManager(hacker,door,enemyList,computerList,obstacleList);
    }

    private String readFile(String filename) {
        File f = new File(filename);
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filename));
            return new String(bytes,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
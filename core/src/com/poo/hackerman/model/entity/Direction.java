package com.poo.hackerman.model.entity;

import com.poo.hackerman.model.gameWorld.GameMap;

import java.io.Serializable;

/**
 *
 */
public class Direction implements Serializable {

    public static final int UP = 0;
    public static final int UP_RIGHT = 1;
    public static final int RIGHT = 2;
    public static final int DOWN_RIGHT = 3;
    public static final int DOWN = 4;
    public static final int DOWN_LEFT = 5;          //new Direction(Direction.UP);
    public static final int LEFT = 6;
    public static final int UP_LEFT = 7;

    private static final int[][] dir = {{0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}};

    private int code;

    public Direction(int code) {
        this.code = code % 8;
    }

    public Direction getRight() {
        return new Direction((code + 1) % 8);           //al sumarle uno me da la proxima direccion a la derecha
    }

    public Direction getLeft() {
        return new Direction(Math.floorMod(code - 1, dir.length));
    }

    public int getCode(){
        return code;
    }

    public int[] getDir() {
        return dir[code];
    }

    public boolean isDiagonal(){
        return (code % 2) == 1;
    }

    /**
     *
     * @param p1 The first position
     * @param p2 The second position
     * @return Direction to take to get to p2 from p1
     */
    public static Direction directionBetween(Position p1, Position p2) {
        double[] dist = new double[8];
        for (int i = 0; i < dist.length; i++) {
            Position posAux = new Position(p1.getX() + dir[i][0] * GameMap.CELL_SIZE, p1.getY() + dir[i][1] * GameMap.CELL_SIZE);
            dist[i] = posAux.distanceOf(p2);
            System.out.println("dist " + dist[i] + "code " + i);
        }
        int minDist = minDouble(dist);
        return new Direction(minDist);
    }

    /**
     *
     * @param list
     * @return The lowest double in List
     */
    private static int minDouble(double[] list) {
        int minIndex = 0;
        double min = list[0];
        for(int i = 0; i < list.length; i++) {
            if(list[i] - min < 0) {
                min = list[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Direction aux = (Direction) obj;
        return getCode() == aux.getCode();
    }
    
    @Override
    public String toString()
    {
        return "Direction: [" + dir[code][0] + "," + dir[code][1] + "]";
    }

}

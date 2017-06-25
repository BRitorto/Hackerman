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
    public static final int DOWN_LEFT = 5;
    public static final int LEFT = 6;
    public static final int UP_LEFT = 7;
    private int code;

    private static final int[][] dir = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};

    public Direction(int code) {
        this.code = code % 8;
    }

    /**
     *
     * @param p1 the first position
     * @param p2 the second position
     * @return the direction to take to get to p2 from p1
     */

    public static Direction directionBetween(Position p1, Position p2) {
        double[] dist = new double[8];
        for (int i = 0; i < dist.length; i++) {
            Position posAux = new Position(p1.getX() + dir[i][0] * GameMap.CELL_SIZE, p1.getY() + dir[i][1] * GameMap.CELL_SIZE);
            dist[i] = posAux.distanceOf(p2);
        }
        int minDist = minDouble(dist);
        return new Direction(minDist);
    }

    /**
     *
     * @param list of doubles
     * @return the minimum double from the list
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

    /**
     *
     * Two directions are the same if they have the same code
     */

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

    public Direction getRight() {return new Direction((code + 1) % 8);}

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
}

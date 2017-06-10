package com.poo.hackerman.model.gameWorld;

public class OccupiedCellException extends RuntimeException {
    public OccupiedCellException()
    {
        super("The cell is occupied!");
    }
}

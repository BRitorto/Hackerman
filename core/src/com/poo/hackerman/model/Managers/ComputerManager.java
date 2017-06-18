package com.poo.hackerman.model.Managers;

import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;

import java.util.List;

/**
 * Created by cderienzo on 6/4/2017.
 */
public class ComputerManager {
    private List<Computer> computers;
    private Door myDoor;
    private int currentComputer;

    public ComputerManager(Door door, List<Computer> computers) {
        this.computers = computers;
        this.myDoor = door;
        currentComputer = 0;
        computers.get(currentComputer).setOn();

    }

    public void updateComputers() {
        if(computers.get(currentComputer).isHacked()){
            computers.get(currentComputer).setOff();
            if(currentComputer == computers.size()) {
                openDoor();
            }
            else{
                currentComputer++;
                computers.get(currentComputer).setOn();
            }
        }
    }

    private void openDoor() {
        myDoor.setOpen();
    }

}
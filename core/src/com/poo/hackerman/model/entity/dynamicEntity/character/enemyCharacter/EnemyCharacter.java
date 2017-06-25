package com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.GameCharacter;

/**
 * A character with a lantern
 */
public abstract class EnemyCharacter extends GameCharacter {

    private Light mylight;

    /**
     * @param position the initial enemy character's position
     * @param direction the initial direction it is facing towards
     * @param range its light's range
     * @param velocity the velocity in which it will move. If it is a camera, this parameter will be 0
     *
     */

    protected EnemyCharacter(Position position, Direction direction, int velocity, int range) {
        super(position, direction, velocity);
        mylight = new Light(range);
    }

    public abstract boolean hackerDetected();

    public Light getMylight() {
            return mylight;
        }

    public void setMylight(Light mylight) {
            this.mylight = mylight;
        }

}


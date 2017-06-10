package com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter;

import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.GameCharacter;

/**
 * A character with a lantern
 */
public abstract class EnemyCharacter extends GameCharacter {

    private Light mylight;

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


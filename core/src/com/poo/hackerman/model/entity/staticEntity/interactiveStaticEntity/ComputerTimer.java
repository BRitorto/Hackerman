package com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class ComputerTimer {

    // Cooldown times for characters
    private static final long HACKING_BASE = 40; // in ms


    // Time attributes for characters
    private long lastHackTime;

    public ComputerTimer() {
        lastHackTime = 0;
    }

    public long getLastHackTime() {
        return lastHackTime;
    }

    public void updateLastHackTime(long nowTime) {
        this.lastHackTime = nowTime;
    }

    public boolean hackTimePassed(long nowTime) {
        return (nowTime - this.getLastHackTime()) >= HACKING_BASE;
    }

}

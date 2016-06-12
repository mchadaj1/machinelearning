package com.example.hunterPreyPredator.agents;


import com.example.hunterPreyPredator.map.Position;
import java.util.Map;

/**
 * Created by mateusz on 14.04.16.
 */
public abstract class AgentBase implements Agent{
    private int number;

    private boolean active = true;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AgentBase(int number) {
        this.number = number;
    }

    public AgentBase(Map<String, String> map) {
        int a = Integer.parseInt(map.get(""));
    }

    @Override
    public Position nextStep(Map<String, Object> mapInfo) {
        return null;
    }

    @Override
    public void getLastMoveEffect() {

    }
    public AgentType getType(){return null;};

    @Override
    public void finishGame(Map<String, Object> mapInfo) {

    }
}

package com.example.hunterPreyPredator.agents;


import com.example.hunterPreyPredator.map.Position;

import java.util.Map;

/**
 * Created by mateusz on 08.04.16.
 */
public class Prey extends AgentBase {
    public Prey(int number) {
        super(number);
    }

    @Override
    public Position nextStep(Map<String, Object> mapInfo) {

        int x = 0;
        int y = 0;
        while(x == 0 && y == 0) {
            x = (int) (Math.random() * 3);
            x -= 1;
            y = (int) (Math.random() * 3);
            y -= 1;
        }
//        x=-1;
//        y=1;
        Position myPosition = (Position)mapInfo.get("position");
        Position newPosition = new Position(myPosition);
        newPosition.setX(myPosition.getX()+x);
        newPosition.setY(myPosition.getY()+y);

        return newPosition;
    }

    @Override
    public void getLastMoveEffect() {

    }

    @Override
    public AgentType getType() {
        return AgentType.PREY;
    }
}

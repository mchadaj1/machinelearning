package com.example.hunterPreyPredator.agents;

/**
 * Created by mateusz on 08.04.16.
 */


import com.example.hunterPreyPredator.map.Position;

import java.util.Map;

public interface Agent {

    public Position nextStep(Map<String, Object> mapInfo);
    public void getLastMoveEffect();
    public AgentType getType();
    public void finishGame(Map<String, Object> mapInfo);

}

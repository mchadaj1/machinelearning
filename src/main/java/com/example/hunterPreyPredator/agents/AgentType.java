package com.example.hunterPreyPredator.agents;

/**
 * Created by mateusz on 08.04.16.
 */
public enum AgentType {
    HUNTER,
    PREY,
    PREDATOR;
    /*
    returns true if one of the agents is hunter - they interact, hunter kills prey or predator eats hunter
     */
    public boolean interacts(AgentType agentType) {
        if(this != HUNTER && agentType != HUNTER) {
            return false;
        }
        return true;
    }

}

package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 16.05.16.
 */
public class StatisticDTO {

    public String name;
    int experiments;
    int simulations;
    Map<String,String> configurations = new HashMap<>();
    public List<List<Double>> preysKilled;

    public void insertParam(String key, String value) {
        configurations.put(key, value);
    }

    public int getExperiments() {
        return experiments;
    }

    public int getSimulations() {
        return simulations;
    }

    public Map<String, String> getConfigurations() {
        return configurations;
    }



    public List<List<Double>> earnedPoints;

    public void setSimulations(int simulations) {
        this.simulations = simulations;
    }

    public void setExperiments(int experiments) {
        this.experiments = experiments;
    }
}

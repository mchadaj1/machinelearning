package com.example.hunterPreyPredator;


import com.example.hunterPreyPredator.agents.AgentBase;
import com.example.hunterPreyPredator.agents.Predator;
import com.example.hunterPreyPredator.agents.Prey;
import com.example.entities.Statistic;
import com.example.hunterPreyPredator.map.MyMap;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 08.04.16.
 */

/**
 * Klasa wykonująca eksperymenty Łowca, Ofiary, Drapieżniki.
 */
public class ExperimentRunner {


    private int preys = 8;
    private int predators = 1;
    private int simuls = 1;
    private int width = 10;
    private int height = 10;
    private int range = 2;
    private int steps = 50;
    private List<AgentBase> agents;
    private MyMap myMap;
    private Long problemConfigId;
    private Long methodConfigId;
    private List<Statistic> statistics = new ArrayList<>();
    private Class<?> hunterClass;
    private OutputStream outputStream;
    private Map<String, String> methodAttributes;

    public ExperimentRunner(Map<String, String> attributes, Map<String, String> methodAttributes, Class<?> hunterClass, OutputStream outputStream){
        this.hunterClass = hunterClass;
        this.outputStream = outputStream;
        preys = Integer.parseInt(attributes.get("preys"));
        predators = Integer.parseInt(attributes.get("predators"));
        simuls = Integer.parseInt(attributes.get("simulations"));
        width = Integer.parseInt(attributes.get("width"));
        height = Integer.parseInt(attributes.get("height"));
        range = Integer.parseInt(attributes.get("range"));
        steps = Integer.parseInt(attributes.get("steps"));
        this.methodAttributes = methodAttributes;
    };

    public List<Statistic> getStatistics() {
        return statistics;
    }

    private List<Simulation> simulations = new ArrayList<>(simuls);

    public void init() throws IllegalAccessException, InstantiationException {
        agents = new ArrayList<>(predators+preys+1);
        AgentBase hunter = null;
        try {
            hunter = (AgentBase) hunterClass.getDeclaredConstructor(Map.class).newInstance(methodAttributes);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        agents.add(hunter);
        for (int i = 1; i <= preys; i++) {
            agents.add(new Prey(i));
        }
        for (int i = preys + 1; i <= predators+preys; i++ ) {
            agents.add(new Predator(i));
        }

    }


    public void run() throws IOException {

        for(int i = 1; i <= simuls ; i++ ) {
            outputStream.write(new String("simultaion: " + i + "\n").getBytes());
            Simulation simulation = new Simulation(steps,preys,predators,width,height, outputStream, range);
            simulation.init(agents);
            simulation.run();
            simulations.add(simulation);
            final int a = i;
            simulation.getStatistics().stream()
                    .forEach(b -> b.setSimulation_number(a));
            statistics.addAll(simulation.getStatistics());
        }


    }

}

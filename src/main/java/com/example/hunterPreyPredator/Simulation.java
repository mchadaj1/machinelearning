package com.example.hunterPreyPredator;


import com.example.hunterPreyPredator.agents.Agent;
import com.example.hunterPreyPredator.agents.AgentBase;
import com.example.entities.Statistic;
import com.example.hunterPreyPredator.map.MyMap;
import com.example.hunterPreyPredator.map.Position;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 08.04.16.
 */

/**
 * Klasa wykonująca symulację zadania Łowca, Ofiary, Drapieżniki.
 */
public class Simulation {

   private int steps;

    private int preys;
    private int predators;
    private int width = 10;
    private int height = 10;
    private int preysleft;
    private List<Statistic> statistics = new ArrayList<>();
    private OutputStream outputStream;

    public Simulation(int steps, int preys, int predators, int width, int height, OutputStream outputStream, int range) {
        this.steps = steps;
        this.preys = preys;
        this.preysleft = preys;
        this.predators = predators;
        this.width = width;
        this.height = height;
        this.outputStream = outputStream;
        this.range = range;
    }


    public List<Statistic> getStatistics() {
        return statistics;
    }

    private int range = 2;

    private List<AgentBase> agents;

    private MyMap map;



    public void init(List<AgentBase> agents) throws IOException {
        this.agents = agents;
        for(AgentBase agent :agents) {
            agent.setActive(true);
        }
        map = new MyMap(width,height,range, outputStream);
        map.init(preys,predators);
        map.showMapInFile();
    }
    public void run() throws IOException {

        for(int i = 1; i<= steps; i++) {

            outputStream.write(new String("Próba przestawienia łowcy\n").getBytes());
            AgentBase hunter = agents.get(0);
            final Map<String, Object> mapInfo = map.getMapInfo(0);
            Position wantedPosition = agents.get(0).nextStep(mapInfo);
            if (map.prepareMove(hunter.getNumber(), wantedPosition)) {
                map.insertNewPosition(hunter.getNumber(), wantedPosition);
                outputStream.write(new String("Przestawiono " + hunter.getNumber() + " na pozycje " + wantedPosition + "\n").getBytes());
            } else {
                map.takeOldPosition(hunter.getNumber());
            }

            map.move();
            Integer deleteAgentNumber = null;
            if ((deleteAgentNumber = map.concludeStep(true)) != null) {
                String agentType = agents.get(deleteAgentNumber).getType().toString();
                statistics.add(new Statistic(deleteAgentNumber, i, agentType));
                if (deleteAgentNumber == 0) {
                    //System.out.println("Hunter deleted");
                    agents.get(0).finishGame(map.getMapInfo(0));
                    return;
                } else {
                    //System.out.println("Usunięto agenta nr " + deleteAgentNumber);

                    agents.get(find(deleteAgentNumber)).setActive(false);
                    preysleft--;
                    if (preysleft == 0) {
                        agents.get(0).finishGame(map.getMapInfo(0));
                        statistics.add(new Statistic(-1, i, "all"));
                        return;

                    }

                }

            }
            map.showMapInFile();

            for (AgentBase agent : agents) {
                if (agent.getNumber() != 0) {
                    if (agent.isActive()) {
                        outputStream.write(new String("Próba przestawienia agenta " + agent.getNumber() + "\n").getBytes());
                        Position wantToStep = agent.nextStep(map.getMapInfo(agent.getNumber()));
                        if (map.prepareMove(agent.getNumber(), wantToStep)) {
                            map.insertNewPosition(agent.getNumber(), wantToStep);
                            outputStream.write(new String("Przestawiono " + agent.getNumber() + " na pozycje " + wantToStep + "\n").getBytes());
                        } else {
                            map.takeOldPosition(agent.getNumber());
                        }

                    }
                }
            }
            boolean noCollisions = false;
            while (noCollisions == false) {
                noCollisions = map.findCollisions();
            }

            map.move();

            deleteAgentNumber = null;
            if ((deleteAgentNumber = map.concludeStep(false)) != null) {
                String agentType = agents.get(deleteAgentNumber).getType().toString();
                statistics.add(new Statistic(deleteAgentNumber, i, agentType));
                if (deleteAgentNumber == 0) {
                    //System.out.println("Hunter deleted");
                    agents.get(0).finishGame(map.getMapInfo(0));
                    return;
                } else {
                    //System.out.println("Usunięto agenta nr " + deleteAgentNumber);

                    agents.get(find(deleteAgentNumber)).setActive(false);
                    preysleft--;
                    if (preysleft == 0) {
                        agents.get(0).finishGame(map.getMapInfo(0));
                        statistics.add(new Statistic(-1, i, "all"));
                        return;

                    }

                }

            }
            map.showMapInFile();

        }
        agents.get(0).finishGame(map.getMapInfo(0));
    }


    public int find(int AgentNumber) {
        for(int i = 0; i < agents.size(); i++) {
            if(agents.get(i).getNumber() == AgentNumber && agents.get(i).isActive())
                return i;
        }
        return -1;
    }

//    public void printAgents() {
//        for(int i = 0; i < agents.size(); i++) {
//            if(agents.get(i).isActive())
//                System.out.println(agents.get(i).getNumber());
//        }
//    }
}

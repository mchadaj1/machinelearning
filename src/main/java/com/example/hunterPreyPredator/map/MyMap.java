package com.example.hunterPreyPredator.map;

import com.example.hunterPreyPredator.agents.AgentType;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 08.04.16.
 */

/**
 * Klasa zarządzająca pozycjami agentów w środowisku.
 */
public class MyMap {

    private int width;
    private int height;
    private int range;
    private int hunterPoints = 0;
    private OutputStream outputStream;


    List<Position> positions;

    List<Position> newPositions;

    public class Pair {
        public int number;
        public AgentType type;

        public Pair(int number, AgentType type) {
            this.number = number;
            this.type = type;
        }
    }

    public MyMap(int width, int height, int range, OutputStream outputStream) {
        this.width = width;
        this.height = height;
        this.range = range;
        this.outputStream = outputStream;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void init(int preys, int predators) {
        positions = new ArrayList<>(predators + preys + 1);
        newPositions = new ArrayList<>(predators + preys + 1);

        //ustaw łowcę
        placeHunter();

        //ustaw ofiary i drapieżników
        placePreysAndPredators(preys, predators);

    }

    public void placeHunter() {
        positions.add(0, new Position(1, height, AgentType.HUNTER, 0));
        newPositions.add(0, new Position(1, height, AgentType.HUNTER, 0));
    }

    public void placePreysAndPredators(int preys, int predators) {

        int placeX = 0;
        int placeY = 1;

        int i;

        int minrows = (int) Math.ceil(Math.sqrt((double) (preys + predators)));
        for (i = 1; i <= preys; i++) {
            positions.add(i, new Position(width - placeX, placeY, AgentType.PREY, i));
            newPositions.add(i, new Position(width - placeX, placeY, AgentType.PREY, i));
            if (placeX < minrows - 1)
                placeX++;
            else {
                placeX = 0;
                placeY++;
            }

        }
        for (; i <= predators + preys; i++) {

            positions.add(i, new Position(width - placeX, placeY, AgentType.PREDATOR, i));
            newPositions.add(i, new Position(width - placeX, placeY, AgentType.PREDATOR, i));

            if (placeX < minrows - 1)
                placeX++;
            else {
                placeX = 0;
                placeY++;
            }
            ;

        }

    }


    /*
    Funkcja sprawdza czy można wykonać ruch
     */
    public boolean prepareMove(int agentNumber, Position newPosition) {
        if (newPosition.getX() < 1 || newPosition.getX() > width || newPosition.getY() < 1 || newPosition.getY() > height) {
            return false;
        }
        Position movedAgent = null;
        for (int i = 0; i < find(agentNumber); i++) {
            movedAgent = newPositions.get(i);
            if (newPosition.getX() == movedAgent.getX() && newPosition.getY() == movedAgent.getY()) {
                return newPosition.getAgentType().interacts(movedAgent.getAgentType());
            }

        }
        return true;
    }

    /*
    Funkcja wyszukuje kolizji na mapie. Jeżeli znajdzie kolizję to cofa jednego z agentów i zwraca false.
    Jeżeli nie znajdzie kolizji to zwraca true.
     */
    public boolean findCollisions() {

        Position firstAgent;
        Position secondAgent;
        for (int i = 0; i < newPositions.size(); i++) {
            firstAgent = newPositions.get(i);
            for (int j = i + 1; j < newPositions.size(); j++) {
                secondAgent = newPositions.get(j);
                if (firstAgent.getX() == secondAgent.getX() && firstAgent.getY() == secondAgent.getY()) {
                    if (firstAgent.getAgentType().interacts(secondAgent.getAgentType()) == false) {
                        newPositions.set(j, positions.get(j));
                        newPositions.set(i, positions.get(i));
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public Map<String, Object> getMapInfo(int agentNumber) {

        Map<String, Object> returnMap = new HashMap<>();

        Position myPosition = positions.get(find(agentNumber));

        List<Position> preys = new ArrayList<>();
        List<Position> predators = new ArrayList<>();
        Position hunter = new Position(0, 0, AgentType.HUNTER, 0);

        for (Position position : positions) {
            if (position == myPosition)
                continue;
            else if (myPosition.inRange(position, range)) {
                insertPosition(position, preys, predators, hunter);
            }
        }
        returnMap.put("range", range);
        returnMap.put("position", myPosition);
        returnMap.put("width", width);
        returnMap.put("height", height);
        returnMap.put("lastMovePoints", hunterPoints);
        if (!preys.isEmpty()) returnMap.put("preys", preys);
        if (!predators.isEmpty()) returnMap.put("predators", predators);
        returnMap.put("hunter", hunter);
        for (Position p : preys) {
            if (p.getY() - myPosition.getY() > range) {
                myPosition.inRange(p, range);
            }
            if (p.getX() - myPosition.getX() > range) {
                myPosition.inRange(p, range);
            }
        }

        return returnMap;
    }

    private void insertPosition(Position position, List<Position> preys, List<Position> predator, Position Hunter) {

        switch (position.getAgentType()) {
            case PREDATOR:
                predator.add(position);
                break;
            case PREY:
                preys.add(position);
                break;
            case HUNTER:
                Hunter.setX(position.getX());
                Hunter.setY(position.getY());
                Hunter.setAgentNumber(position.getAgentNumber());
                break;

        }
    }


    public void showMapInFile() throws IOException {
        for (int i = 0; i < positions.size(); i++) {
            outputStream.write(new String(i + " " + positions.get(i) + "\n").getBytes());
        }
        AgentType agentType;
        Pair pair;
        for (int i = height; i > 0; i--) {
            for (int j = 1; j <= width; j++) {
                if ((pair = checkPositionForShow(j, i)) != null)
                    outputStream.write(new String(positions.get(pair.number).getAgentNumber() + "").getBytes());
                else
                    outputStream.write(' ');
            }
            outputStream.write('\n');
        }
    }

    public Pair checkPositionForShow(int x, int y) {
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getX() == x && positions.get(i).getY() == y) {
                return new Pair(i, positions.get(i).getAgentType());
            }

        }
        return null;

    }

    public char getChar(AgentType agentType) {
        switch (agentType) {
            case PREDATOR:
                return 'P';
            case PREY:
                return 'Y';
            case HUNTER:
                return 'H';
        }
        return ' ';
    }

    public void move() {
        for (int i = 0; i < positions.size(); i++) {
            positions.set(i, newPositions.get(i));
        }
    }

    public void insertNewPosition(int agentNumber, Position newPosition) {
        newPositions.set(find(agentNumber), newPosition);
    }

    public void takeOldPosition(int agentNumber) {
        newPositions.set(find(agentNumber), positions.get(find(agentNumber)));
    }

    public Integer concludeStep(boolean updateStatistics) throws IOException {
        for (int i = 1; i < positions.size(); i++) {
            if (positions.get(0).getX() == positions.get(i).getX() && positions.get(0).getY() == positions.get(i).getY()) {
                if (positions.get(i).getAgentType() == AgentType.PREDATOR) {
                    outputStream.write(new String("Predator " + positions.get(i).getAgentNumber() + " ate Hunter").getBytes());
                    if (updateStatistics)
                        hunterPoints = 2;
                    return 0;
                } else {
                    int removedAgentNumber = positions.get(i).getAgentNumber();
                    outputStream.write(new String("Hunter killed " + removedAgentNumber + " Prey").getBytes());
                    if (updateStatistics)
                        hunterPoints = 1;
                    positions.remove(i);
                    newPositions.remove(i);
                    return removedAgentNumber;
                }

            }
        }
        if (updateStatistics)
            hunterPoints = 0;
        return null;
    }

    public int find(int agentNumber) {
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getAgentNumber() == agentNumber) {
                return i;
            }
        }
        return -1;
    }
}

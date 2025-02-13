package simulation;

import simulation.action.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        Renderer renderer = new Renderer();
        List<Action> initActions = Arrays.asList(
                new InitEntity()
        );

        // список действий на каждый ход
        List<Action> turnActions = Arrays.asList(
                new HerbivoreTurnAction(),
                new PredatorTurnAction(),
                new AddGrassAction(),
                new AddHerbivoreAction(),
                new AddPredatorAction()
        );
        Simulation simulation = new Simulation(map, renderer, initActions, turnActions);
        simulation.startSimulation();


    }
}
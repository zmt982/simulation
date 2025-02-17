package simulation;

import simulation.action.*;
import simulation.entity.Grass;
import simulation.entity.Herbivore;
import simulation.entity.Predator;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private Map map;
    private Renderer renderer;
    private int turnCounter = 0; // счетчик ходов
    private boolean isRunning = false;
    List<Action> initActions; // список действий перед стартом симуляции
    List<Action> turnActions; // список действий на каждый ход

    public Simulation(Map map, Renderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    // запустить бесконечный цикл симуляции и рендеринга
    public void startSimulation() {
        // Выполняем все действия перед стартом
        for (Action action : initActions) {
            action.execute(map);
        }
        renderer.render(map);
        System.out.println();
        isRunning = true;

        while (isRunning) {
//        while (turnCounter < 30) {
            nextTurn();
            System.out.println();
        }
    }

    // просимулировать и отрендерить один ход
    public void nextTurn() {
        turnCounter++;
        System.out.println("ход номер " + turnCounter);
        // Выполняем все действия на этот ход
        for (Action action : turnActions) {
            if (!(action instanceof AddGrassAction) &&
                    !(action instanceof AddHerbivoreAction) &&
                    !(action instanceof AddPredatorAction))
                action.execute(map);
        }

        // Если на карте нет травы, добавляем
        if (map.countEntities(Grass.class) == 0) {
            new AddGrassAction().execute(map);
        }

        // Если на карте нет травядных, добавляем
        if (map.countEntities(Herbivore.class) == 0) {
            new AddHerbivoreAction().execute(map);
        }

        // Если на карте нет хищников, добавляем
        if (map.countEntities(Predator.class) == 0) {
            new AddPredatorAction().execute(map);
        }

        renderer.render(map);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // приостановить бесконечный цикл симуляции и рендеринга
    public void pauseSimulation() {
        isRunning = false;
        System.out.println("The simulation is suspended");
    }

    public void resumeSimulation() {
        if (!isRunning) {
            System.out.println("The simulation resumed");
            isRunning = true;
            startSimulation();
        }
    }
}
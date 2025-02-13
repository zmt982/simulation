package simulation.action;

import simulation.Map;

abstract public class Action {

    abstract public void execute(Map map);

    protected void addEntities(Map map, String entityType, int initCount) {
        for (int i = 0; i < initCount; i++) {
            map.addNewEntity(entityType);
        }
    }

}
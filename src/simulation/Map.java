package simulation;

import simulation.entity.*;

import java.util.*;

public class Map {
    java.util.Map<Coordinates, Entity> entities = new HashMap<>();

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public boolean isEmptyCell(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public boolean isNeighbor(Coordinates coordinates, Coordinates neighbor) {
        int dX = Math.abs(coordinates.X - neighbor.X);
        int dY = Math.abs(coordinates.Y - neighbor.Y);
        return (dX <= 1 && dY <= 1) && !(dX == 0 && dY == 0);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public Entity addNewEntity(String type) {
        while (true) {
            int X = Utils.getRandomIntInRange(0, Renderer.MAP_X_SIZE);
            int Y = Utils.getRandomIntInRange(0, Renderer.MAP_Y_SIZE);
            Coordinates coordinates = new Coordinates(X, Y);
            if (isEmptyCell(coordinates)) {
                Entity entity = EntityFactory.createEntity(type, coordinates);
                setEntity(coordinates, entity);
                return entity;
            }
        }
    }

    public int countEntities(Class<? extends Entity> entityType) {
        int count = 0;

        for (Entity entity : entities.values()) {
            if (entityType.isInstance(entity)) {
                count++;
            }
        }

        return count;
    }

    public Set<Coordinates> findAllByType(Class<? extends Entity> entityType) {
        Set<Coordinates> result = new HashSet<>();

        for (Entity entity : entities.values()) {
            if (entityType.isInstance(entity)) {
                result.add(entity.coordinates);
            }
        }

        return result;
    }

    public List<Coordinates> findPath(Coordinates from, Set<Coordinates> foodsCells) {
        List<Coordinates> shortestPath = null;

        for (Coordinates foodCell : foodsCells) {
            List<Coordinates> path = bfsFindPath(from, foodCell);

            if (!path.isEmpty()) {
                if (shortestPath == null || path.size() < shortestPath.size()) {
                    shortestPath = path;
                }
            }
        }

        return shortestPath != null ? shortestPath : Collections.emptyList();
    }

    public List<Coordinates> bfsFindPath(Coordinates from, Coordinates to) {
            Queue<List<Coordinates>> queue = new LinkedList<>();
            Set<Coordinates> visited = new HashSet<>();

            queue.add(Collections.singletonList(from));
            visited.add(from);

            while (!queue.isEmpty()) {
                List<Coordinates> path = queue.poll();
                Coordinates last = path.get(path.size() - 1);

                // если дошли до цели - возвращаем путь
                if (last.equals(to)) {
                    return path;
                }

                // добавляем соседние клетки
                for (Coordinates neighbor : getNeighbors(last)) {
                    if (!visited.contains(neighbor) && (isEmptyCell(neighbor) || neighbor.equals(to))) {
                        visited.add(neighbor);
                        List<Coordinates> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }

            return Collections.emptyList(); // путь не найден
    }

    private List<Coordinates> getNeighbors(Coordinates coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        int[][] shifts = {
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}, // Вверх, вправо, вниз, влево
                {1, 1}, {1, -1}, {-1, -1}, {-1, 1} // Диагонали
        };

        for (int[] shift : shifts) {
            CoordinatesShift coordShift = new CoordinatesShift(shift[0], shift[1]);
            if (coordinates.canShift(coordShift)) {
                neighbors.add(coordinates.shift(coordShift));
            }
        }

        return neighbors;
    }

    public void setupEntitiesStartPositions() {
        setEntity(new Coordinates(0, 1), new Grass(new Coordinates(0, 1)));
        setEntity(new Coordinates(0, 2), new Herbivore(new Coordinates(0, 2)));
        setEntity(new Coordinates(0, 3), new Predator(new Coordinates(0, 3)));
        setEntity(new Coordinates(0, 4), new Rock(new Coordinates(0, 4)));
        setEntity(new Coordinates(0, 5), new Tree(new Coordinates(0, 5)));

        setEntity(new Coordinates(5, 5), new Predator(new Coordinates(5, 5)));
        setEntity(new Coordinates(4, 4), new Rock(new Coordinates(4, 4)));
        setEntity(new Coordinates(4, 5), new Rock(new Coordinates(4, 5)));
        setEntity(new Coordinates(4, 6), new Rock(new Coordinates(4, 6)));
        setEntity(new Coordinates(5, 4), new Rock(new Coordinates(5, 4)));
        setEntity(new Coordinates(6, 4), new Rock(new Coordinates(6, 4)));

        setEntity(new Coordinates(5, 2), new Herbivore(new Coordinates(5, 2)));
        setEntity(new Coordinates(8, 2), new Herbivore(new Coordinates(8, 2)));
        setEntity(new Coordinates(5, 1), new Grass(new Coordinates(5, 1)));
        setEntity(new Coordinates(8, 1), new Grass(new Coordinates(8, 1)));
    }
}
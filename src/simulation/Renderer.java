package simulation;

import simulation.entity.Entity;

import java.util.concurrent.TimeUnit;

public class Renderer {
    public static final int MAP_X_SIZE = 6;
    public static final int MAP_Y_SIZE = 20;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_SPRITE = "\uD83C\uDFFC";
    public static final String GRAY_SQUARE_BACKGROUND = "\u001B[48;5;240m"; // цвет фона

    public static final String GRASS = "Grass";
    public static final String HERBIVORE = "Herbivore";
    public static final String PREDATOR = "Predator";
    public static final String ROCK = "Rock";
    public static final String TREE = "Tree";
    public static final String GRASS_COLOR = "\u001B[32m"; // GREEN цвет травы
    public static final String HERBIVORE_COLOR = "\u001B[34m"; // BLUE цвет травоядного
    public static final String PREDATOR_COLOR = "\u001B[31m"; // RED цвет хищника
    public static final String ROCK_COLOR = "\u001B[30m"; // BLACK цвет камня
    public static final String TREE_COLOR = "\u001B[38;5;94m"; // BROWN цвет дерева

    public static final String GRASS_IMAGE = "\uD83C\uDF3F"; // изображение травы
    public static final String HERBIVORE_IMAGE = "\uD83D\uDC07"; // изображение травоядного
    public static final String PREDATOR_IMAGE = "\uD83E\uDD8A"; // изображение хищника
    public static final String ROCK_IMAGE = "\uD83D\uDC8E"; // изображение камня
    public static final String TREE_IMAGE = "\uD83C\uDF33"; // изображение дерева

    public void render(Map map) {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        for (int X = MAP_X_SIZE; X >= 0; X--) {
            String line = "";
            for (int Y = MAP_Y_SIZE; Y >= 0; Y--) {
                Coordinates coordinates = new Coordinates(X, Y);
                if (map.isEmptyCell(coordinates)) {
                    line += getEmptySprite();
                } else {
                    line += getEntitySprite(map.getEntity(coordinates));
                }
            }
            System.out.println(line);
        }
    }

    private String getUnicodeEntityImage(Entity entity) {
        return getChoice(entity, GRASS_IMAGE, HERBIVORE_IMAGE, PREDATOR_IMAGE, ROCK_IMAGE, TREE_IMAGE);
    }

    private String getUnicodeEntityColor(Entity entity) {
        return getChoice(entity, GRASS_COLOR, HERBIVORE_COLOR, PREDATOR_COLOR, ROCK_COLOR, TREE_COLOR);
    }

    private String getChoice(Entity entity, String chooseGrass, String chooseHerbivore, String choosePredator,
                             String chooseRock, String chooseTree) {
        switch (entity.getClass().getSimpleName()) {
            case GRASS:
                return chooseGrass;
            case HERBIVORE:
                return chooseHerbivore;
            case PREDATOR:
                return choosePredator;
            case ROCK:
                return chooseRock;
            case TREE:
                return chooseTree;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + entity.getClass().getSimpleName());
        }
    }

    private String getEmptySprite() {
        return colorizeSprite(EMPTY_SPRITE);
    }

    private String getEntitySprite(Entity entity) {
        String EntityColor = getUnicodeEntityColor(entity);
        String EntityImage = getUnicodeEntityImage(entity);
        return colorizeSprite(EntityColor + EntityImage);
    }

    private String colorizeSprite(String sprite) {
        return GRAY_SQUARE_BACKGROUND + sprite + ANSI_RESET;
    }
}
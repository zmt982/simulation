import entity.Coordinates;
import entity.Entity;

public class BoardConsoleRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_SPRITE = "\uD83C\uDFFB";
    public static final String GRAY_SQUARE_BACKGROUND = "\u001B[48;5;240m"; // цвет фона

    public static final String GRASS_COLOR = "\u001B[32m"; // GREEN цвет травы
    public static final String HERBIVORE_COLOR = "\u001B[34m"; // BLUE цвет травоядного
    public static final String PREDATOR_COLOR = "\u001B[31m"; // RED цвет хищника
    public static final String ROCK_COLOR = "\u001B[30m"; // BLACK цвет камня
    public static final String TREE_COLOR = "\u001B[38;5;94m"; // BROWN цвет дерева

    public static final String GRASS_IMAGE = "\uD83C\uDF3F"; // изображение травы
    public static final String HERBIVORE_IMAGE = "\uD83D\uDC0F"; // изображение травоядного
    public static final String PREDATOR_IMAGE = "\uD83E\uDD96"; // изображение хищника
    public static final String ROCK_IMAGE = "\uD83E\uDEE8"; // изображение камня
    public static final String TREE_IMAGE = "\uD83C\uDF33"; // изображение дерева

    public void render(Map map) {
        for (int X = 10; X >= 0; X--) {
            String line = "";
            for (int Y = 10; Y >= 0; Y--) {
                Coordinates coordinates = new Coordinates(X, Y);
                if (map.isSquareEmpty(coordinates)) {
                    line += getEmptySprite();
                } else {
                    line += getEntitySprite(map.getEntity(coordinates));
                }

            }
            System.out.println(line);
        }
    }

    private String getUnicodeEntityImage(Entity entity) {
        return getToChoose(entity, GRASS_IMAGE, HERBIVORE_IMAGE, PREDATOR_IMAGE, ROCK_IMAGE, TREE_IMAGE);
    }

    private String getUnicodeEntityColor(Entity entity) {
        return getToChoose(entity, GRASS_COLOR, HERBIVORE_COLOR, PREDATOR_COLOR, ROCK_COLOR, TREE_COLOR);
    }

    private String getToChoose(Entity entity, String chooseGrass, String chooseHerbivore, String choosePredator,
                               String chooseRock, String chooseTree) {
        switch (entity.getClass().getSimpleName()) {
            case "Grass":
                return chooseGrass;
            case "Herbivore":
                return chooseHerbivore;
            case "Predator":
                return choosePredator;
            case "Rock":
                return chooseRock;
            case "Tree":
                return chooseTree;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + entity.getClass().getSimpleName());
        }
    }

    private String getEmptySprite() {
        return colorizeSprite(EMPTY_SPRITE);
    }

    private String getEntitySprite(Entity entity) {
        String EntityImage = getUnicodeEntityImage(entity);
        String EntityColor = getUnicodeEntityColor(entity);
        return colorizeSprite(EntityColor + EntityImage);
    }

    private String colorizeSprite(String sprite) {
        return GRAY_SQUARE_BACKGROUND + sprite + ANSI_RESET;
    }
}
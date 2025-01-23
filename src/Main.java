public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        map.setupEntitiesStartPositions();

        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(map);

        int a = 123;
    }
}
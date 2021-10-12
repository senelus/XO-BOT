public class GameMap {
    private final int size;
    private final String[][] currentMapState;

    public GameMap(int size) {
        this.size = size;
        currentMapState = new String[size][size];
        for (int i =0; i < size; i++)
            for (int j =0; j < size; j++)
                currentMapState[i][j] = "_";
    }

    public void Update(int positionX, int positionY, String symbol) {
        currentMapState[positionX][positionY] = symbol;
    }

    public void DisplayOnTheScreen() {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < size; i++) {
            for (int j =0; j < size; j++)
                builder.append(currentMapState[i][j]);
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    public boolean IsCellEmpty(int positionX, int positionY) {
        return currentMapState[positionX][positionY].compareTo("_") == 0;
    }
}

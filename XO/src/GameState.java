import java.util.ArrayList;

public class GameState {
    private ArrayList<Player> players;
    public boolean IsAnyoneWin = false;
    private GameMap map;
    private int Size;
    private StateStructure[] lines;
    private StateStructure[] columns;
    private StateStructure MainDiagonal = new StateStructure();
    private StateStructure SecondaryDiagonal = new StateStructure();

    public void Start(int mapSize, ArrayList<Player> players) {
        this.players = players;
        map = new GameMap(mapSize);
        Size = mapSize;
        lines = new StateStructure[mapSize];
        columns = new StateStructure[mapSize];
        for (int i =0; i < mapSize; i++) {
            lines[i] = new StateStructure();
            columns[i] = new StateStructure();
        }
    }

    public void ReportVictory(Player player) {
        IsAnyoneWin = true;
        System.out.println("Игрок " + player.getName() + " победил!");
    }

    public void UpdateStateStructure(StateStructure structure, Player player) {
        if (structure.IsSequenceWinning) {
            if (structure.TheFirstElementEntered == null) {
                structure.TheFirstElementEntered = player.getSymbol();
                structure.SequenceCount++;
            }
            else if (structure.TheFirstElementEntered.compareTo(player.getSymbol()) == 0) {
                structure.SequenceCount++;
                if (structure.SequenceCount == Size) ReportVictory(player);
            }
            else structure.IsSequenceWinning = false;
        }
    }

    public GameMap getMap() {
        return map;
    }

    public int getSize() {
        return Size;
    }

    public StateStructure[] getLines() {
        return lines;
    }

    public StateStructure[] getColumns() {
        return columns;
    }

    public StateStructure getMainDiagonal() {
        return MainDiagonal;
    }

    public StateStructure getSecondaryDiagonal() {
        return SecondaryDiagonal;
    }
}

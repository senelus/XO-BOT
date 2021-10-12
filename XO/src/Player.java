public class Player {
    private final String Name;
    private final String symbol;

    public Player(String name, String symbol){
        Name = name;
        this.symbol = symbol;
    }

    public void GetMove(int positionX, int positionY, GameState state) {
        state.getMap().Update(positionX, positionY, symbol);
        StateStructure line = state.getLines()[positionX];
        StateStructure column = state.getColumns()[positionY];
        state.UpdateStateStructure(line, this);
        state.UpdateStateStructure(column, this);
        if (positionX == positionY && positionX == (int)(state.getSize() / 2)) {
            state.UpdateStateStructure(state.getMainDiagonal(),this);
            state.UpdateStateStructure(state.getSecondaryDiagonal(),this);
        }
        else if (positionX == positionY) state.UpdateStateStructure(state.getMainDiagonal(),this);
        else if ((state.getSize()- 1 - positionX) == positionY) state.UpdateStateStructure(state.getSecondaryDiagonal(),this);
    }

    public String getName() {
        return Name;
    }

    public String getSymbol() {
        return symbol;
    }
}

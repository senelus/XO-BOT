public class Bot extends Player{
    private GameState state;

    public Bot(String name, String symbol, GameState state) {
        super(name, symbol);
        this.state = state;
    }

    public void GetSmartMove() {
        StateStructure[] lines = state.getLines();
        StateStructure[] columnes = state.getColumns();
        int maxCountLines = 0;
        int potentialLinesIndex = 0;
        int maxCountColumnes = 0;
        int potentialColumnesIndex = 0;
        if (state.getMainDiagonal().SequenceCount == state.getSize() - 2) {
            SecondHelpMethod(true);
            return;
        }

        if (state.getSecondaryDiagonal().SequenceCount == state.getSize() - 2) {
            SecondHelpMethod(false);
            return;
        }
        for (int i = 0; i < state.getSize(); i++) {
            if (lines[i].SequenceCount == state.getSize() - 2) {
                HelpMethod(i, true);
                return;
            }
            if (columnes[i].SequenceCount == state.getSize() - 2) {
                HelpMethod(i, false);
                return;
            }
            if (state.getMap().IsCellEmpty((int)(state.getSize() / 2),(int)(state.getSize() / 2))) {
                GetMove((int)(state.getSize() / 2),(int)(state.getSize() / 2),state);
                return;
            }
            /*if (lines[i].IsSequenceWinning && columnes[i].IsSequenceWinning && state.getMap().IsCellEmpty(i,i)) {
                GetMove(i, i, state);
                return;
            }
            if (lines[i].IsSequenceWinning && columnes[state.getSize() - 1 - i].IsSequenceWinning  && state.getMap().IsCellEmpty(i,state.getSize() - 1 - i)) {
                GetMove(i, state.getSize() - 1 - i, state);
                return;
            }*/
            if (lines[i].IsSequenceWinning && lines[i].TheFirstElementEntered != null && lines[i].TheFirstElementEntered.equals(getSymbol()) && maxCountLines < lines[i].SequenceCount) {
                maxCountLines = lines[i].SequenceCount;
                potentialLinesIndex = i;
            }
            else if (columnes[i].IsSequenceWinning && columnes[i].TheFirstElementEntered != null && columnes[i].TheFirstElementEntered.equals(getSymbol()) && maxCountColumnes < columnes[i].SequenceCount) {
                maxCountColumnes = columnes[i].SequenceCount;
                potentialColumnesIndex = i;
            }
        }
        if (maxCountLines > maxCountColumnes) {
            HelpMethod(potentialLinesIndex, true);
            return;
        }
        else if (maxCountColumnes > maxCountLines) {
            HelpMethod(potentialColumnesIndex, false);
            return;
        }
        else {
            for (int i = 0; i < state.getSize(); i++)
                for (int j = 0; j < state.getSize(); j++)
                    if (state.getMap().IsCellEmpty(i,j)) {
                        GetMove(i,j,state);
                        return;
                    }
        }

    }

    private void HelpMethod(int index, boolean isLine) {
        int[] coords = null;
        for (int i = 0 ; i < state.getSize(); i++) {
            if (isLine && state.getMap().IsCellEmpty(index, i)) coords =  new int[] {index, i};
            else if (!isLine && state.getMap().IsCellEmpty(i, index)) coords =  new int[] {i, index};
        }
        if (coords != null) GetMove(coords[0], coords[1],state);
    }

    private void SecondHelpMethod(boolean isMainDiagonal) {
        for (int i = 0; i < state.getSize(); i++)
            if (isMainDiagonal && state.getMap().IsCellEmpty(i, i)) {
                GetMove(i, i, state);
                return;
            }
            else if (!isMainDiagonal && state.getMap().IsCellEmpty(i, state.getSize() - 1 - i)) {
                GetMove(i, state.getSize() - 1 - i, state);
                return;
            }
    }
}

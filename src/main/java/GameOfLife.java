import java.io.IOException;
import java.util.List;

public class GameOfLife extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        setInitialState(inputFile);
        byte[][] result = calculate(initialState, T, N);
        return resultToListOfStrings(result);
    }

    private byte[][] calculate(byte[][] start, int T, int N) {
        return null;
    }

}

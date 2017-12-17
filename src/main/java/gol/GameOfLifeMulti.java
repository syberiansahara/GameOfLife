package gol;

import java.io.IOException;
import java.util.List;

public class GameOfLifeMulti extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        return calculate(inputFile);
    }

    protected byte[][] calculate(byte[][] start, int T, int N) {
        byte[][] result = new byte[N][N];
        return result;
    }
}

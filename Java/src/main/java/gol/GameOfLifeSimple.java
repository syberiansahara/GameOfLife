package gol;

import java.io.IOException;
import java.util.List;

public class GameOfLifeSimple extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        return calculate(inputFile);
    }

    protected byte[][] calculate(byte[][] start, int T, int N) {
        byte[][] first = new byte[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                first[i][j] = start[i][j];
            }
        }
        byte[][] second = new byte[N][N];
        byte[][] tmp;
        for (int i = 0; i < T; i++) {
            iterate(first, second, N);
            tmp = first;
            first = second;
            second = tmp;
        }
        return first;
    }

    protected void iterate(byte[][] first, byte[][] second, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                second[i][j] = handleCell(first, i, j);
            }
        }
    }
}

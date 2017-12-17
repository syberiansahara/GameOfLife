package gol;

import java.io.IOException;
import java.util.List;

public class GameOfLifeSimple extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        return calculate(inputFile);
    }

    protected byte[][] calculate(byte[][] start, int T, int N){
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

    private void iterate(byte[][] first, byte[][] second, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int right = (j + 1) % N;
                int left = (j + N - 1) % N;

                int top = (i + N - 1) % N;
                int down = (i + 1) % N;

                int aliveCells =    first[top][left]  + first[top][j]   + first[top][right] +
                        first[i][left]                      + first[i][right] +
                        first[down][left] + first[down][j]  + first[down][right];
                second[i][j] = ((aliveCells == 3) || ((aliveCells == 2) && (first[i][j] == 1))) ? (byte) 1 : (byte) 0;
            }
        }
    }
}

package gol;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class GameOfLifeMulti extends ParentGOL implements IGameOfLife {
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

        Object monitor = new Object();

        RowHandler[] rowHandlers = new RowHandler[N];
//        rowHandlers[].start();
        
        for (int i = 0; i < T; i++) {
            iterate(first, second, N, rowHandlers);
            tmp = first;
            first = second;
            second = tmp;
        }
        return first;
    }

    protected void iterate(byte[][] first, byte[][] second, int N, RowHandler[] rowHandlers) {
        for (int i = 0; i < N; i++) {

        }
    }

    private class RowHandler extends Thread {
        private final Object monitor;

        public RowHandler(Object monitor) {
            this.monitor = monitor;
        }

        public void run() {
            for (int j = 0; j < N; j++) {
                GameOfLifeMulti.second[i][j] = handleCell(first, i, j);
            }
        }
    }
}

package gol;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameOfLifeMulti extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        return calculate(inputFile);
    }

    public byte[][] first;
    public byte[][] second;

    protected byte[][] calculate(byte[][] start, int T, int N) {
        first = new byte[N][N];
        second = new byte[N][N];

        try {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    first[i][j] = start[i][j];
                }
            }

            AtomicInteger[] monitors = new AtomicInteger[T + 1];
            for (int t = 0; t <= T; t ++) {
                monitors[t] = new AtomicInteger(0);
            }

            byte[][] tmp;

            for (int n = 0; n < N; n++) {
                RowHandler[] rowHandlers = new RowHandler[N];
                rowHandlers[n] = new RowHandler(n, monitors);
                rowHandlers[n].start();
            }

            for (int t = 0; t < T; t++) {
                synchronized (monitors[t + 1]) {
                    monitors[t + 1].wait();
                }
                tmp = first;
                first = second;
                second = tmp;
                synchronized (monitors[t]) {
                    monitors[t].notifyAll();
                }
            }
            return first;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private class RowHandler extends Thread {

        private final int myLineNumber;
        private final AtomicInteger[] monitors;

        public RowHandler(int myLineNumber, AtomicInteger[] monitors) throws InterruptedException {
            this.myLineNumber = myLineNumber;
            this.monitors = monitors;
        }

        public void run() {
            for (int t = 0; t < T; t++) {
                for (int j = 0; j < N; j++) {
                    second[myLineNumber][j] = handleCell(first, myLineNumber, j);
                }

                if (monitors[t].incrementAndGet() == N - 1) {
                    synchronized (monitors[t + 1]) {
                        monitors[t + 1].notify(); //only the master thread
                    }
                } else {
                    synchronized (monitors[t]) {
                        try {
                            monitors[t].wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

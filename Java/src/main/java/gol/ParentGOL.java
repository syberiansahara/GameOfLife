package gol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ninefoldcomplex on 17.12.2017.
 */
public abstract class ParentGOL {
    protected int N;
    protected int T;
    protected byte[][] initialState;

    protected void initialize(String inputFile) throws IOException {
        List<String> inputLines = Files.readAllLines(Paths.get(inputFile));
        N = Integer.valueOf(inputLines.get(0).split(" ")[0]);
        T = Integer.valueOf(inputLines.get(0).split(" ")[1]);
        initialState = getInitialState(inputLines);
    }

    protected byte[][] getInitialState(List<String> inputLines) {
        byte[][] initialState = new byte[N][N];
        for (int row = 1; row <= N; row++) {
            String[] line = inputLines.get(row).split("");
            for (int column = 0; column < N; column++) {
                initialState[row - 1][column] = Byte.valueOf(line[column]);
            }
        }
        return initialState;
    }

    protected List<String> calculate(String inputFile) throws IOException{
        initialize(inputFile);
        byte[][] resultingState = calculate(initialState, T, N);
        return resultToListOfStrings(resultingState);
    }

    protected abstract byte[][] calculate(byte[][] start, int T, int N);

    protected byte handleCell(byte[][] first, int i, int j) {
        int right = (j + 1) % N;
        int left = (j + N - 1) % N;

        int top = (i + N - 1) % N;
        int down = (i + 1) % N;

        int aliveCells = first[top][left] + first[top][j] + first[top][right] +
                first[i][left] + first[i][right] +
                first[down][left] + first[down][j] + first[down][right];
        return ((aliveCells == 3) || ((aliveCells == 2) && (first[i][j] == 1))) ? (byte) 1 : (byte) 0;
    }

    protected List<String> resultToListOfStrings(byte[][] resultingState) {
        List<String> result = new ArrayList<>();
        String line;
        for (int row = 0; row < N; row ++) {
            line = "";
            for (int column = 0; column < N; column++) {
                line += resultingState[row][column];
            }
            result.add(line);
        }
        return result;
    }
}

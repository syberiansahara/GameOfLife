import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by ninefoldcomplex on 17.12.2017.
 */
public class ParentGOL {
    protected int N;
    protected int T;
    protected byte[][] initialState;

    protected void setInitialState(String inputFile) throws IOException {
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
}

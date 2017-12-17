import java.io.IOException;
import java.util.List;

public class GameOfLife extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        setInitialState(inputFile);
        return null;
    }
}

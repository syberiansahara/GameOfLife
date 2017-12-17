import java.io.IOException;
import java.util.List;

public class GameOfLifeOneThread extends ParentGOL implements IGameOfLife {
    @Override
    public List<String> play(String inputFile) throws IOException {
        setInitialState(inputFile);
        return null;
    }
}

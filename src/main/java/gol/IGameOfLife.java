package gol;

import java.io.IOException;
import java.util.List;

public interface IGameOfLife {
    List<String> play(String inputFile) throws IOException;
}

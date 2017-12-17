import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IGameOfLifeTest {
    private IGameOfLife IGameOfLife;

    @Before
    public void before() {
        IGameOfLife = new GameOfLifeOneThread();
    }

    @Test
    public void testGame() throws Exception {
//        testOneGame("resources/input10000.txt", "resources/output10000.txt");
//        testOneGame("resources/input1000.txt", "resources/output1000.txt");
        testOneGame("resources/input100.txt", "resources/output100.txt");
        testOneGame("resources/input.txt", "resources/output.txt");
    }

    private void testOneGame(String inputFile, String expectedOutputFile) throws IOException {
        List<String> result = IGameOfLife.play(inputFile);
        assertEquals(readFile(expectedOutputFile), result);
    }

    private static List<String> readFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}

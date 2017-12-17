import gol.GameOfLifeMulti;
import gol.GameOfLifeSimple;
import gol.IGameOfLife;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ninefoldcomplex on 17.12.2017.
 */
public class Main {
    private final static double initialTime = System.nanoTime();
    private static final IGameOfLife simple = new GameOfLifeSimple();
    private static final IGameOfLife multi = new GameOfLifeMulti();
    public static final int EXPERIMENT_COUNT = 3;
    public static String[] inputFiles = {
//            "resources/input.txt"
            "resources/input1000.txt"
    };

    public static void main(String[] args) throws IOException {
        for (String inputFile : inputFiles) {
            System.out.printf("%18s%18s\n", "SIMPLE", "MULTI");
            Result result = experiment(inputFile, EXPERIMENT_COUNT);
            System.out.printf("%18f%18f\n", result.getSimpleTime(), result.getMultiTime());
        }
    }

    public static Result experiment(String inputFile, int count) throws IOException {
        ArrayList<Result> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(experiment(inputFile));
        }
        return Result.med(results);
    }

    public static Result experiment(String inputFile) throws IOException {
        double startTime;
        startTime = getElapsedTimeInNanoseconds();
        simple.play(inputFile);
        double simpleTime = getElapsedTimeInNanoseconds() - startTime;
        startTime = getElapsedTimeInNanoseconds();
        multi.play(inputFile);
        double multiTime = getElapsedTimeInNanoseconds() - startTime;
        return new Result(simpleTime, multiTime);
    }

    public static double getElapsedTimeInNanoseconds() {
        return System.nanoTime() - initialTime;
    }
}

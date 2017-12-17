import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ninefoldcomplex on 17.12.2017.
 */
public class Result {
    private final double simpleTime;
    private final double multiTime;

    public Result(double simpleTime, double multiTime) {
        this.simpleTime = simpleTime;
        this.multiTime = multiTime;
    }

    public double getSimpleTime() {
        return simpleTime;
    }

    public double getMultiTime() {
        return multiTime;
    }

    static Result med(Iterable<Result> results){
        ArrayList<Double> simpleTimes = new ArrayList<>();
        ArrayList<Double> multiTimes = new ArrayList<>();
        int med = 0;
        for (Result result : results){
            simpleTimes.add(result.simpleTime);
            multiTimes.add(result.multiTime);
            med++;
        }
        med = med / 2;
        Collections.sort(simpleTimes);
        Collections.sort(multiTimes);
        return new Result(simpleTimes.get(med), multiTimes.get(med));
    }
}

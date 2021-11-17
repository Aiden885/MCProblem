public class Estimator {
    /**
     * A tool help to evaluate the algorithm
     * @author Jikang Bai
     */
    int ConditionTried;
    int ConditionAdded;
    int bestPathLength;
    long startTime;
    long endTime;

    Estimator() {
        ConditionTried = 0;
        ConditionAdded = 0;
    }

    void Attempt() {
        ConditionTried += 1;
    }

    void Added() {
        ConditionAdded += 1;
    }

    void start() {
        startTime = System.nanoTime();
    }

    void finished() {
        endTime = System.nanoTime();
    }
}

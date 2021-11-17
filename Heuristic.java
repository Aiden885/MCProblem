public class Heuristic {
    /**
     * This class contain all the heuristic algorithm used
     * 
     * @author Jikang Bai
     */
    static int CalculateMethod0(Condition c, int k) {
        // return 0 for all the condition, which is equal to a BFS
        return 0;
    }

    static int CalculateMethod1(Condition c, int k) {
        // this is the algorithm used in text book, only useful when k = 3.
        return c.state[0][0] + c.state[1][0] - 2 * c.state[2][0];
    }

    static int CalculateMethod2(Condition c, int k) {
        // The final chosen one. The detail inference will be in the report.
        return c.state[2][0] == 1 ? (int) Math.ceil((c.state[0][0] + c.state[1][0] - k) / (k - 1.0)) * 2 + 1
                : (int) Math.ceil((c.state[0][0] + c.state[1][0] - k + 1) / (k - 1.0)) * 2 + 2;
    }
}

import java.util.Arrays;
import java.util.Stack;

public class CMDShower implements Shower {
    /**
     * This shower will show the result in CMD with each condition on the best
     * solution path
     * 
     * @author Jikang Bai
     */
    Stack<Condition> path;

    CMDShower() {
        path = new Stack<Condition>();
    }

    @Override
    public void show(Condition termination, Estimator est) {
        System.out.println("There are " + est.ConditionAdded + " conditions accessed in total");
        System.out.println("There are " + est.ConditionTried + " conditions found in total");
        System.out.println(
                "The search program takes " + (est.endTime - est.startTime) / 10000000.0 + " milliseconds in total");
        System.out.println("The best path is " + termination.g + " steps long");
        while (termination != null) {
            path.add(termination);
            termination = termination.parent;
        }
        System.out.println("_L__R_");
        while (!path.isEmpty()) {
            int[][] s = path.pop().state;
            System.out.println(
                    Arrays.toString(s[0]) + "\n" + Arrays.toString(s[1]) + "\n" + Arrays.toString(s[2]) + "\n");
        }

    }
}

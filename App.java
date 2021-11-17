import java.util.Comparator;
import java.util.Vector;

public class App {
    /**
     * The entrance of the whole program and the main structure of the algorithm
     * 
     * @author Jikang Bai
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int N = 5, k = 3;
        Condition origin = new Condition(N);
        Condition termination = new Condition();
        Vector<Condition> OpenMap = new Vector<Condition>();
        Vector<Condition> ClosedMap = new Vector<Condition>();
        ConditionComparator cmp = new ConditionComparator();
        Checker checker = new ImprovedChecker(N, k);
        Shower shower = new AnimationShower();
        Estimator est = new Estimator();

        est.start();
        OpenMap.add(origin);
        while (!OpenMap.isEmpty()) {
            Condition tmp = OpenMap.remove(0);
            est.Added();
            ClosedMap.add(tmp);
            if (tmp.equals(termination)) {
                termination = tmp;
                break;
            } else {
                Ruler ruler = new BasicRuler(tmp, k);
                while (ruler.hasRule()) {
                    Condition next = new Condition(tmp, ruler.nextRule());
                    if (checker.CheckLegal(next)) {
                        next.h = Heuristic.CalculateMethod1(next, k);
                        Condition inOpen = checker.getSameState(next, OpenMap);
                        Condition inClosed = checker.getSameState(next, ClosedMap);
                        // A station can in open map or closed map or not in both.
                        if (inOpen != null) {
                            if (next.g < inOpen.g) {
                                // better than the same station in open map, replace it.
                                OpenMap.remove(inOpen);
                                OpenMap.add(next);
                                OpenMap.sort(cmp);
                            }
                        } else if (inClosed != null) {
                            if (next.g < inClosed.g) {
                                // better than the same station in closed map, add it to open map and remove
                                // that in closed map
                                ClosedMap.remove(inClosed);
                                OpenMap.add(next);
                                OpenMap.sort(cmp);
                            }
                        } else {
                            // first accessed, add to open map
                            OpenMap.add(next);
                            OpenMap.sort(cmp);
                            est.Attempt();
                        }
                    }
                }
            }
        }
        est.finished();
        shower.show(termination, est);
    }
}

class ConditionComparator implements Comparator<Condition> {
    /**
     * The Condition with smaller f = g + h has higher priority we need to expand
     * that first
     */
    @Override
    public int compare(Condition o1, Condition o2) {
        return (o1.g + o1.h) - (o2.g + o2.h);
    }

}
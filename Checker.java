import java.util.Vector;

public interface Checker {
    /**
     * Checker is used to check whether a new condition is legal
     * or the new condition has been visited before.
     * Checker should provide the sufficient condition of the legality
     * @author Jikang Bai
     * @param c
     * @return
     */
    boolean CheckLegal(Condition c);
    Condition getSameState(Condition condition, Vector<Condition> map);
}

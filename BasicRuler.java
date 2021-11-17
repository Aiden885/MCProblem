import java.util.Iterator;
import java.util.Vector;

public class BasicRuler implements Ruler{
    /**
     * The basic method to generate translation rules
     * In this way many illegal rules may be generated
     * It requires a lot legal check later in Checker
     * @author Jikang Bai
     */
    Vector<Rule> rules;
    Iterator<Rule> itRules;

    BasicRuler(Condition c, int k) {
        rules = new Vector<Rule>();
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= k; j++) {
                rules.add(new Rule(i, j));
            }
        }
        itRules = rules.iterator();
    }
    @Override 
    public boolean hasRule() {
        return itRules.hasNext();
    }
    @Override
    public Rule nextRule() {
        return itRules.next();
    }
}



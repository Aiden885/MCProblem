import java.util.Iterator;
import java.util.Vector;

public class ImprovedRuler implements Ruler{
    /**
     * Improved version of ruler
     * This can guarantee that 
     *  1. people on land is between 0 and N
     *  2. The safety on board
     * This can correspondingly reduce the cost of checker
     * for checker play the role only after the new Condition object generated
     * @author ZhenYi Zhu
     */
    Vector<Rule> rules;
    Iterator<Rule> itRules;

    ImprovedRuler(Condition c, int k) {
        rules = new Vector<Rule>();
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= k; j++) {
                if ((c.state[2][0] == 1 && c.state[0][0] - i >= 0 && c.state[1][0] - j >= 0)
                        || (c.state[2][1] == 1 && c.state[0][1] - i >= 0 && c.state[1][1] - j >= 0)) {
                    if (i + j <= k && i >= j) {
                        rules.add(new Rule(i, j));
                    } else {
                        if (i + j <= k && i == 0) {
                            rules.add(new Rule(i, j));
                        }
                    }
                }
            }
        }
        itRules = rules.iterator();
    }

    public boolean hasRule() {
        return itRules.hasNext();
    }

    public Rule nextRule() {
        return itRules.next();
    }
}

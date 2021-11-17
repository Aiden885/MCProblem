public interface Ruler {
    /**
     * Ruler is used to generate transfer rules for M and C. i.e. how many M or C should be tranced.
     * Ruler provide the necessary condition for legal condition
     * @author Jikang Bai
     */
    public boolean hasRule();
    public Rule nextRule();
}

class Rule {
    int m;
    int c;

    Rule(int M, int C) {
        m = M;
        c = C;
    }
}

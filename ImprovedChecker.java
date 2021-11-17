public class ImprovedChecker extends BasicChecker{
    /**
     * This is used with the Improved ruler. For legal check it has fewer condition to judge
     * which can improve the performance
     * @author ZhenYi Zhu
     * @param N
     * @param k
     */
    ImprovedChecker(int N, int k) {
        super(N, k);
    }

    @Override
    public boolean CheckLegal(Condition condition) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (condition.state[j][i] < 0 || condition.state[j][i] > N) {
                    return false;
                }
            }
        }
        
        for (int i = 0; i < 2; i++) {
            if (condition.state[0][i] < condition.state[1][i] && condition.state[0][i] != 0) {
                return false;
            }
        }
        int m = Math.abs(condition.state[0][0] - condition.parent.state[0][0]);
        int c = Math.abs(condition.state[1][0] - condition.parent.state[1][0]);
        // The number of people on boat available?
        if (m + c <= 0 || m + c > this.k) {
            return false;
        }
        return true;
    }
    
}

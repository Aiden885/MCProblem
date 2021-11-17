public interface Shower {
    /**
     * Shower use the final condition got and trace back to get the whole best path
     * then show it.
     * 
     * @author Jikang Bai
     * @param c
     * @param est
     */
    public void show(Condition c, Estimator est);
}

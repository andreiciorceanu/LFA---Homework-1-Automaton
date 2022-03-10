public class Pair<I extends Number, I1 extends Number> {
    private Integer node1;
    private Integer node2;

    public Pair(Integer node1, Integer node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public int getNode1() {
        return node1;
    }

    public void setNode1(int node1) {
        this.node1 = node1;
    }

    public int getNode2() {
        return node2;
    }

    public void setNode2(int node2) {
        this.node2 = node2;
    }
}

public class edge {
    private final node target; // node tetangga yang dituju
    private final double weight; // jarak/bobot antar node asal ke target

    public edge(node target, double weight) {
        this.target = target;
        this.weight = weight;
    }

    public node getTarget() { // getter node target
        return target;
    }

    public double getWeight() { // getter weight
        return weight;
    }
}

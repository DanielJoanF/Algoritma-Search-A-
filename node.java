import java.util.Objects;

public class node implements Comparable<node> {
    private final String name;
    private double gCost;
    private double hCost;
    private double fCost;
    private node parent;

    public node(String name, double hCost) {
        this.name = name;
        this.hCost = hCost;
        this.gCost = Double.MAX_VALUE;
        this.fCost = Double.MAX_VALUE;
        this.parent = null;
    }

    // Getter Setter
    public String getName() {
        return name;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
        this.fCost = this.gCost + this.hCost;
    }

    public double getHCost() {
        return hCost;
    }

    public double getFCost() {
        return fCost;
    }

    public void setFCost(double fCost) {
        this.fCost = fCost;
    }

    public node getParent() {
        return parent;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }

    // Mengurutkan node di PriorityQueue berdasarkan nilai fCost terkecil
    @Override
    public int compareTo(node other) {
        return Double.compare(this.fCost, other.fCost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        node other = (node) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

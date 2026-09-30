import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class graph {
    // Menyimpan pemetaan nama node (String) ke objek node
    private final Map<String, node> nodes;

    // Menyimpan adjacency list: setiap node memiliki daftar edge (tetangga & bobot)
    private final Map<node, List<edge>> adjacencies;

    // Konstruktor
    public graph() {
        this.nodes = new HashMap<>();
        this.adjacencies = new HashMap<>();
    }

    // Method untuk menambahkan node ke graph (dengan nama dan hCost)
    public node addNode(String name, double hCost) {
        node n = new node(name, hCost);
        nodes.put(name, n);
        adjacencies.putIfAbsent(n, new ArrayList<>());
        return n;
    }

    // Method untuk menambahkan objek node ke graph
    public void addNode(node n) {
        if (n != null) {
            nodes.put(n.getName(), n);
            adjacencies.putIfAbsent(n, new ArrayList<>());
        }
    }

    // Menambahkan hubungan dua arah (undirected edge) antar dua node berdasarkan nama
    public void addEdge(String sourceName, String destName, double weight) {
        node source = nodes.get(sourceName);
        node dest = nodes.get(destName);
        if (source != null && dest != null) {
            adjacencies.get(source).add(new edge(dest, weight));
            adjacencies.get(dest).add(new edge(source, weight));
        }
    }

    // Menambahkan hubungan dua arah antar dua objek node
    public void addUndirectedEdge(node source, node destination, double weight) {
        if (source != null && destination != null) {
            addNode(source);
            addNode(destination);
            adjacencies.get(source).add(new edge(destination, weight));
            adjacencies.get(destination).add(new edge(source, weight));
        }
    }

    // Getter untuk mengambil Node berdasarkan namanya
    public node getNode(String name) {
        return nodes.get(name);
    }

    // Getter untuk mendapatkan tetangga dari sebuah node
    public List<edge> getNeighbors(node n) {
        return adjacencies.getOrDefault(n, new ArrayList<>());
    }

    public Map<String, node> getNodes() {
        return nodes;
    }
}

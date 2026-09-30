import java.util.List;

public class main {
    public static void main(String[] args) {
        // 1. Inisialisasi graph
        graph g = new graph();

        // 2. Menambahkan node berdasarkan nilai heuristic h(n) (Jarak garis lurus ke G)
        g.addNode("S", 80);
        g.addNode("A", 80);
        g.addNode("B", 60);
        g.addNode("C", 70);
        g.addNode("D", 85);
        g.addNode("E", 74);
        g.addNode("F", 70);
        g.addNode("G", 0);
        g.addNode("H", 40);
        g.addNode("J", 100);
        g.addNode("K", 30);
        g.addNode("L", 20);
        g.addNode("M", 70);

        // 3. Tambahkan semua Sisi/Jarak g(n) antar Node (Dua Arah)
        // Koneksi dari Node S
        g.addEdge("S", "A", 10);
        g.addEdge("S", "B", 25);
        g.addEdge("S", "C", 30);
        g.addEdge("S", "D", 35);
        g.addEdge("S", "E", 10);

        // Koneksi dari Node A & B
        g.addEdge("A", "B", 10);
        g.addEdge("A", "G", 90);
        g.addEdge("B", "F", 5);
        g.addEdge("B", "K", 50);

        // Koneksi dari Node C, D, & E
        g.addEdge("C", "H", 40);
        g.addEdge("D", "E", 15);
        g.addEdge("D", "H", 25);
        g.addEdge("D", "L", 52);
        g.addEdge("E", "J", 20);

        // Koneksi dari Node F, H, J
        g.addEdge("F", "K", 40);
        g.addEdge("H", "L", 25);
        g.addEdge("J", "M", 40);

        // Koneksi menuju Goal (G)
        g.addEdge("K", "G", 30);
        g.addEdge("L", "G", 40);
        g.addEdge("M", "G", 80);

        // 4. Setup Node Awal & Tujuan (Start & Goal)
        node start = g.getNode("S");
        node goal = g.getNode("G");

        // 5. Panggil Fungsi Pencarian A* Search
        AStarSearch searcher = new AStarSearch();
        List<node> path = searcher.findPath(g, start, goal);

        // 6. Tampilkan Hasil (Path + Total Cost)
        if (path != null) {
            System.out.println("Path: " + path);
            System.out.println("Total Cost: " + goal.getGCost());
        } else {
            System.out.println("Path tidak ditemukan.");
        }
    }
}

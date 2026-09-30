import java.util.List;
import java.util.stream.Collectors;

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
        node goal  = g.getNode("G");

        // 5. Panggil Fungsi Pencarian A* Search
        AStarSearch searcher = new AStarSearch();
        List<node> path      = searcher.findPath(g, start, goal);

        // 6. Tampilkan hasil dengan pemformatan jalur
        printResult(path, g);
    }

    // ── Pemformatan Hasil Pencarian ───────────────────────────────────────────
    private static void printResult(List<node> path, graph g) {
        System.out.println();
        System.out.println("  ============================================================");
        System.out.println("         HASIL PENCARIAN JALUR TERPENDEK  (A* Search)");
        System.out.println("  ============================================================");
        System.out.println();

        if (path == null || path.isEmpty()) {
            System.out.println("  Jalur tidak ditemukan.");
            System.out.println();
            return;
        }

        // -- Baris jalur dengan panah --
        String jalurPanah = path.stream()
                               .map(node::getName)
                               .collect(Collectors.joining("  ->  "));
        System.out.println("  Jalur   :  " + jalurPanah);
        System.out.println();

        // -- Rincian biaya tiap langkah --
        System.out.println("  Rincian biaya per langkah:");
        System.out.println("  +--------+--------+--------+--------+--------+");
        System.out.println("  | Dari   | Ke     | Bobot  | g(n)   | f(n)   |");
        System.out.println("  +--------+--------+--------+--------+--------+");

        for (int i = 0; i < path.size(); i++) {
            node current = path.get(i);
            if (i == 0) {
                System.out.printf(
                    "  | %-6s | %-6s | %-6s | %-6.0f | %-6.0f |\n",
                    "-", current.getName(), "-",
                    current.getGCost(), current.getFCost()
                );
            } else {
                node prev        = path.get(i - 1);
                double edgeWeight = getEdgeWeight(g, prev, current);
                System.out.printf(
                    "  | %-6s | %-6s | %-6.0f | %-6.0f | %-6.0f |\n",
                    prev.getName(), current.getName(),
                    edgeWeight, current.getGCost(), current.getFCost()
                );
            }
        }
        System.out.println("  +--------+--------+--------+--------+--------+");
        System.out.println();

        // -- Ringkasan --
        double totalCost = path.get(path.size() - 1).getGCost();
        System.out.println("  Ringkasan:");
        System.out.println("  Jalur Terpendek : " + jalurPanah);
        System.out.println("  Total Biaya     : " + totalCost);
        System.out.println("  Panjang Jalur   : " + (path.size() - 1) + " langkah");
        System.out.println();
        System.out.println("  ============================================================");
        System.out.println();
    }

    private static double getEdgeWeight(graph g, node from, node to) {
        for (edge e : g.getNeighbors(from)) {
            if (e.getTarget().equals(to)) return e.getWeight();
        }
        return 0;
    }
}

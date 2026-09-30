import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearch {
    private graph g;

    public AStarSearch() {
    }

    public AStarSearch(graph g) {
        this.g = g;
    }

    public List<node> search(String startName, String goalName) {
        if (g == null) return null;
        node startNode = g.getNode(startName);
        node goalNode = g.getNode(goalName);
        return search(g, startNode, goalNode);
    }

    public List<node> findPath(graph g, node startNode, node goalNode) {
        return search(g, startNode, goalNode);
    }

    public List<node> search(graph g, node startNode, node goalNode) {
        if (g == null || startNode == null || goalNode == null) {
            return null;
        }

        // Reset state node sebelum pencarian
        for (node n : g.getNodes().values()) {
            n.setGCost(Double.MAX_VALUE);
            n.setParent(null);
        }

        // PriorityQueue secara otomatis mengurutkan node berdasarkan fCost terkecil
        PriorityQueue<node> openSet = new PriorityQueue<>();
        Set<node> closedSet = new HashSet<>();

        // Inisialisasi node awal
        startNode.setGCost(0);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            // Ambil node dengan nilai fCost terendah
            node current = openSet.poll();

            // Jika sudah mencapai node tujuan, rekonstruksi jalur
            if (current.equals(goalNode)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            // Evaluasi setiap tetangga dari node saat ini
            for (edge e : g.getNeighbors(current)) {
                node neighbor = e.getTarget();

                // Hitung estimasi biaya gCost baru dari node saat ini ke tetangga
                double tentativeGCost = current.getGCost() + e.getWeight();

                // Jika menemukan jalur yang lebih baik ke tetangga
                if (tentativeGCost < neighbor.getGCost()) {
                    neighbor.setParent(current);
                    neighbor.setGCost(tentativeGCost); // fCost otomatis diperbarui
                    closedSet.remove(neighbor);

                    // Masukkan ke openSet jika belum ada, atau perbarui posisinya
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else {
                        // Refresh posisi elemen di PriorityQueue setelah nilai fCost berubah
                        openSet.remove(neighbor);
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null; // Jalur tidak ditemukan
    }

    // Metode internal untuk merekonstruksi rantai jalur dari Goal kembali ke Start
    private List<node> reconstructPath(node current) {
        List<node> path = new ArrayList<>();
        node temp = current;

        while (temp != null) {
            path.add(temp);
            temp = temp.getParent();
        }

        Collections.reverse(path); // Balik urutan agar dimulai dari Start ke Goal
        return path;
    }
}
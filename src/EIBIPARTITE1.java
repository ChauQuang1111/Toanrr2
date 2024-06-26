import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EIBIPARTITE {
    static InputReader reader = new InputReader(System.in);
    static StringBuilder stringBuffer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        int nTestCase = reader.nextInt();
        for (int i = 0; i < nTestCase; i++) {
            int nVertice = reader.nextInt();
            int mEdge = reader.nextInt();
            Vertex[] graph = readGraph(nVertice, mEdge);
            boolean isBipartite = isGraphBipartite(graph);
            stringBuffer.append(isBipartite ? "Yes" : "No").append("\n");
        }
        System.out.println(stringBuffer);
    }

    static boolean isGraphBipartite(Vertex[] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (!graph[i].visit) {
                if (!dfs(graph[i], true)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean dfs(Vertex v, boolean color) {
        v.visit = true;
        v.color = color;
        for (Vertex w : v.adjacentVertex) {
            if (!w.visit) {
                if (!dfs(w, !color) || w.color == v.color) {
                    return false;
                }
            } else if (w.color == v.color) {
                return false;
            }
        }
        return true;
    }

    static Vertex[] readGraph(int n, int m) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();

            vertices[u].addNeighbor(vertices[v]);
            vertices[v].addNeighbor(vertices[u]);
        }
        return vertices;
    }

    static class Vertex {
        private int id;
        private boolean visit;
        private boolean color; // true for one color, false for the other
        List<Vertex> adjacentVertex = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id + " ";
        }

        public void addNeighbor(Vertex v) {
            adjacentVertex.add(v);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex) {
                return ((Vertex) obj).id == id;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return id;
        }

    }

    static class InputReader {
        StringTokenizer tokenizer;
        BufferedReader reader;
        String token;
        String temp;

        public InputReader(InputStream stream) {
            tokenizer = null;
reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() throws IOException {
            return reader.readLine();
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    if (temp != null) {
                        tokenizer = new StringTokenizer(temp);
                        temp = null;
                    } else {
                        tokenizer = new StringTokenizer(reader.readLine());
                    }
                } catch (IOException e) {
                }
            }
            return tokenizer.nextToken();
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}

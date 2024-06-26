import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EIGREENCITY {
	static InputReader reader;
	static StringBuilder sb;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reader= new InputReader(System.in);
		sb= new StringBuilder();
		
		int nVertices = reader.nextInt();
		int root = reader.nextInt();
		Node[] graph = readGraph(nVertices);
		int count = 0;
		
		for (Node n : graph)
			if (n.adjecent.isEmpty()) {
				count++;
			}
		
		for (int i = 0; i<count; i++) {
			int id = reader.nextInt();
			int weight = reader.nextInt();
			
			graph[id].weight = weight;
		}
		dfs(graph[root]);
		for (Node n : graph) {
			sb.append(n).append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(Node n) {
		n.visited = true;
		for (Node m: n.adjecent) {
			if (!m.visited) {
				dfs(m);
				n.weight+=m.weight;
			}
		}
	}
	
	
	public static Node[] readGraph(int nVertices) {
		Node[] graph = new Node[nVertices];
		for (int i=0; i<nVertices; i++) {
			graph[i] = new Node(i);
		}
		
		for (int i=1; i<nVertices; i++) {
			int a = reader.nextInt();
			int b = reader.nextInt();
			
			graph[a].addAdjecent(graph[b]);
		}
		
		return graph;
	}
	
	
	static class Node {
		private int id;
		private int weight;
		private boolean visited;
		private List<Node> adjecent = new ArrayList<Node>();
		
		public Node(int id) {
			super();
			this.id = id;
		}
		public void addAdjecent(Node n) {
			adjecent.add(n);
		}
		@Override
		public String toString() {
			return id + " " + weight;
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

        public InputReader(FileInputStream stream) {
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

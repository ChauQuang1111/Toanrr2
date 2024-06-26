import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EIUMLMK2 {
	static InputReader reader;
	static StringBuilder sb;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reader= new InputReader(System.in);
		sb= new StringBuilder();
		
		int nVertices = reader.nextInt();
		Node[] graph = readGraph(nVertices);
		double price = reader.nextDouble();
		dfs(graph[0], price);
		for (Node n : graph) {
			sb.append(n).append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(Node n, double price) {
		n.visited = true;
		if (price <= n.maxPrice+0.01) {
			n.use = n.adjecent.size()+1;
		}
		price*=1.1;
		for (Node m: n.adjecent) {
			if (!m.visited) {
				if (price<=m.maxPrice+0.01) {
					n.use--;
					dfs(m, price);
				}
				//dfs(m, price);
			}
		}
	}
	
	
	public static Node[] readGraph(int nVertices) {
		Node[] graph = new Node[nVertices];
		for (int i=0; i<nVertices; i++) {
			graph[i] = new Node(i);
		}
		
		for (int i=0; i<nVertices-1; i++) {
			int a = reader.nextInt();
			int b = reader.nextInt();
			
			graph[a].addAdjecent(graph[b]);
		}
		for (Node n : graph) {
			n.maxPrice = reader.nextDouble();
		}
		
		return graph;
	}
	
	
	static class Node {
		private int id;
		private double maxPrice;
		private int use=0;
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
			return id + " " + use;
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

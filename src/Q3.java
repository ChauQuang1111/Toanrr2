import java.util.*;
import java.io.*;

public class Q3 {
	static StringBuffer sb = new StringBuffer();
	static InputReader reader = new InputReader(System.in);
	public static void main(String[] args) throws Exception {
		Pot[] graph = readGraph();
		dfs(graph[0]);
		for (Pot p : graph) {
			if (p.water != 0) {
				sb.append(p).append("\n");
			}
		}
		System.out.println(sb);
	}
	public static void dfs(Pot p) {
		p.visited = true;
		if (p.relationship.size() != 0) {
			double temp = p.water/p.relationship.size();
			p.water = 0;
			for (Pot o : p.relationship) {
				if (o.visited == false) {
					o.water+=temp;
					dfs(o);
				}
			}
		}
	}
	public static Pot[] readGraph() {
		int nPot = reader.nextInt();
		Pot[] graph = new Pot[nPot];
		
		for (int i=0; i< nPot; i++) {
			graph[i] = new Pot(i, reader.nextDouble());
		}
		
		for (int i=0; i<nPot-1; i++) {
			int a = reader.nextInt();
			int b = reader.nextInt();
			
			//graph[a].addRelationship(graph[b]);
			graph[b].addRelationship(graph[a]);
		}
		
		return graph;
	}
	
	static class Pot {
		int id;
		boolean visited;
		double water;
		List<Pot> relationship = new ArrayList<Pot>();
		public Pot(int id, double water) {
			this.id = id;
			this.water = water;
		}
		
		public void addRelationship(Pot p){
			relationship.add(p);
		}

		@Override
		public String toString() {
			return id + " " + water;
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
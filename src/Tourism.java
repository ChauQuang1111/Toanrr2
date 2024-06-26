import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tourism {

	static InputReader reader = new InputReader(System.in);
	static StringBuffer stringBuffer = new StringBuffer();
	static int count = 0;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		int n = reader.nextInt();
		int m = reader.nextInt();
		Vertex[] graph = readGraph(n, m);
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(i)) {
				bfs(graph[i]);
				stringBuffer.append(count + 1);
			}
		}
		System.out.println(stringBuffer);

	}

	static void bfs(Vertex v) {
		Queue<Vertex> q = new ArrayDeque<>();
		q.add(v);
		v.visit = true;
		while (!q.isEmpty()) {
			Vertex w = q.poll();
			for (Vertex x : w.adjecentVertices) {
				if (x.visit == false) {
					x.visit = true;
					x.level = w.level + 1;
					count = x.level;
					q.add(x);
				}

			}
		}
	}

	static Vertex[] readGraph(int numberOfVertice, int numberOfEdge) {
		Vertex[] vertices = new Vertex[numberOfVertice];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < numberOfEdge; i++) {
			int u = reader.nextInt();
			int v = reader.nextInt();
			map.put(v, u);
			vertices[u].addAdjecentVertices(vertices[v]);

		}
		for (int i = 0; i < numberOfVertice; i++) {
			vertices[i].adjecentVertices.sort((v1, v2) -> Integer.compare(v1.id, v2.id));

		}
		return vertices;
	}

	static class Vertex {
		private int id;
		private boolean visit;
		private int level;
		List<Vertex> adjecentVertices = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addAdjecentVertices(Vertex v) {
			adjecentVertices.add(v);
		}

		@Override
		public String toString() {
			stringBuffer.append(id).append(" ").append(level).append("\n");
			return stringBuffer.toString();
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

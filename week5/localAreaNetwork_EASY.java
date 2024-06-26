package week5;

import java.util.*;
import java.io.*;

public class localAreaNetwork_EASY {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();

		var nVertex = ni();
		var nEdges = nVertex - 1;

		Vertex[] vertices = readGraph(nVertex, nEdges);

		dfs(vertices[0], 0);

		System.out.println(maxWeight);
	}

	static int maxWeight;

	static void dfs(Vertex vertex, int check) {

		vertex.visited = true;

		if (check > maxWeight) {
			maxWeight = check;
		}

		for (var v : vertex.listVertex) {
			if (!v.vextex.visited) {

				dfs(v.vextex, check + v.weight);
			}
		}
	}

	static Vertex[] readGraph(int nVertex, int nEdges) {

		Vertex[] vertices = new Vertex[nVertex];

		for (var i = 0; i < nVertex; i++) {
			vertices[i] = new Vertex(i);
		}

		for (var i = 0; i < nEdges; i++) {
			var u = ni();
			var v = ni();
			var weight = ni();

			vertices[u].addVertex(vertices[v], weight);
			vertices[v].addVertex(vertices[u], weight);
		}

		for (var i : vertices) {
			i.listVertex.sort((s1, s2) -> {
				return Integer.compare(s1.vextex.id, s2.vextex.id);
			});
		}

		return vertices;
	}

	static class Vertex {
		public int id;

		public Vertex(int id) {
			super();
			this.id = id;
		}

		public boolean visited;
		public ArrayList<Edge> listVertex = new ArrayList<>();

		public void addVertex(Vertex vertex, int weight) {
			listVertex.add(new Edge(vertex, weight));
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
			return ((Integer) id).hashCode();
		}
	}

	static class Edge {
		public Vertex vextex;
		public int weight;

		public Edge(Vertex vextex, int weight) {
			super();
			this.vextex = vextex;
			this.weight = weight;
		}

	}

	static InputStream is = System.in;
	static byte[] inbuf = new byte[1 << 24];
	static int lenbuf = 0, ptrbuf = 0;

	static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	static double nd() {
		return Double.parseDouble(ns());
	}

	static char nc() {
		return (char) skip();
	}

	static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
}

package week2;

import java.io.*;
import java.util.*;

class ConnectedComponets3 {

	static int countVertices;
	static int sumDegree;
	static int countComponent;
	static int countEdges;
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();

		int nVertices = ni();
		int nEdges = ni();

		Vertex[] graph = readGraph(nVertices, nEdges);

		for (int i = 0; i < graph.length; i++) {
			if (!graph[i].visited) {				
				countVertices++;
				
				List<Vertex> components = new ArrayList<>();
				dfs(graph[i], components);
				
				countEdges = sumDegree / 2;
				sb.append(graph[i].id).append(" ").append(components.size()).append(" ").append(countEdges).append("\n");
				sumDegree = 0;
			}
		}
		System.out.println(sb);
	}
	
	static void dfs(Vertex v, List<Vertex> components) {
		v.visited = true;
		components.add(v);
		sumDegree += v.adjecentVertices.size();
		for (Vertex w : v.adjecentVertices) {
			if (w.visited == false) {
				dfs(w, components);
			}
		}
	}

	static Vertex[] readGraph(int nVertices, int nEdges) {
		Vertex[] vertices = new Vertex[nVertices];

		for (int i = 0; i < nVertices; ++i) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < nEdges; i++) {
			int a = ni();
			int b = ni();

			vertices[a].addAdjecentVertices(vertices[b]);
			vertices[b].addAdjecentVertices(vertices[a]);
		}

		for (int i = 0; i < nVertices; i++) {
			vertices[i].adjecentVertices.sort((v1, v2) -> {
				return v1.id - v2.id;
			});
		}
		return vertices;
	}

	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<>();
		public boolean visited;

		public Vertex(int id) {
			super();
			this.id = id;
		}

		public void addAdjecentVertices(Vertex vertex) {
			adjecentVertices.add(vertex);
		}

		public int getDegree() {
			return adjecentVertices.size();
		}

		@Override
		public String toString() {
			return id + " ";
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


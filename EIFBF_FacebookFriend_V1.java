package week4;

import java.io.*;
import java.util.*;

class EIFBF_FacebookFriend_V1 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int nVertices = ni();
		int nEdges = ni();

		Vertex[] graph = readGraph(nVertices, nEdges);
		List<Vertex> listLargestVertex = new ArrayList<>();

		for (int i = graph.length - 1; i > 0; i--) {
			if (!graph[i].visited) {
				dfs(graph[i]);
				listLargestVertex.add(graph[i]);
			}
		}
		
		listLargestVertex.sort((s1, s2) -> {
			return s1.id - s2.id;
		});

		for (var k : listLargestVertex) {
			sb.append(k.id).append(" ").append(k.totalMale).append(" ").append(k.totalFemale).append("\n");
		}
		System.out.println(sb);
	}

	static Vertex[] readGraph(int nVertices, int nEdges) {
		Vertex[] vertices = new Vertex[nVertices + 1];

		for (int i = 1; i <= nVertices; i++) {
			String gender = ns();
			vertices[i] = new Vertex(i, gender);
		}

		for (int i = 0; i < nEdges; i++) {
			int a = ni();
			int b = ni();

			vertices[a].addAdjecentVertex(vertices[b]);
			vertices[b].addAdjecentVertex(vertices[a]);
		}

		for (int i = 1; i < vertices.length; i++) {
			vertices[i].adjecentVertices.sort((s1, s2) -> {
				return s1.id - s2.id;
			});
		}
		return vertices;
	}

	static void dfs(Vertex v) {
		v.visited = true;

		if (v.gender.equals("Nam")) {
			v.totalMale++;
		} else {
			v.totalFemale++;
		}
		for (Vertex w : v.adjecentVertices) {
			if (w.visited == false) {
				dfs(w);
				v.totalMale += w.totalMale;
				v.totalFemale += w.totalFemale;
			}
		}
	}

	static class Vertex {
		public int id;
		public String gender;
		public List<Vertex> adjecentVertices = new ArrayList<Vertex>();
		public boolean visited;
		public int totalMale;
		public int totalFemale;

		public Vertex(int id, String gender) {
			super();
			this.id = id;
			this.gender = gender;
		}

		public void addAdjecentVertex(Vertex vertex) {
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

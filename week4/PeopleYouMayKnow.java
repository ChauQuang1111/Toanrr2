package week4;

import java.io.*;
import java.util.*;

class PeopleYouMayKnow {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int nVertices = ni();
		int nEdges = ni();

		Vertex[] graph = readGraph(nVertices, nEdges);
		int nodeU = ni();
		int query = ni();

		bfs(graph, nodeU);

		HashMap<Integer, List<Vertex>> map = new HashMap<Integer, List<Vertex>>();

		for (Vertex v : graph) {
			if (map.containsKey(v.level)) {
				map.get(v.level).add(v);
				continue;
			}
			
			//list vertex có cùng level
			List<Vertex> list = new ArrayList<>();
			 
			list.add(v);
			map.put(v.level, list);
		}

		for (int i = 0; i < query; i++) {
			int k = ni();
			if (map.containsKey(k)) {
				for (Vertex v : map.get(k)) {
					sb.append(v.id).append(" ");
				}
			} else {
				sb.append(-1);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static Vertex[] readGraph(int nVertices, int nEdges) {
		Vertex[] vertices = new Vertex[nVertices + 1];

		for (int i = 0; i <= nVertices; ++i) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < nEdges; ++i) {
			int a = ni();
			int b = ni();

			vertices[a].addAdjecentVertex(vertices[b]);
			vertices[b].addAdjecentVertex(vertices[a]);
		}

		for (int i = 0; i <= nVertices; ++i) {
			vertices[i].adjecentVertices.sort((s1, s2) -> {
				return s1.id - s2.id;
			});
		}
		return vertices;
	}

	static void bfs(Vertex[] v, int u) {
		Queue<Vertex> q = new ArrayDeque<>();
		q.add(v[u]);
		v[u].visited = true;
		v[u].level = 0;

		while (!q.isEmpty()) {
			Vertex w = q.poll();
			for (Vertex x : w.adjecentVertices) {
				if (!x.visited) {
					x.visited = true;
					q.add(x);
					x.level = w.level + 1;
				}
			}
		}
	}

	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<Vertex>();
		public boolean visited;
		public int level;

		public Vertex(int id) {
			super();
			this.id = id;
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

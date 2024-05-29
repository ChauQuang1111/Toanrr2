package week3;

import java.io.*;
import java.util.*;

class CayThuMuc {
	static StringBuilder res = new StringBuilder();
	static String dash = "---";

	public static void main(String[] args) {
		int n = ni();
		HashMap<String, Vertex> vertices = new HashMap<>();

		for (int i = 0; i < n - 1; i++) {
			String first = ns();
			String last = ns();

			if (!vertices.containsKey(first)) {
				vertices.put(first, new Vertex(first));
			}

			if (!vertices.containsKey(last)) {
				vertices.put(last, new Vertex(last));
			}

			Vertex u = vertices.get(first);
			Vertex v = vertices.get(last);

			u.addAdjacentVertex(v);
			v.addAdjacentVertex(u);
		}

		for (String key : vertices.keySet()) {
			vertices.get(key).adjacentVertices.sort((v1, v2) -> {
				return v1.id.compareToIgnoreCase(v2.id);
			});
		}

		String root = ns();
		String level = "-";

		dfs(vertices.get(root), level);

		System.out.println(res);
	}

	static void dfs(Vertex vertex, String level) {
		vertex.visited = true;

		res.append(level);
		res.append(vertex.id);
		res.append("\n");
		level = level.concat(dash);

		for (Vertex next : vertex.adjacentVertices) {
			if (!next.visited) {
				dfs(next, level);
			}
		}
	}

	static class Vertex {
		String id;
		List<Vertex> adjacentVertices = new ArrayList<>();
		boolean visited;

		public Vertex(String id) {
			this.id = id;
		}

		public void addAdjacentVertex(Vertex vertex) {
			if (adjacentVertices.contains(vertex)) {
				return;
			}

			this.adjacentVertices.add(vertex);
		}

		public int getDegree() {
			return adjacentVertices.size();
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
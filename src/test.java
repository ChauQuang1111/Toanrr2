import java.io.*;
import java.util.*;

class test {
	public static void main(String[] args) {
		int n = ni();
		int m = ni();

		Vertex[] vertices = readGraph(n, m);

		for (int i = 0; i < n; i++) {
			vertices[i].adjacentVertices.sort((v1, v2) -> v1.id - v2.id);
		}

		StringBuilder res = new StringBuilder();
		List<Vertex> list = dfs(vertices[0]);

		res.append(list.get(0).id).append(" ");
		
		for (int i = list.size() - 1; i > 0; --i) {
			res.append(list.get(i).id).append(" ");
//			System.out.println(list.get(i).id + " ");
		}

		System.out.println(res);
	}

	static ArrayList<Vertex> dfs(Vertex v) {
//		v.isVisited = true;
		ArrayList<Vertex> list;
		
		for (Vertex next : v.adjacentVertices) {
			 list = dfs(next);
			if(list == null) {
				v.isVisited = true;
			}else {
				list.add(v);
				return list;
			}
			if(v.id == 0) {
				return list = new ArrayList<>();
			}
		}

		return null;
	}

	static Vertex[] readGraph(int n, int m) {
		Vertex[] vertices = new Vertex[n];

		for (int i = 0; i < n; ++i) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < m; ++i) {
			Vertex u = vertices[ni()];
			Vertex v = vertices[ni()];

			u.addAdjacentVertex(v);
		}

		return vertices;
	}

	static class Vertex {
		int id;
		List<Vertex> adjacentVertices = new ArrayList<>();
		boolean isVisited;

		public Vertex(int id) {
			this.id = id;
		}

		public void addAdjacentVertex(Vertex vertex) {
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

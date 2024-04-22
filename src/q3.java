import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class q3 {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Vertex[] vertices = readGraph();
		for (Vertex vertex : vertices) {
			ArrayList<Vertex> list = new ArrayList<>();
			int nOfVertices = 0;
			int nOfE = 0;
			int degree = 0;
			int ans = 0;
			if (!vertex.visited) {
				nOfVertices = dfs(vertex, list);
				for (Vertex next : list) {
					degree += next.adjacentVertices.size();
				}
				if (nOfVertices >= 2) {
					nOfE = degree / 2;
				}
				
			}
		}
		System.out.println(sb);
	}

	static ArrayList<Vertex> list;

	static int dfs(Vertex vertex, List<Vertex> list) {
		int count = 1;
		vertex.visited = true;
		list.add(vertex);
//		int degree = vertex.adjacentVertices.size();
		for (Vertex v : vertex.adjacentVertices) {
			if (!v.visited) {
				count += dfs(v, list);
			}
		}
		return count;
	}

	static Vertex[] readGraph() {
		int n = ni();
		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}

		int m = ni();
		for (int i = 0; i < m; i++) {
			Vertex u = vertices[ni()];
			Vertex v = vertices[ni()];
			u.addVertex(v);
			v.addVertex(u);
		}

		for (Vertex vertex : vertices) {
			vertex.adjacentVertices.sort((v1, v2) -> v1.id - v2.id);
		}
		return vertices;
	}

	static class Vertex {
		public int id;
		public boolean visited = false;
		public List<Vertex> adjacentVertices = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addVertex(Vertex vertex) {
			adjacentVertices.add(vertex);
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;

public class question2 {

	public static void main(String[] args) {
		Vertex[] vertices = readGraph();
		StringBuilder sb = new StringBuilder();
		for (Vertex vertex : vertices) {
			List<Integer> list = new ArrayList<>(vertex.map.keySet());
			list.sort(null);
			for (Integer id : list) {
				sb.append(vertex.id).append(" ");
				sb.append(id).append(" ");
				sb.append(vertex.map.get(id)).append("\n");
			}
		}
		System.out.println(sb);
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
			int weight = ni();

			if (u.id < v.id) {
				u.addVertex(v, weight);
			} else {
				v.addVertex(u, weight);
			}
		}

//		for (int i = 0; i < vertices.length; i++) {
//			Vertex vertex = vertices[i];
//			vertex.adjacentVertices.sort((v1, v2) -> v1.id - v2.id);
//		}

		return vertices;
	}

	static class Vertex {
		public int id;
		public Hashtable<Integer, Integer> map = new Hashtable<>();
//		public List<Vertex> adjacentVertices = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
		}

		public void addVertex(Vertex vertex, int weight) {
			if (map.get(vertex.id) == null) {
				map.put(vertex.id, Integer.MAX_VALUE);
			}
			if (weight < map.get(vertex.id)) {
				map.put(vertex.id, weight);
			}
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class EITREHE1 {

	public static void main(String[] args) {
		Vertex[] vertices = readGraph();
		int result = 0;
		for (Vertex vertex : vertices) {
			if (!vertex.isInQueue) {
				int count = BFS(vertex) - 1;
				result = result > count ? result : count;
			}
		}
		System.out.println(result);
	}

	static int BFS(Vertex vertex) {
		Queue<Vertex> queue = new ArrayDeque<>();
		int level = 0;
		for (Vertex vertex1 : vertex.adjacentVertices) {
			if (vertex1.isInQueue) {
				continue;
			}
			vertex.isInQueue = true;
			queue.add(vertex);

			while (!queue.isEmpty()) {
				level++;
				int size = queue.size();
				Vertex vertex2 = queue.poll();
				for (int i = 0; i < size; i++) {
					for (Vertex next : vertex2.adjacentVertices) {
						if (!next.isInQueue) {
							next.isInQueue = true;
							queue.add(next);
						}
					}
					if (size > 1 && i < size - 1 && queue.size() > 0) {
						vertex2 = queue.poll();
					}
				}
			}
		}
		return level;
	}

	static Vertex[] readGraph() {
		int n = ni();
		Vertex[] vertices = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < n - 1; i++) {
			Vertex u = vertices[ni()];
			Vertex v = vertices[ni()];
			u.addVertex(v);
			v.addVertex(u);
		}

		for (int i = 0; i < vertices.length; i++) {
			Vertex vertex = vertices[i];
			vertex.adjacentVertices.sort((v1, v2) -> v1.id - v2.id);
		}

		return vertices;
	}

	static class Vertex {
		public int id;
		public boolean isInQueue = false;
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

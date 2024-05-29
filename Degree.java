package week_1;

import java.io.*;
import java.util.*;;

public class Degree {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Vertex[] graph = readGraph();
		for (int i = 1; i < graph.length; ++i) {
			Vertex v = graph[i];
			sb.append(v.getDegree()).append(" ");
		}
		System.out.println(sb);
	}

	static Vertex[] readGraph() {
		int nVertices = ni();
		int nEdges = ni();

		// tạo ra mảng để lưu các đỉnh và khởi tạo các đỉnh.
		Vertex[] vertices = new Vertex[nVertices + 1];

		for (int i = 0; i <= nVertices; ++i) {
			vertices[i] = new Vertex(i);
		}

		// đọc lần lượt các cạnh
		for (int i = 0; i < nEdges; ++i) {
			int a = ni();
			int b = ni();

			// đồ thị vô hướng nên cạnh a-b nghĩa là: a kề b, b kề a.
			vertices[a].addAdjecentVertex(vertices[b]);
			vertices[b].addAdjecentVertex(vertices[a]);
		}
		return vertices;
	}

	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<Vertex>();

		public Vertex(int id) {
			super();
			this.id = id;
		}

		// lưu các đỉnh cận kề.
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

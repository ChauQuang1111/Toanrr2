package week5;

import java.io.*;
import java.util.*;

class TimKiemFile {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		int n = ni();
		Map<String, Vertex> graph = readGraph(n);
		
		String root = ns();
		String keyWord = ns();
		
		
	}
	
	static Map<String, Vertex> readGraph(int n) {
		Map<String, Vertex> vertices = new TreeMap<>();
		
		for (int i = 0; i < n - 1; i++) {
			String u = ns();
			String v = ns();
			
			if (!vertices.containsKey(u)) {
				vertices.put(u, new Vertex(u));
			}
			
			if(!vertices.containsKey(v)) {
				vertices.put(v, new Vertex(v));
			}
			
			Vertex vertexU = vertices.get(u);
			Vertex vertexV = vertices.get(v);
			
			vertexU.addAdjecentVertex(vertexV);
			vertexV.addAdjecentVertex(vertexU);
		}
		
		for (var key : vertices.keySet()) {
			vertices.get(key).adjecentVertices.sort((s1, s2) -> {
				return s1.id.compareTo(s2.id);
			});
		}
		return vertices;
	}
	
	static void dfs() {
		
	}
	
	static class Vertex {
		String id;
		List<Vertex> adjecentVertices = new ArrayList<>();
		boolean visited;
		int count;

		public Vertex(String id) {
			this.id = id;
		}

		public void addAdjecentVertex(Vertex vertex) {
			if(adjecentVertices.contains(vertex)) {
				return;
			}
			this.adjecentVertices.add(vertex);
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

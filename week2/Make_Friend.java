package week2;

import java.io.*;
import java.util.*;


public class Make_Friend {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		int nAccount = ni();
		int nRelationship = ni();
		
		Vertex[] graph = readGraph(nAccount, nRelationship);
		
		for (int i = 0; i < graph.length; i++) {
			sb.append(graph[i].id).append(" ").append(graph[i].getDegree()).append(" ");
			
			
			for (var j : graph[i].adjecentVertices) {
				sb.append(j.id).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static Vertex[] readGraph(int nAccount, int nRelationship) {
		Vertex[] vertices = new Vertex[nAccount];

		for (int i = 0; i < nAccount; ++i) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < nRelationship; ++i) {
			int a = ni();
			int b = ni();

			vertices[a].addAdjecentVertex(vertices[b]);
			vertices[b].addAdjecentVertex(vertices[a]);
		}
		
		for (Vertex v : vertices) {
			v.adjecentVertices.sort((s1, s2) -> Integer.compare(s1.id, s2.id));
		}
		return vertices;
	}
	
	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<Vertex>();
		public boolean visited;

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

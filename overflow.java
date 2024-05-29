package week5;

import java.io.*;
import java.util.*;

public class overflow {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();

		int n = ni();		
		Vertex[] graph = readGraph(n);
		
		for(Vertex i : graph) {
			if(!i.visited) {
				dfs(i);
			}
		}
				
		for(Vertex i : graph) {
			if(i.water == 0) {
				continue;
			}			
			sb.append(i.id).append(" ").append(i.water).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(Vertex v) {
		v.visited = true;
		int numberOfShare = v.adjacentVertices.size();
		double shareAmount = 0;
		
		if(numberOfShare != 0) {
			shareAmount = v.water / numberOfShare;
			v.water = 0;
		}
		
		for(Vertex vertex : v.adjacentVertices) {
			if(!vertex.visited) {
				vertex.water += shareAmount;				
				dfs(vertex);
			}
		}
	}
	
	static Vertex[] readGraph(int n) {
		Vertex[] vertices = new Vertex[n];

		for (int i = 0; i < n; ++i) {
			vertices[i] = new Vertex(i, nd());
		}

		for (int i = 0; i < n - 1; ++i) {
			int u = ni();
			int v = ni();

			vertices[u].addAdjacentVertex(vertices[v]);
		}
		
		for(int i = 0; i < n;i++) {
			vertices[i].adjacentVertices.sort((v1,v2) -> v1.id - v2.id);
		}

		return vertices;
	}
	
	static class Vertex {
		int id;
		List<Vertex> adjacentVertices = new ArrayList<>();
		boolean visited;
		double water;

		public Vertex(int id, double water) {
			this.id = id;
			this.water = water;
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

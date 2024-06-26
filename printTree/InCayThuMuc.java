package printTree;

import java.io.*;
import java.util.*;

public class InCayThuMuc {

	static StringBuilder sb = new StringBuilder();
	static String dash = "---";
	
	public static void main(String[] args) {
		int n = ni();
		HashMap<String, Vertex> vertices = new HashMap<>();
		
		for (int i = 0; i < n - 1; i++) {
			String n1 = ns();
			String n2 = ns();
			
			if(!vertices.containsKey(n1)) {
				vertices.put(n1, new Vertex(n1));
			}
			
			if(!vertices.containsKey(n2)) {
				vertices.put(n2, new Vertex(n2));
			}
			
			Vertex u = vertices.get(n1);
			Vertex v = vertices.get(n2);
			
			u.addAdjecentVertex(v);
			v.addAdjecentVertex(u);			
		}
		
		for (var k : vertices.keySet()) {
			vertices.get(k).adjecentVertices.sort((k1, k2) ->{
				return k1.id.compareToIgnoreCase(k2.id);
			});
		}
		
		String root = ns();
		String level = "-";
		
		dfs(vertices.get(root), level);
		System.out.println(sb);
		
		
	}
	
	static void dfs(Vertex vertex, String level) {
		vertex.visited = true;
		
		sb.append(level);
		sb.append(vertex.id);
		sb.append("\n");
		
		level = level.concat(dash);
		
		for (Vertex k : vertex.adjecentVertices) {
			if(!k.visited) {
				dfs(k,level);
			}
		}		
	}
	
	static class Vertex {
		String id;
		List<Vertex> adjecentVertices = new ArrayList<>();
		boolean visited;

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

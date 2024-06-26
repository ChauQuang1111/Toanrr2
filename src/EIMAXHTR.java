import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.text.AbstractDocument.LeafElement;

public class EIMAXHTR {
	static InputReader reader;
	static StringBuilder sb;
	static int maxHeight = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		reader = new InputReader(System.in);
		sb = new StringBuilder();
		Vertex[] graph = readGraph();
		for (Vertex v : graph) {
			int height = 0;
			dfs(v);
			for (Vertex w : graph) {
				if (w.level>height) {
					height = w.level;
				}
				w.visited=false;
				w.level=0;
			}
			v.height = height;
			if (height>maxHeight) {
				maxHeight = height;
			}
		}
		for (Vertex v : graph) {
			if (v.height == maxHeight) {
				sb.append(v);
			}
		}
		System.out.println(sb);
	}
	public static void dfs(Vertex v) {
		v.visited=true;
		for (Vertex w : v.adjecent) {
			if (!w.visited) {
				w.level = v.level+1;
				dfs(w);
			}
		}
	}
	
	public static Vertex[] readGraph() {
		int nVertices = reader.nextInt();
		Vertex[] graph = new Vertex[nVertices];
		
		for (int i=0; i<nVertices; i++) {
			graph[i] = new Vertex(i);
		}
		
		for (int i=0; i<nVertices-1; i++) {
			int a = reader.nextInt();
			int b =reader.nextInt();
			
			graph[a].addAdjecent(graph[b]);
			graph[b].addAdjecent(graph[a]);
		}
		return graph;
	}
	
	static class Vertex {
		private int id;
		private int level=0;
		private boolean visited;
		private int height;
		private List<Vertex> adjecent = new ArrayList<Vertex>();
		
		public Vertex(int id) {
			this.id = id;
		}
		
		public void addAdjecent(Vertex v) {
			adjecent.add(v);
		}
		@Override
		public String toString() {
			return id + " " + height;
		}
		
		
	}
	
	
	static class InputReader {
		private byte[] inbuf = new byte[2 << 23];
		public int lenbuf = 0, ptrbuf = 0;
		public InputStream is;

		public InputReader(InputStream stream) throws IOException {

			inbuf = new byte[2 << 23];
			lenbuf = 0;
			ptrbuf = 0;
			is = System.in;
			lenbuf = is.read(inbuf);
		}

		public InputReader(FileInputStream stream) throws IOException {
			inbuf = new byte[2 << 23];
			lenbuf = 0;
			ptrbuf = 0;
			is = stream;
			lenbuf = is.read(inbuf);
		}

		public boolean hasNext() throws IOException {
			if (skip() >= 0) {
				ptrbuf--;
				return true;
			}
			return false;
		}

		public String nextLine() throws IOException {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
										// != ' ')
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		private int readByte() {
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

		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		private double nextDouble() {
			return Double.parseDouble(next());
		}

		public Character nextChar() {
			return skip() >= 0 ? (char) skip() : null;
		}

		private int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public int nextInt() {
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

		public long nextLong() {
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
}

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class EIFOLTRE {
	static InputReader reader;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		reader = new InputReader(System.in);
		sb = new StringBuilder();
		List<Vertex> graph = readGraph();
		String firstVertice = reader.next();
		int level=0;
		for (Vertex v: graph) {
			if (v.name.equals(firstVertice)) {
				DFS(v,level);
			}
		}
		System.out.println(sb);
	}
	static void DFS(Vertex v, int level) {
		v.visited = true;
		for (int i=0; i<level; i++) {
			sb.append("---");
		}
		level++;
		sb.append("-");
		sb.append(v+"\n");
		for (Vertex w : v.adjecentVertices) {
			if(w.visited == false) {
				DFS(w, level);
			}
		}
	}
	static List<Vertex> readGraph() {
		int nVertices = reader.nextInt();
		HashMap<String, Vertex> tree = new HashMap<>();
		for (int i=0; i<nVertices-1; i++) {
			String a = reader.next();
			if (!tree.containsKey(a)) {
				Vertex A = new Vertex(a);
				tree.put(a, A);
			}
			String b = reader.next();
			if (!tree.containsKey(b)) {
				Vertex B = new Vertex(b);
				tree.put(b, B);
			}
			tree.get(a).addAdjecentVertex(tree.get(b));
			tree.get(b).addAdjecentVertex(tree.get(a));
		}
		List<Vertex> vertices = new ArrayList<>(tree.values());
		for (Vertex v:vertices) {
			v.adjecentVertices.sort((s1,s2) -> {
				int compare = s1.name.compareToIgnoreCase(s2.name);
				return compare;
			});
		}
		return vertices;
	}

	static class Vertex {
		public String name;
		public boolean visited;
		public List<Vertex> adjecentVertices = new ArrayList<Vertex>();

		public Vertex(String name) {
			this.name = name;
		}

		public void addAdjecentVertex(Vertex vertex) {
			adjecentVertices.add(vertex);
		}

		public int getDegree() {
			return adjecentVertices.size();
		}

		@Override
		public String toString() {
			return name + "";
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIMULEMA {
	static InputReader reader;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		reader = new InputReader(System.in);
		int nNode = reader.nextInt();
		Node[]graph = readGraph(nNode);
		dfs(graph[0]);
		for (Node n: graph) {
			sb.append(n).append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(Node n) {
		n.visited = true;
		for(Node m:n.adjecent) {
			if(!m.visited) {
				dfs(m);
				n.hoahong+=m.hoahong/2;
			}
		}
	}
	
	public static Node[] readGraph(int nNode) {
		Node[] graph = new Node[nNode];
		for(int i=0; i<nNode; i++) {
			int doanhthu = reader.nextInt();
			
			graph[i] = new Node(i, (int)Math.floor(doanhthu*0.15));
		}
		for(int i=0; i<nNode-1; i++) {
			int u = reader.nextInt();
			int v =reader.nextInt();
			
			graph[u].addAdjecent(graph[v]);
		}
		return graph;
	}
	
	static class Node {
		private int id;
		private int hoahong;
		private boolean visited;
		List<Node> adjecent = new ArrayList<Node>();
		
		public Node(int id, int hoahong) {
			super();
			this.id = id;
			this.hoahong = hoahong;
		}
		
		public void addAdjecent(Node n) {
			adjecent.add(n);
		}

		@Override
		public String toString() {
			return id + " " + hoahong;
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

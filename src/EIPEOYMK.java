import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class EIPEOYMK {

	public static void main(String[] args) {
		Vertex[] vertices = readGraph();
		Vertex u = vertices[ni()];
		int q = ni();

		int count = 1;
		u.isInQueue = true;

		Queue<Vertex> queue = new ArrayDeque<>();

		for (Vertex vertex : u.adjacentVertices) {
			if (vertex.isInQueue) {
				continue;
			}
			count = 1;
			vertex.isInQueue = true;
			queue.add(vertex);

			list.add(vertex.id);
			map.put(count, list);
		}

		while (!queue.isEmpty()) {
			bfs(queue, count);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			int k = ni();
			if (map.containsKey(k)) {
				List<Integer> newList = new ArrayList<>(map.get(k));
				newList.sort(Comparator.naturalOrder());
				for (int j = 0; j < newList.size(); j++) {
					sb.append(newList.get(j)).append(" ");
				}
				sb.append("\n");
			} else {
				sb.append("-1").append("\n");
			}
		}
		System.out.println(sb);
	}

	static void bfs(Queue<Vertex> queue, int count) {

		while (!queue.isEmpty()) {
			Vertex v = queue.poll();
			count++;
			if (map.containsKey(count)) {
				list = map.get(count);
			} else {
				list = new ArrayList<>();
			}
			int levelList = map.get(count - 1).size();
			for (int i = 0; i < levelList; i++) {
				for (Vertex next : v.adjacentVertices) {
					if (!next.isInQueue) {
						next.isInQueue = true;
						queue.add(next);
						list.add(next.id);
					}
				}
				if (!list.isEmpty()) {
					map.put(count, list);
				}
				if (levelList > 1 && i < levelList - 1 && queue.size() > 0) {
					v = queue.poll();
				}
			}
		}
	}

	static List<Integer> list = new ArrayList<>();
	static HashMap<Integer, List<Integer>> map = new HashMap<>();

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
			u.addVertex(v);
			v.addVertex(u);
		}

		for (Vertex vertex : vertices) {
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

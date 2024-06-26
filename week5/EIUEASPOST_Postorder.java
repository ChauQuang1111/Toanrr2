package week5;

import java.io.*;
import java.util.*;;

public class EIUEASPOST_Postorder {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		int nNodes = ni();
		var nodes = readTree(nNodes);
		printPostOrder(nodes[0]);
		System.out.println(sb);
	}
	
	static void printPostOrder(Node node) {
		if (node == null) {
			return;
		}
		printPostOrder(node.nodeLeft);
		printPostOrder(node.nodeRight);
		sb.append(node.id).append(" ");
	}

	static Node[] readTree(int nNodes) {
		Node[] node = new Node[nNodes];

		for (int i = 0; i < nNodes; i++) {
			node[i] = new Node(i + 1);
		}

		for (int i = 0; i < nNodes; i++) {
			var leftIndex = ni();
			node[i].nodeLeft = leftIndex > 0 ? node[leftIndex - 1] : null;

			var rightIndex = ni();
			node[i].nodeRight = rightIndex > 0 ? node[rightIndex - 1] : null;
		}

		return node;
	}

	static class Node {
		public int id;
		public Node nodeLeft;
		public Node nodeRight;

		public Node(int id) {
			super();
			this.id = id;
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

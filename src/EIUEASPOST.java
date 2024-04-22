import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

public class EIUEASPOST {

	public static void main(String[] args) {
		int nNode = ni();
		Node[] nodes = readTree(nNode);
		StringBuilder sb = new StringBuilder();

		List<Integer> list = new ArrayList<>();
		PrintPostOrder(nodes[0], list);
		for (Integer id : list) {
			sb.append(id).append(" ");
		}
		System.out.println(sb);
	}

	static void PrintPostOrder(Node node, List<Integer> postOrder) {
		if (node == null) {
			return;
		}
		PrintPostOrder(node.Left, postOrder);
		PrintPostOrder(node.Right, postOrder);
		postOrder.add(node.id);
	}

	static Node[] readTree(int nNode) {
		Node[] nodes = new Node[nNode];
		for (int i = 0; i < nNode; i++) {
			nodes[i] = new Node(i + 1);
		}

		for (int i = 0; i < nNode; i++) {
			int leftIndex = ni();
			nodes[i].Left = leftIndex > 0 ? nodes[leftIndex - 1] : null;
			int rightIndex = ni();
			nodes[i].Right = rightIndex > 0 ? nodes[rightIndex - 1] : null;
		}
		return nodes;
	}

	static class Node {
		public int id;
		public Node Left;
		public Node Right;

		public Node(int id) {
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;


public class EIMKF {

	public static void main(String[] args) {
		int n = ni();
		Account[] acc = new Account[n];
		for (int i = 0; i < n; i++) {
			acc[i] = new Account(i);
		}

		int m = ni();
		for (int i = 0; i < m; i++) {
			Account acc1 = acc[ni()];
			Account acc2 = acc[ni()];
			acc1.addFriend(acc2);
			acc2.addFriend(acc1);
		}

		for (int i = 0; i < acc.length; i++) {
			Account account = acc[i];
			account.friendAcc.sort((v1, v2) -> v1.id - v2.id);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			Account acccount = acc[i];
			sb.append(i + " ");
			sb.append(acccount.friendAcc.size() + " ");
			for (int j = 0; j < acccount.friendAcc.size(); j++) {		
				sb.append(acccount.friendAcc.get(j).id + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static class Account {
		public int id;
		public List<Account> friendAcc = new ArrayList<>();

		public Account(int id) {
			this.id = id;
		}

		public void addFriend(Account account) {
			friendAcc.add(account);
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

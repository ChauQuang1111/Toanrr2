import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIFBF2 {
	static InputReader reader;
	static StringBuilder sb;
	static int nam=0;
	static int nu=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		reader = new InputReader(System.in);
		sb = new StringBuilder();
		Friend[] listFriend = readFraph();
		for (int i=1; i<listFriend.length; i++) {
			if (!listFriend[i].visited) {
				nam=0;
				nu=0;
				dfs(listFriend[i]);
			}
		}
		for (int i=1; i<listFriend.length; i++) {
			sb.append(listFriend[i]).append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(Friend f) {
		f.visited = true;
		if (f.gender.equalsIgnoreCase("Nam")) {
			nam++;
		} else {
			nu++;
		}
		for (Friend w : f.friends) {
			if (!w.visited) {
				dfs(w);
				w.countNam=nam;
				w.countNu=nu;
			}
		}
		f.countNam=nam;
		f.countNu=nu;
	}
	
	public static Friend[] readFraph() {
		int nfriend = reader.nextInt();
		int mRelationship = reader.nextInt();
		Friend[] listFriend = new Friend[nfriend+1];
		
		for (int i=1; i<=nfriend; i++) {
			listFriend[i] =new Friend(i, reader.next());
		}
		
		for (int i=0; i<mRelationship; i++) {
			int a = reader.nextInt();
			int b= reader.nextInt();
			
			listFriend[a].addFriend(listFriend[b]);
			listFriend[b].addFriend(listFriend[a]);
		}
		return listFriend;
	}
	
	public static class Friend {
		private int id;
		private String gender;
		private boolean visited;
		private int countNam;
		private int countNu;
		private List<Friend> friends = new ArrayList<Friend>();
		
		public Friend(int id, String gender) {
			this.id = id;
			this.gender = gender;
		}
		
		public void addFriend(Friend f) {
			friends.add(f);
		}

		@Override
		public String toString() {
			return id + " " + countNam + " " + countNu;
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

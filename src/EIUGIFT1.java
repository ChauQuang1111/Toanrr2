import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class EIUGIFT1 {
	static InputReader reader;
	static StringBuffer sb;

	public static void main(String[] args) {
		reader = new InputReader(System.in);
		sb = new StringBuffer();

		ArrayList<Double> cubes = new ArrayList<Double>();
		ArrayList<Double> papers = new ArrayList<Double>();

		int nCube = reader.nextInt();
		int mPaper = reader.nextInt();

		for (int i = 0; i < nCube; i++) {
			cubes.add(reader.nextDouble());
		}

		for (int i = 0; i < mPaper; i++) {
			papers.add(reader.nextDouble());
		}

		Collections.sort(cubes);
		Collections.sort(papers);
		
		long max = findMaxGift(cubes, papers);
		
		sb.append(max);
		System.out.println(max);

	}

	public static long findMaxGift(ArrayList<Double> cubes, ArrayList<Double> papers) {
		long max = 0;
		int i = 0, j = 0;

		while (i < cubes.size() && j < papers.size()) {
			if (papers.get(j) / cubes.get(i) < 2) {
				j++;
			} else if (papers.get(j) / cubes.get(i) > 3) {
				i++;
			} else {
				i++;
				j++;
				max++;
			}
		}
		return max;
	}

	static class InputReader {
		StringTokenizer tokenizer;
		BufferedReader reader;
		String token;
		String temp;

		public InputReader(InputStream stream) {
			tokenizer = null;
			reader = new BufferedReader(new InputStreamReader(stream));
		}

		public InputReader(FileInputStream stream) {
			tokenizer = null;
			reader = new BufferedReader(new InputStreamReader(stream));
		}

		public String nextLine() throws IOException {
			return reader.readLine();
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					if (temp != null) {
						tokenizer = new StringTokenizer(temp);
						temp = null;
					} else {
						tokenizer = new StringTokenizer(reader.readLine());
					}
				} catch (IOException e) {
				}
			}
			return tokenizer.nextToken();
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}

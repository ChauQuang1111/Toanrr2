import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


 class EIPEOYMK {

    static InputReader reader = new InputReader(System.in);
    static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws IOException {
        int n = reader.nextInt();
        int m = reader.nextInt();
        
        Node[] graph = readGraph(n, m);
        int u = reader.nextInt();
        int q = reader.nextInt();
       
        bfs(graph[u]);
        for (int i = 0; i < q; i++) {
            int k = reader.nextInt();
            List<Node> list = map.get(k);
            
            if(list == null) {
                stringBuffer.append("-1");
            }else {
                list.sort((n1,n2) -> Integer.compare(n1.id, n2.id));
                for (Node node : list) {
                    stringBuffer.append(node.id).append(" ");
                }
                
            }
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer);   
    }
    static HashMap<Integer, List<Node>> map = new HashMap<>();
    
    
	static void bfs(Node v) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(v);
		v.visit = true;
		while(!q.isEmpty()) {
			Node w = q.poll();
			for (Node x : w.listNode) {
				if(x.visit == false) {
					x.level = w.level+1;
					List<Node> list = map.get(x.level);
	                if(list == null) {
	                    list = new ArrayList<>();
	                    map.put(x.level,list);
	                }
	                list.add(x);
					x.visit = true;
					q.add(x);
				}
			}
		}
	}

    static Node[] readGraph(int n, int m) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < m; i++) {
            int u = reader.nextInt();
            int v = reader.nextInt();

            nodes[u].addNode(nodes[v]);
            nodes[v].addNode(nodes[u]);
        }
        return nodes;
    }

    static class Node {
        private int id;
        private boolean visit;
        private int level;
        List<Node> listNode = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public void addNode(Node n) {
            listNode.add(n);
        }
        @Override
        public String toString() {
            stringBuffer.append(id).append(" ");
            return stringBuffer.toString();
        }

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
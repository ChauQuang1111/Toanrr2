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


 class EIFBF {

    static InputReader reader = new InputReader(System.in);
    static StringBuffer stringBuffer = new StringBuffer();
    static int maxId = 0;
    public static void main(String[] args) throws IOException {
        int n = reader.nextInt();
        int m = reader.nextInt();
        
        List<Node> listA = new ArrayList<>();
        Node[] graph = readGraph(n, m);
        for (int i = 1 ; i < graph.length; i++) {
            int countMale = 0;
            int countFemale = 0;
            if (graph[i].visit == false) {
                List<Node> verticeComponent = new ArrayList<>();
                dfs(graph[i],verticeComponent);
                
                for (int j = 0; j <verticeComponent.size(); j++) {
                    if(verticeComponent.get(j).gender.equalsIgnoreCase("Nam")) {
                        countMale++;
                    }else {
                        countFemale ++;
                    }
                }
                Node vertex = new Node(maxId, countMale, countFemale);
                listA.add(vertex);
                maxId = 0;
            }
        }
        listA.sort((o1, o2) -> Integer.compare(o1.id, o2.id) );
        for (int i = 0; i < listA.size(); i++) {
            stringBuffer.append(listA.get(i).id + " " + listA.get(i).countMale + " " +listA.get(i).countFemale);
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer);
    }

    static void dfs(Node v, List<Node> component) {
        v.visit = true;
        component.add(v);
        if(v.id > maxId) {
            maxId = v.id;
        }
        for (Node w : v.listNode) {
            if(w.visit == false) {
                dfs(w,component );
            }
        }
    }


    static Node[] readGraph(int n, int m) {
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i < nodes.length; i++) {
            String gender = reader.next();
            nodes[i] = new Node(i,gender);
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
        private String gender;
        private boolean visit;
        private int countMale;
        private int countFemale;
        List<Node> listNode = new ArrayList<>();

        public Node(int id, String gender) {
            this.id = id;
            this.gender = gender;;
        }
        public Node(int id, int countMale, int countFemale) {
            this.id = id;
            this.countMale = countMale;
            this.countFemale = countFemale;
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

